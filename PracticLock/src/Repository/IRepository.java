package Repository;

import Model.ProgramState;
import Model.Utilities.MyException;

import java.util.List;

public interface IRepository {
    void addProgramState(ProgramState programState);
    void logProgramState(ProgramState program) throws MyException;
    void clearLogFile() throws MyException;
    void setProgramsList(List<ProgramState> programs);
    List<ProgramState> getPrograms();
}
