package Model.Statements;

import Model.ProgramState;
import Model.Types.Type;
import Model.Utilities.ICustomDictionary;
import Model.Utilities.MyException;
import Model.Values.IValue;
import Model.Values.IntValue;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AcquireStatement implements IStatement{
    private String var;
    private static Lock lock = new ReentrantLock();

    public AcquireStatement(String var){
        this.var = var;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws MyException {
        IValue value = programState.getSymbolTable().get(var);
        IntValue number = (IntValue) value;

        if (!programState.getSemaphoreTable().containsKey(number.getValue()))
            throw new MyException("Variable is not a semaphore!");

        lock.lock();

        var entry = programState.getSemaphoreTable().get(number.getValue());
        if (entry.getValue0() - entry.getValue2() > entry.getValue1().size()){
            if (!entry.getValue1().contains(programState.getId())){
                entry.getValue1().add(programState.getId());
            }
        }
        else
            programState.getExecutionStack().push(new AcquireStatement(var));

        lock.unlock();

        return null;
    }

    @Override
    public ICustomDictionary<String, Type> typeCheck(ICustomDictionary<String, Type> typeEnv) throws MyException {
        return null;
    }

    @Override
    public String toString(){
        return "acquire(" + var + ")";
    }
}
