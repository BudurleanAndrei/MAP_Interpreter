package Model.Utilities;

import java.util.Map;

public interface IHeap<Integer, IValue> {
    IValue get(Integer key);
    void put(Integer key, IValue value);
    void remove(Integer key);
    boolean containsKey(Integer key);
    Map<Integer, IValue> getContent();
    void setContent(Map<Integer, IValue> content);
    String toString();
    int getFreeLocation();
}
