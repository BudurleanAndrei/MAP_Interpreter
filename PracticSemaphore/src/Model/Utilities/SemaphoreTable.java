package Model.Utilities;

import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SemaphoreTable<TKey, TValue> implements ISemaphoreTable<Integer, Triplet<Integer, ArrayList<Integer>, Integer>>{
    private Map<Integer, Triplet<Integer, ArrayList<Integer>, Integer>> semaphoreTable;
    private int freeLocation;

    public SemaphoreTable(){
        semaphoreTable = new HashMap<>();
        freeLocation = 1;
    }

    @Override
    public Triplet<Integer, ArrayList<Integer>, Integer> get(Integer integer) {
        return semaphoreTable.get(integer);
    }

    @Override
    public void put(Integer integer, Triplet<Integer, ArrayList<Integer>, Integer> objects) {
        semaphoreTable.put(integer, objects);
    }

    @Override
    public void remove(Integer integer) {
        semaphoreTable.remove(integer);
    }

    @Override
    public boolean containsKey(Integer integer) {
        return semaphoreTable.containsKey(integer);
    }

    @Override
    public Map<Integer, Triplet<Integer, ArrayList<Integer>, Integer>> getContent() {
        return semaphoreTable;
    }

    @Override
    public void setContent(Map<Integer, Triplet<Integer, ArrayList<Integer>, Integer>> content) {
        semaphoreTable = content;
    }

    @Override
    public int getFreeLocation() {
        return freeLocation;
    }
}
