package Model.Statements;

import Model.Expressions.IExpression;
import Model.ProgramState;
import Model.Statements.IStatement;
import Model.Types.ReferenceType;
import Model.Types.Type;
import Model.Utilities.ICustomDictionary;
import Model.Utilities.IHeap;
import Model.Utilities.MyException;
import Model.Values.IValue;
import Model.Values.ReferenceValue;

public class HeapWriteStatement implements IStatement {
    private String variable;
    private IExpression expression;

    public HeapWriteStatement(String variable, IExpression expression){
        this.variable = variable;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws MyException {
        if (!programState.getSymbolTable().containsKey(variable))
            throw new MyException("Variable does not exist!");
        if (!(programState.getSymbolTable().get(variable).getType() instanceof ReferenceType))
            throw new MyException("Variable must be of type reference!");
        if (!programState.getHeap().containsKey(((ReferenceValue)programState.getSymbolTable().get(variable)).getAddress()))
            throw new MyException("Address does not exist!");

        IValue expressionValue = expression.eval(programState.getSymbolTable(), programState.getHeap());
        ReferenceValue value = (ReferenceValue)programState.getSymbolTable().get(variable);
        Type type = ((ReferenceType)value.getType()).getInner();

        if (!expressionValue.getType().equals(type))
            throw new MyException("Expression and variable have different types!");

        programState.getHeap().put(((ReferenceValue)programState.getSymbolTable().get(variable)).getAddress(), expressionValue);

        return null;
    }

    @Override
    public ICustomDictionary<String, Type> typeCheck(ICustomDictionary<String, Type> typeEnv) throws MyException {
        Type typeVar = typeEnv.get(variable);
        Type typeExpr = expression.typeCheck(typeEnv);

        if (typeVar instanceof ReferenceType) {
            Type innerType = ((ReferenceType) typeVar).getInner();

            if (innerType.equals(typeExpr))
                return typeEnv;
            throw new MyException("Right and left side must be of ref type!");
        }
        throw new MyException("Variable must be of reference type!");
    }

    @Override
    public String toString(){
        return "writeH(" + variable + ", " + expression.toString() + ")";
    }
}
