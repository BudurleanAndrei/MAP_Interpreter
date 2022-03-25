package Model.Statements;

import Model.ProgramState;
import Model.Types.Type;
import Model.Utilities.ICustomDictionary;
import Model.Utilities.MyException;

public class CompoundStatement implements IStatement{
    private IStatement first;
    private IStatement second;

    public CompoundStatement(IStatement first, IStatement second){
        this.first = first;
        this.second = second;
    }

    @Override
    public ProgramState execute(ProgramState programState) {
        programState.getExecutionStack().push(second);
        programState.getExecutionStack().push(first);
        return null;
    }

    @Override
    public ICustomDictionary<String, Type> typeCheck(ICustomDictionary<String, Type> typeEnv) throws MyException {
        return second.typeCheck(first.typeCheck(typeEnv));
    }

    @Override
    public String toString() {
        return "(" + first.toString() + "," + second.toString() + ")";
    }
}
