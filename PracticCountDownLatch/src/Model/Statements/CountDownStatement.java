package Model.Statements;

import Model.ProgramState;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Utilities.ICustomDictionary;
import Model.Utilities.MyException;
import Model.Values.IntValue;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CountDownStatement implements IStatement{
    private String var;
    private static Lock lock;

    public CountDownStatement(String var){
        this.var = var;
        lock = new ReentrantLock();
    }

    @Override
    public ProgramState execute(ProgramState programState) throws MyException {
        if (!programState.getSymbolTable().containsKey(var))
            throw new MyException("Variable does not exist!");
        if (!programState.getSymbolTable().get(var).getType().equals(new IntType()))
            throw new MyException("Variable must be of type int!");

        lock.lock();

        Integer index = ((IntValue)programState.getSymbolTable().get(var)).getValue();

        if (!programState.getLatchTable().containsKey(index))
            throw new MyException("Variable is not a latch!");
        if (programState.getLatchTable().get(index) > 0)
            programState.getLatchTable().put(index, programState.getLatchTable().get(index) - 1);

        programState.getOutput().add(new IntValue(programState.getId()));

        lock.unlock();
        return null;
    }

    @Override
    public ICustomDictionary<String, Type> typeCheck(ICustomDictionary<String, Type> typeEnv) throws MyException {
        if (!typeEnv.get(var).equals(new IntType()))
            throw new MyException("Variable must be of int type!");

        return typeEnv;
    }

    @Override
    public String toString() {
        return "countDown(" + var + ")";
    }
}
