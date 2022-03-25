package Model.Statements;

import Model.Expressions.IExpression;
import Model.ProgramState;
import Model.Types.Type;
import Model.Utilities.ICustomDictionary;
import Model.Utilities.MyException;
import Model.Values.IValue;

public class AssignStatement implements IStatement{
    private String key;
    private IExpression expression;

    public AssignStatement(String key, IExpression expression){
        this.key = key;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws MyException {
        if (programState.getSymbolTable().containsKey(key)){
            IValue value = expression.eval(programState.getSymbolTable(), programState.getHeap());
            Type typeKey = programState.getSymbolTable().get(key).getType();
            if (value.getType().equals(typeKey))
                programState.getSymbolTable().put(key, value);
            else
                throw new MyException("Variable has a different type!");
        }
        else
            throw new MyException("Variable does not exist!");

        return null;
    }

    @Override
    public ICustomDictionary<String, Type> typeCheck(ICustomDictionary<String, Type> typeEnv) throws MyException {
        Type typeVar = typeEnv.get(key);
        Type typeExpr = expression.typeCheck(typeEnv);
        if (typeVar.equals(typeExpr))
            return typeEnv;
        throw new MyException("Right and left side have different types!");
    }

    @Override
    public String toString(){
        return key + "=" + expression.toString();
    }
}
