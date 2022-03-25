package Model.Utilities;

import java.util.Map;

public interface ISemaphoreTable<TKey, TValue> {
    TValue get(TKey key);
    void put(TKey key, TValue value);
    void remove(TKey key);
    boolean containsKey(TKey key);
    Map<TKey, TValue> getContent();
    void setContent(Map<TKey, TValue> content);
    String toString();
    int getFreeLocation();
}

