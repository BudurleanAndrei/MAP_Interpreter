package Model.Statements;

import Model.ProgramState;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Utilities.ICustomDictionary;
import Model.Utilities.MyException;
import Model.Values.IntValue;

public class NewLockStatement implements IStatement{
    private String var;

    public NewLockStatement(String var){
        this.var = var;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws MyException {
        if (!programState.getSymbolTable().containsKey(var))
            throw new MyException("Variable does not exist!");
        if (!programState.getSymbolTable().get(var).getType().equals(new IntType()))
            throw new MyException("Variable must be of type int!");

        int freeLocation = programState.getLockTable().getFreeLocation();
        programState.getLockTable().put(freeLocation, -1);
        programState.getSymbolTable().put(var, new IntValue(freeLocation));

        return null;
    }

    @Override
    public ICustomDictionary<String, Type> typeCheck(ICustomDictionary<String, Type> typeEnv) throws MyException {
        return null;
    }

    @Override
    public String toString(){
        return "newLock(" + var + ")";
    }
}
