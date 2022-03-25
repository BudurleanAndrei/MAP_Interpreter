package Model.Statements;

import Model.ProgramState;
import Model.Types.Type;
import Model.Utilities.CustomStack;
import Model.Utilities.ICustomDictionary;
import Model.Utilities.ICustomStack;
import Model.Utilities.MyException;
import Model.Values.IValue;

public class ForkStatement implements IStatement{
    private IStatement statement;

    public ForkStatement(IStatement statement){
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws MyException {
        ICustomStack<IStatement> newExecutionStack = new CustomStack<>();
        ICustomDictionary<String, IValue> newSymbolTable = programState.getSymbolTable().copy();

        return new ProgramState(newExecutionStack, newSymbolTable, programState.getOutput(), programState.getFileTable(),
                programState.getHeap(), statement, ProgramState.generateChildId(), programState.getSemaphoreTable());
    }

    @Override
    public ICustomDictionary<String, Type> typeCheck(ICustomDictionary<String, Type> typeEnv) throws MyException {
        statement.typeCheck(typeEnv.copy());
        return typeEnv;
    }

    @Override
    public String toString(){
        return "fork(" + statement.toString() + ")";
    }
}
