package Repository;

import Model.ProgramState;
import Model.Utilities.MyException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository{
    private List<ProgramState> programs;
    private String filePath;

    public Repository(ProgramState programState, String filePath){
        programs = new ArrayList<>();
        programs.add(programState);
        this.filePath = filePath;
    }

    @Override
    public void addProgramState(ProgramState programState) {
        programs.add(programState);
    }

    @Override
    public void logProgramState(ProgramState program) throws MyException {
        try{
            PrintWriter logFile =new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)));
            logFile.println(program.toString());
            logFile.close();
        }
        catch (IOException exception){
            throw new MyException("Error opening file!");
        }
    }

    @Override
    public void clearLogFile() throws MyException {
        try {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(filePath, false)));
            logFile.println();
            logFile.close();
        }
        catch (IOException exception) {
            throw new MyException("Error opening file!");
        }
    }

    @Override
    public void setProgramsList(List<ProgramState> programs) {
        this.programs = programs;
    }

    public List<ProgramState> getPrograms(){
        return programs;
    }
}
