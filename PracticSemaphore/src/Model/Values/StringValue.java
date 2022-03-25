package Model.Values;

import Model.Types.StringType;
import Model.Types.Type;

import java.util.Objects;

public class StringValue implements IValue{
    private String value;

    public StringValue(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString(){
        return value;
    }

    @Override
    public boolean equals(IValue another){
        if (!(another instanceof StringValue))
            return false;
        return Objects.equals(value, ((StringValue)another).getValue());
    }

    @Override
    public Type getType() {
        return new StringType();
    }
}
