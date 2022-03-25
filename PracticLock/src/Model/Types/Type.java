package Model.Types;

import Model.Values.IValue;

public interface Type {
    boolean equals(Object another);
    String toString();
    IValue defaultValue();
}
