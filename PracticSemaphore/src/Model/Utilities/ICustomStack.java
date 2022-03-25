package Model.Utilities;

import java.util.Stack;

public interface ICustomStack<TElem>{
    TElem pop();
    void push(TElem elem);
    boolean isEmpty();
    String toString();
    Stack<TElem> getContent();
}
