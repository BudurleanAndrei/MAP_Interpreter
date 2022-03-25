package Model.Utilities;

import java.util.ArrayList;
import java.util.List;

public class CustomList<TElem> implements ICustomList<TElem>{
    private ArrayList<TElem> list;

    public CustomList(){
        list = new ArrayList<TElem>();
    }

    @Override
    public void add(TElem elem) {
        list.add(elem);
    }

    @Override
    public void insert(int position, TElem elem) {
        list.add(position, elem);
    }

    @Override
    public TElem get(int position) {
        return list.get(position);
    }

    @Override
    public TElem remove(int position) {
        return list.remove(position);
    }

    @Override
    public void set(int position, TElem elem) {
        list.set(position, elem);
    }

    @Override
    public List<TElem> getContent(){
        return list;
    }

    @Override
    public String toString() {
        String str = "";
        for (TElem tElem : list)
            str = str + "\n" + tElem.toString();
        return str;
    }
}
