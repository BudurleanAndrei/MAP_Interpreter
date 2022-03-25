package Model.Values;

import Model.Types.BoolType;
import Model.Types.Type;

public class BoolValue implements IValue{
    boolean value;

    public BoolValue(boolean value){
        this.value = value;
    }

    public boolean getValue(){
        return value;
    }

    @Override
    public String toString() {
        if (value)
            return "true";
        return "false";
    }

    @Override
    public Type getType()
    {
        return new BoolType();
    }

    @Override
    public boolean equals(IValue another){
        if (!(another instanceof BoolValue))
            return false;
        return value == ((BoolValue)another).getValue();
    }
}
