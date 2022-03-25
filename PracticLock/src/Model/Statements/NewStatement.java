package Model.Statements;

import Model.Expressions.IExpression;
import Model.ProgramState;
import Model.Types.ReferenceType;
import Model.Types.Type;
import Model.Utilities.ICustomDictionary;
import Model.Utilities.MyException;
import Model.Values.IValue;
import Model.Values.ReferenceValue;

import java.lang.ref.Reference;

public class NewStatement implements IStatement{
    String variable;
    IExpression expression;

    public NewStatement(String variable, IExpression expression){
        this.variable = variable;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws MyException {
        if (!programState.getSymbolTable().containsKey(variable))
            throw new MyException("Variable does not exist!");

        IValue value = programState.getSymbolTable().get(variable);

        if (!(programState.getSymbolTable().get(variable).getType() instanceof ReferenceType))
            throw new MyException("Variable is not reference type!");

        IValue expressionValue = expression.eval(programState.getSymbolTable(), programState.getHeap());
        ReferenceValue variableValue = (ReferenceValue)programState.getSymbolTable().get(variable);
        Type type = ((ReferenceType)variableValue.getType()).getInner();

        if (!expressionValue.getType().equals(type))
            throw new MyException("Expression and variable have different types!");

        int location = programState.getHeap().getFreeLocation();
        programState.getHeap().put(location, expressionValue);

        programState.getSymbolTable().put(variable, new ReferenceValue(location, type));

        return null;
    }

    @Override
    public ICustomDictionary<String, Type> typeCheck(ICustomDictionary<String, Type> typeEnv) throws MyException {
        Type typeVar = typeEnv.get(variable);
        Type typeExpr = expression.typeCheck(typeEnv);
        if (typeVar.equals(new ReferenceType(typeExpr)))
            return typeEnv;
        else
            throw new MyException("Right and left side have different types!");
    }

    @Override
    public String toString(){
        return "new(" + variable + ", " + expression.toString() + ")";
    }
}
