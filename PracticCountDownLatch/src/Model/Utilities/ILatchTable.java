package Model.Utilities;

import java.util.Map;

public interface ILatchTable<TKey, TValue>{
    TValue get(TKey key);
    void put(TKey key, TValue value);
    void remove(TKey key);
    boolean containsKey(TKey key);
    Map<Integer, TValue> getContent();
    void setContent(Map<TKey, TValue> content);
    String toString();
    int getFreeLocation();
}
