package Model.Expressions;

import Model.Types.BoolType;
import Model.Types.Type;
import Model.Utilities.ICustomDictionary;
import Model.Utilities.IHeap;
import Model.Utilities.MyException;
import Model.Values.BoolValue;
import Model.Values.IValue;

public class LogicExpression implements IExpression{
    private IExpression first;
    private IExpression second;
    private int operation; // 1 - AND, 2 - OR

    public LogicExpression(IExpression first, IExpression second, int operation){
        this.first = first;
        this.second = second;
        this.operation = operation;
    }

    @Override
    public IValue eval(ICustomDictionary<String, IValue> table, IHeap<Integer, IValue> heap) throws MyException {
        IValue value1 = first.eval(table, heap);
        if (value1.getType().equals(new BoolType()))
            throw new MyException("Invalid type for first value!");
        IValue value2 = second.eval(table, heap);
        if (value2.getType().equals(new BoolType()))
            throw new MyException("Invalid type for second value!");

        BoolValue result1 = (BoolValue)value1;
        BoolValue result2 = (BoolValue)value2;

        switch(operation){
            case 1 -> {
                return new BoolValue(result1.getValue() && result2.getValue());
            }
            case 2 -> {
                return new BoolValue(result1.getValue() || result2.getValue());
            }
            default ->{
                throw new MyException("Invalid operation!");
            }
        }
    }

    @Override
    public Type typeCheck(ICustomDictionary<String, Type> typeEnv) throws MyException {
        Type type1 = first.typeCheck(typeEnv);
        Type type2 = second.typeCheck(typeEnv);

        if (type1.equals(new BoolType())){
            if (type2.equals(new BoolType()))
                return new BoolType();
            throw new MyException("Second operand must be of type bool!");
        }
        throw new MyException("First operand must be of type bool!");
    }

    public String toString(){
        switch (operation){
            case 1 ->{
                return first.toString() + " && " + second.toString();
            }
            case 2 ->{
                return first.toString() + " || " + second.toString();
            }
            default ->{
                return "";
            }
        }
    }
}
