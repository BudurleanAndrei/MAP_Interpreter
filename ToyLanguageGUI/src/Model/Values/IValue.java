package Model.Values;
import Model.Types.Type;

public interface IValue {
    String toString();
    Type getType();
    boolean equals(IValue another);
}
