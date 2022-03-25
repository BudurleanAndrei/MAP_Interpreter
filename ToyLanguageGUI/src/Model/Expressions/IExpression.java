package Model.Expressions;

import Model.Types.Type;
import Model.Utilities.ICustomDictionary;
import Model.Utilities.IHeap;
import Model.Utilities.MyException;
import Model.Values.IValue;

public interface IExpression {
    IValue eval(ICustomDictionary<String, IValue> table, IHeap<Integer, IValue> heap) throws MyException;
    Type typeCheck(ICustomDictionary<String, Type> typeEnv) throws MyException;
}
