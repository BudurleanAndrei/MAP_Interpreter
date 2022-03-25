package Model.Statements;

import Model.ProgramState;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Utilities.ICustomDictionary;
import Model.Utilities.MyException;
import Model.Values.IntValue;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UnlockStatement implements IStatement{
    private String var;
    private static Lock lock = new ReentrantLock();

    public UnlockStatement(String var){
        this.var = var;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws MyException {
        if (!programState.getSymbolTable().containsKey(var))
            throw new MyException("Variable does not exist!");
        if (!programState.getSymbolTable().get(var).getType().equals(new IntType()))
            throw new MyException("Variable must be of type int!");

        lock.lock();

        int index = ((IntValue)programState.getSymbolTable().get(var)).getValue();

        if (!programState.getLockTable().containsKey(index))
            throw new MyException("Variable is not a lock!");


        if (programState.getLockTable().get(index) == programState.getId())
            programState.getLockTable().put(index, -1);

        lock.unlock();

        return null;
    }

    @Override
    public ICustomDictionary<String, Type> typeCheck(ICustomDictionary<String, Type> typeEnv) throws MyException {
        return null;
    }

    @Override
    public String toString(){
        return "unlock(" + var + ")";
    }
}
