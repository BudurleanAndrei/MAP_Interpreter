package Model.Statements;

import Model.Expressions.IExpression;
import Model.ProgramState;
import Model.Types.IntType;
import Model.Types.StringType;
import Model.Types.Type;
import Model.Utilities.ICustomDictionary;
import Model.Utilities.MyException;
import Model.Values.IValue;
import Model.Values.IntValue;
import Model.Values.StringValue;

import java.io.IOException;

public class FileReadStatement implements IStatement{
    private IExpression expression;
    private String variable;

    public FileReadStatement(IExpression expression, String variable){
        this.expression = expression;
        this.variable = variable;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws MyException {
        IValue result = expression.eval(programState.getSymbolTable(), programState.getHeap());
        if (!result.getType().equals(new StringType()))
            throw new MyException("Expression must evaluate to string!");

        StringValue file = (StringValue)result;

        if (!programState.getFileTable().containsKey(file))
            throw new MyException("File is no open!");

        if (!programState.getSymbolTable().containsKey(variable))
            throw new MyException("Variable does not exist!");

        if (!programState.getSymbolTable().get(variable).getType().equals(new IntType()))
            throw new MyException("Variable is not of type int!");

        try{
            String line = programState.getFileTable().get(file).readLine();

            if (line == null)
                programState.getSymbolTable().put(variable, new IntType().defaultValue());
            else
                programState.getSymbolTable().put(variable, new IntValue(Integer.parseInt(line)));
        }
        catch (IOException exception) {
            throw new MyException("Error on reading from file!");
        }

        return null;
    }

    @Override
    public ICustomDictionary<String, Type> typeCheck(ICustomDictionary<String, Type> typeEnv) throws MyException {
        Type typeExpr = expression.typeCheck(typeEnv);
        Type typeVar = typeEnv.get(variable);

        if (typeExpr.equals(new StringType())){
            if (typeVar.equals(new IntType()))
                return typeEnv;
            throw new MyException("Variable must be of type int!");
        }
        throw new MyException("Expression must evaluate to string type!");
    }

    @Override
    public String toString(){
        return "fileRead(" + expression.toString() + ", " + variable + ")";
    }
}
