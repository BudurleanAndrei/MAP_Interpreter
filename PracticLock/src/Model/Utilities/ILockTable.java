package Model.Utilities;

import java.util.Map;

public interface ILockTable {
    Integer get(Integer key);
    void put(Integer key, Integer value);
    void remove(Integer key);
    boolean containsKey(Integer key);
    Map<Integer, Integer> getContent();
    void setContent(Map<Integer, Integer> content);
    String toString();
    int getFreeLocation();
}
