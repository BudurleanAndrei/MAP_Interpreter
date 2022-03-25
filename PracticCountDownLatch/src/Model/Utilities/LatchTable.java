package Model.Utilities;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LatchTable<TKey, TValue> implements ILatchTable<Integer, Integer>{
    private Map<Integer, Integer> latchTable;
    private int freeLocation;

    public LatchTable(){
        latchTable = new HashMap<>();
        freeLocation = 1;
    }

    @Override
    public Integer get(Integer integer) {
        return latchTable.get(integer);
    }

    @Override
    public void put(Integer integer, Integer integer2) {
        latchTable.put(integer, integer2);
        freeLocation++;
    }

    @Override
    public void remove(Integer integer) {
        latchTable.remove(integer);
    }

    @Override
    public boolean containsKey(Integer integer) {
        return latchTable.containsKey(integer);
    }

    @Override
    public Map<Integer, Integer> getContent() {
        return latchTable;
    }

    @Override
    public void setContent(Map<Integer, Integer> content) {
        latchTable = content;
    }

    @Override
    public int getFreeLocation() {
        return freeLocation;
    }
}
