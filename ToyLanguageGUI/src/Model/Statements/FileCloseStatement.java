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
import java.io.IOException;

public class FileCloseStatement implements IStatement{
    private IExpression expression;

    public FileCloseStatement(IExpression expression){
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws MyException {
        IValue result = expression.eval(programState.getSymbolTable(), programState.getHeap());
        if (!result.getType().equals(new StringType()))
            throw new MyException("Expression must evaluate to string!");

        StringValue file = (StringValue)result;

        if (!programState.getFileTable().containsKey(file))
            throw new MyException("File is not open!");

        try{
            programState.getFileTable().get(file).close();
            programState.getFileTable().remove(file);
        }
        catch (IOException exception) {
            throw new MyException("Error on closing file!");
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
        return "fileClose(" + expression.toString() + ")";
    }
}
