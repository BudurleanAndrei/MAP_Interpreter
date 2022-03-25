package Model.Utilities;

import java.util.HashMap;
import java.util.Map;

public class LockTable implements ILockTable{
    private Map<Integer, Integer> lockTable;
    private Integer freeLocation;

    public LockTable(){
        lockTable = new HashMap<>();
        freeLocation = 0;
    }

    @Override
    public Integer get(Integer key) {
        return lockTable.get(key);
    }

    @Override
    public void put(Integer key, Integer value) {
        lockTable.put(key, value);
        freeLocation++;
    }

    @Override
    public void remove(Integer key) {
        lockTable.remove(key);
    }

    @Override
    public boolean containsKey(Integer key) {
        return lockTable.containsKey(key);
    }

    @Override
    public Map<Integer, Integer> getContent() {
        return lockTable;
    }

    @Override
    public void setContent(Map<Integer, Integer> content) {
        lockTable = content;
    }

    @Override
    public int getFreeLocation() {
        return freeLocation;
    }
}
