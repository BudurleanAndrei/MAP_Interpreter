package Model.Statements;

import Model.ProgramState;
import Model.Types.Type;
import Model.Utilities.ICustomDictionary;
import Model.Utilities.MyException;

public interface IStatement {
    ProgramState execute(ProgramState programState) throws MyException;
    ICustomDictionary<String, Type> typeCheck(ICustomDictionary<String, Type> typeEnv) throws MyException;
    String toString();
}
