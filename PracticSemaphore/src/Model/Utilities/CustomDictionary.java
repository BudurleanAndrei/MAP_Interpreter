package Model.Utilities;

import java.util.HashMap;
import java.util.Map;

public class CustomDictionary<TKey, TValue> implements ICustomDictionary<TKey, TValue>{
    private HashMap<TKey, TValue> dictionary;

    public CustomDictionary(){
        dictionary = new HashMap<TKey, TValue>();
    }

    @Override
    public TValue get(TKey key) {
        return dictionary.get(key);
    }

    @Override
    public void put(TKey key, TValue value) {
        dictionary.put(key, value);
    }

    @Override
    public void remove(TKey key) {
        dictionary.remove(key);
    }

    @Override
    public boolean isEmpty() {
        return dictionary.isEmpty();
    }

    @Override
    public boolean containsKey(TKey key){
        return dictionary.containsKey(key);
    }

    @Override
    public Map<TKey, TValue> getContent() {
        return dictionary;
    }

    @Override
    public ICustomDictionary<TKey, TValue> copy() {
        ICustomDictionary<TKey, TValue> newDictionary = new CustomDictionary<>();

        for (TKey key : dictionary.keySet())
            newDictionary.put(key, dictionary.get(key));

        return newDictionary;
    }

    public String toString(){
        String str = "";
        for (TKey key : dictionary.keySet())
            str = str + "\n" + key.toString() + " -> " + dictionary.get(key).toString();
        return str;
    }
}
