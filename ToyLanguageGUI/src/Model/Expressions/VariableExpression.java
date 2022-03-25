package Model.Expressions;

import Model.Types.Type;
import Model.Utilities.ICustomDictionary;
import Model.Utilities.IHeap;
import Model.Utilities.MyException;
import Model.Values.IValue;

public class VariableExpression implements IExpression{
    private String key;

    public VariableExpression(String key){
        this.key = key;
    }

    @Override
    public IValue eval(ICustomDictionary<String, IValue> table, IHeap<Integer, IValue> heap) throws MyException {
        if (!table.containsKey(key))
            throw new MyException("Variable does not exist!");
        return table.get(key);
    }

    @Override
    public Type typeCheck(ICustomDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv.get(key);
    }

    @Override
    public String toString() {
        return key;
    }
}
