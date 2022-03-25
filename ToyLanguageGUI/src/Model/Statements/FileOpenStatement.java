package Model.Statements;

import Model.Expressions.IExpression;
import Model.ProgramState;
import Model.Types.StringType;
import Model.Types.Type;
import Model.Utilities.ICustomDictionary;
import Model.Utilities.MyException;
import Model.Values.IValue;
import Model.Values.StringValue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class FileOpenStatement implements IStatement{
    private IExpression expression;

    public FileOpenStatement(IExpression expression){
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws MyException {
        IValue result = expression.eval(programState.getSymbolTable(), programState.getHeap());
        if (!result.getType().equals(new StringType()))
            throw new MyException("Expression must evaluate to string!");

        StringValue file = (StringValue)result;

        if (programState.getFileTable().containsKey(file))
            throw new MyException("File already open!");

        try{
            BufferedReader reader = new BufferedReader(new FileReader(file.getValue()));
            programState.getFileTable().put(file, reader);
        }
        catch (FileNotFoundException exception){
            throw new MyException("File does not exist!");
        }

        return null;
    }

    @Override
    public ICustomDictionary<String, Type> typeCheck(ICustomDictionary<String, Type> typeEnv) throws MyException {
        Type type = expression.typeCheck(typeEnv);

        if (type.equals(new StringType()))
            return typeEnv;
        throw new MyException("Expression must evaluate to string type!");
    }

    @Override
    public String toString(){
        return "fileOpen(" + expression.toString() + ")";
    }
}
