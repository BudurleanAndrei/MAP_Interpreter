package Model.Utilities;

import java.util.Map;

public interface ICustomDictionary<TKey, TValue>{
    TValue get(TKey key);
    void put(TKey key, TValue value);
    void remove(TKey key);
    boolean isEmpty();
    public boolean containsKey(TKey key);

    Map<TKey, TValue> getContent();

    ICustomDictionary<TKey,TValue> copy();
}
