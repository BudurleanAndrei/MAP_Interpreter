package Model.Utilities;

import java.util.*;

public class CustomStack<TElem> implements ICustomStack<TElem>{
    private Stack<TElem> stack;

    public CustomStack(){
        stack = new Stack<TElem>();
    }

    @Override
    public TElem pop() {
        return stack.pop();
    }

    @Override
    public void push(TElem elem) {
        stack.push(elem);
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public String toString(){
        List<TElem> list = Arrays.asList((TElem[])stack.toArray());
        Collections.reverse(list);
        return list.toString();
    }

    @Override
    public Stack<TElem> getContent() {
        return stack;
    }
}
