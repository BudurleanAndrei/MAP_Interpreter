package Model.Statements;

import Model.ProgramState;
import Model.Types.Type;
import Model.Utilities.ICustomDictionary;
import Model.Utilities.MyException;

public class NoOperationStatement implements IStatement{
    public NoOperationStatement(){

    }

    @Override
    public ProgramState execute(ProgramState programState) {
        return null;
    }

    @Override
    public ICustomDictionary<String, Type> typeCheck(ICustomDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public String toString(){
        return null;
    }
}
