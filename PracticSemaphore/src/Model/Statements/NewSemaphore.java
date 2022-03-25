package Model.Statements;

import Model.Expressions.IExpression;
import Model.ProgramState;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Utilities.ICustomDictionary;
import Model.Utilities.MyException;
import Model.Values.IValue;
import Model.Values.IntValue;
import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewSemaphore implements IStatement{
    private String var;
    private IExpression expression1;
    private IExpression expression2;
    private static Lock lock = new ReentrantLock();

    public NewSemaphore(String variable, IExpression expression1, IExpression expression2){
        this.var = variable;
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws MyException {
        IntValue value1 = (IntValue)expression1.eval(programState.getSymbolTable(), programState.getHeap());
        IntValue value2 = (IntValue)expression2.eval(programState.getSymbolTable(), programState.getHeap());

        if (!programState.getSymbolTable().containsKey(var))
            throw new MyException("Variable does not exist!");
        if (!programState.getSymbolTable().get(var).getType().equals(new IntType()))
            throw new MyException("Variable must be of type int!");

        lock.lock();

        int freeLocation = programState.getSemaphoreTable().getFreeLocation();
        programState.getSemaphoreTable().put(programState.getSemaphoreTable().getFreeLocation(),
                new Triplet<Integer, ArrayList<Integer>, Integer>(value1.getValue(), new ArrayList<Integer>(),
                        value2.getValue()));
        programState.getSymbolTable().put(var, new IntValue(freeLocation));

        lock.unlock();

        return null;
    }

    @Override
    public ICustomDictionary<String, Type> typeCheck(ICustomDictionary<String, Type> typeEnv) throws MyException {
        return null;
    }

    @Override
    public String toString(){
        return "newSem(" + var + ", " + expression1.toString() + ", " + expression2.toString() + ")";
    }
}
