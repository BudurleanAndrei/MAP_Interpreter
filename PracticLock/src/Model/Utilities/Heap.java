package Model.Utilities;

import Model.Values.IValue;

import java.util.HashMap;
import java.util.Map;

public class Heap<Integer, IValue> implements IHeap<Integer, IValue>{
    Map<Integer, IValue> heap;
    int freeLocation;

    public Heap(){
        heap = new HashMap<>();
        freeLocation = 1;
    }

    public int getFreeLocation(){
        return freeLocation;
    }

    @Override
    public IValue get(Integer key) {
        return heap.get(key);
    }

    @Override
    public void put(Integer key, IValue value) {
        heap.put(key, value);
        freeLocation++;
    }

    @Override
    public void remove(Integer key) {
        heap.remove(key);
    }

    @Override
    public boolean containsKey(Integer key) {
        return heap.containsKey(key);
    }

    @Override
    public Map<Integer, IValue> getContent() {
        return heap;
    }

    @Override
    public void setContent(Map<Integer, IValue> content) {
        heap = content;
    }

    @Override
    public String toString(){
        String str = "";
        for (Integer key : heap.keySet())
            str = str + "\n" + key.toString() + " -> " + heap.get(key).toString();
        return str;
    }
}
