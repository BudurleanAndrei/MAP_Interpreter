package Model.Expressions;

import Model.Types.Type;
import Model.Utilities.ICustomDictionary;
import Model.Utilities.IHeap;
import Model.Utilities.MyException;
import Model.Values.IValue;

public class ValueExpression implements IExpression{
    private IValue value;

    public ValueExpression(IValue value){
        this.value = value;
    }

    @Override
    public IValue eval(ICustomDictionary<String, IValue> table, IHeap<Integer, IValue> heap) {
        return value;
    }

    @Override
    public Type typeCheck(ICustomDictionary<String, Type> typeEnv) throws MyException {
        return value.getType();
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
