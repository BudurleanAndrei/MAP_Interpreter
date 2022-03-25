package Model.Types;

import Model.Values.BoolValue;
import Model.Values.IValue;

public class BoolType implements Type{
    @Override
    public boolean equals(Object another){
        return another instanceof BoolType;
    }
    @Override
    public String toString() {
        return "bool";
    }

    @Override
    public IValue defaultValue(){
        return new BoolValue(false);
    }
}
