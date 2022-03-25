package Model.Statements;

import Model.Expressions.IExpression;
import Model.ProgramState;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Utilities.ICustomDictionary;
import Model.Utilities.MyException;
import Model.Values.IValue;
import Model.Values.IntValue;

import javax.swing.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewLatchStatement implements IStatement{
    private String var;
    private IExpression expression;
    private static Lock lock;

    public NewLatchStatement(String var, IExpression expression){
        this.var = var;
        this.expression = expression;
        lock = new ReentrantLock();
    }

    @Override
    public ProgramState execute(ProgramState programState) throws MyException {
        IValue value = expression.eval(programState.getSymbolTable(), programState.getHeap());

        if (!(value instanceof IntValue))
            throw new MyException("Expression must evaluate to int!");

        if (!programState.getSymbolTable().containsKey(var))
            throw new MyException("Variable does not exist!");
        if (!programState.getSymbolTable().get(var).getType().equals(new IntType()))
            throw new MyException("Variable must be of type int!");

        Integer result = ((IntValue)expression.eval(programState.getSymbolTable(), programState.getHeap())).getValue();

        lock.lock();

        int freeLocation = programState.getLatchTable().getFreeLocation();
        programState.getLatchTable().put(freeLocation, result);
        programState.getSymbolTable().put(var, new IntValue(freeLocation));

        lock.unlock();

        return null;
    }

    @Override
    public ICustomDictionary<String, Type> typeCheck(ICustomDictionary<String, Type> typeEnv) throws MyException {
        if (!typeEnv.get(var).equals(new IntType()))
            throw new MyException("Variable must be of int type!");
        Type typeExpr = expression.typeCheck(typeEnv);
        if (!typeExpr.equals(new IntType()))
            throw new MyException("Expression must evaluate to int type!");

        return typeEnv;
    }

    @Override
    public String toString(){
        return "newLatch(" + var + ", " + expression.toString() + ")";
    }
}
