package Model.Values;

import Model.Types.ReferenceType;
import Model.Types.Type;

public class ReferenceValue implements IValue{
    int address;
    Type locationType;

    public ReferenceValue(int address, Type locationType){
        this.address = address;
        this.locationType = locationType;
    }

    @Override
    public Type getType() {
        return new ReferenceType(locationType);
    }

    public int getAddress(){
        return address;
    }

    @Override
    public boolean equals(IValue another) {
        if (!(another instanceof ReferenceValue))
            return false;
        return address == ((ReferenceValue)another).getAddress();
    }

    @Override
    public String toString(){
        return "(" + address + ", " + locationType.toString() + ")";
    }
}
