package Model.Statements;

import Model.ProgramState;
import Model.Types.*;
import Model.Utilities.ICustomDictionary;
import Model.Utilities.MyException;
import Model.Values.BoolValue;
import Model.Values.IntValue;

public class DeclareStatement implements IStatement{
    private Type type;
    private String key;

    public DeclareStatement(String key, Type type){
        this.type = type;
        this.key = key;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws MyException {
        if (programState.getSymbolTable().containsKey(key))
            throw new MyException("Variable already exists!");

        if (type.toString().equals("int"))
            programState.getSymbolTable().put(key, new IntType().defaultValue());
        else
            if (type.toString().equals("bool"))
                programState.getSymbolTable().put(key, new BoolType().defaultValue());
            else
                if (type instanceof ReferenceType)
                    programState.getSymbolTable().put(key, new ReferenceType(((ReferenceType)type).getInner()).defaultValue());
                else
                    programState.getSymbolTable().put(key, new StringType().defaultValue());
        return null;
    }

    @Override
    public ICustomDictionary<String, Type> typeCheck(ICustomDictionary<String, Type> typeEnv) throws MyException {
        typeEnv.put(key, type);
        return typeEnv;
    }

    @Override
    public String toString(){
        return type.toString() + " " + key;
    }
}
