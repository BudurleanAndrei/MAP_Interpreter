package Model.Types;

import Model.Values.IValue;
import Model.Values.ReferenceValue;

public class ReferenceType implements Type{
    Type inner;

    public ReferenceType(Type inner){
        this.inner = inner;
    }

    @Override
    public boolean equals(Object another){
        if (another instanceof ReferenceType)
            return inner.equals(((ReferenceType)another).getInner());
        return false;
    }

    public Type getInner(){
        return inner;
    }

    @Override
    public IValue defaultValue() {
        return new ReferenceValue(0, inner);
    }

    @Override
    public String toString(){
        return "ref(" + inner.toString() + ")";
    }
}
