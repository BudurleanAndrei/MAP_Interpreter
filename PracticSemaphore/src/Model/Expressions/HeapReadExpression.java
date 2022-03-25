package Model.Expressions;

import Model.Types.ReferenceType;
import Model.Types.Type;
import Model.Utilities.ICustomDictionary;
import Model.Utilities.IHeap;
import Model.Utilities.MyException;
import Model.Values.IValue;
import Model.Values.ReferenceValue;

public class HeapReadExpression implements IExpression{
    private IExpression expression;

    public HeapReadExpression(IExpression expression){
        this.expression = expression;
    }

    @Override
    public IValue eval(ICustomDictionary<String, IValue> table, IHeap<Integer, IValue> heap) throws MyException {
        IValue value = expression.eval(table, heap);

        if (!(value instanceof ReferenceValue referenceValue))
            throw new MyException("Expression must evaluate to reference value!");

        if (!heap.containsKey(referenceValue.getAddress()))
            throw new MyException("The heap does not contain the address!");

        return heap.get(referenceValue.getAddress());
    }

    @Override
    public Type typeCheck(ICustomDictionary<String, Type> typeEnv) throws MyException {
        Type type = expression.typeCheck(typeEnv);

        if (type instanceof ReferenceType){
            ReferenceType refType = (ReferenceType)type;
            return refType.getInner();
        }
        throw new MyException("The argument must be of reference type!");
    }

    @Override
    public String toString(){
        return "readH(" + expression.toString() + ")";
    }
}
