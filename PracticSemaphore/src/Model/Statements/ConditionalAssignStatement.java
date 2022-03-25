package Model.Statements;

import Model.Expressions.IExpression;
import Model.ProgramState;
import Model.Types.Type;
import Model.Utilities.ICustomDictionary;
import Model.Utilities.MyException;
import Model.Values.BoolValue;
import Model.Values.IValue;

public class ConditionalAssignStatement implements IStatement{
    private String key;
    private IExpression expression;
    private IExpression valueExpression1;
    private IExpression valueExpression2;

    public ConditionalAssignStatement(String key, IExpression expression, IExpression valueExpression1,
                                      IExpression valueExpression2){
        this.key = key;
        this.expression = expression;
        this.valueExpression1 = valueExpression1;
        this.valueExpression2 = valueExpression2;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws MyException {
        IValue cond = expression.eval(programState.getSymbolTable(), programState.getHeap());
        if (!(cond instanceof BoolValue))
            throw new MyException("Expression is not of type bool!");
        BoolValue condB = (BoolValue)cond;

        if (condB.getValue())
            programState.getSymbolTable().put(key, valueExpression1.eval(programState.getSymbolTable(),
                                                                         programState.getHeap()));
        else
            programState.getSymbolTable().put(key, valueExpression2.eval(programState.getSymbolTable(),
                                                                         programState.getHeap()));

        return null;
    }

    @Override
    public ICustomDictionary<String, Type> typeCheck(ICustomDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public String toString(){
        return key + "?" + valueExpression1.toString() + ":" + valueExpression2.toString();
    }
}
