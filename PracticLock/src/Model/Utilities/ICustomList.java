package Model.Utilities;

import java.util.List;

public interface ICustomList<TElem> {
    void add(TElem elem);
    void insert(int position, TElem elem);
    TElem get(int position);
    TElem remove(int position);
    void set(int position, TElem elem);
    List<TElem> getContent();
    String toString();
}
