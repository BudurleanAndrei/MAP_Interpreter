package Model.Types;

import Model.Values.IValue;
import Model.Values.StringValue;

public class StringType implements Type{
    @Override
    public boolean equals(Object another){
        return another instanceof StringType;
    }

    @Override
    public String toString(){
        return "String";
    }

    @Override
    public IValue defaultValue(){
        return new StringValue("");
    }
}
