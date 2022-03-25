package Model.Values;

import Model.Types.IntType;
import Model.Types.Type;

public class IntValue implements IValue{
    int value;

    public IntValue(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public Type getType()
    {
        return new IntType();
    }

    @Override
    public boolean equals(IValue another){
        if (!(another instanceof IntValue))
            return false;
        return value == ((IntValue)another).getValue();
    }
}
