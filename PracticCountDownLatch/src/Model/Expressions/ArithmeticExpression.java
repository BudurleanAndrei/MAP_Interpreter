package Model.Expressions;

import Model.Types.IntType;
import Model.Types.Type;
import Model.Utilities.ICustomDictionary;
import Model.Utilities.IHeap;
import Model.Utilities.MyException;
import Model.Values.IValue;
import Model.Values.IntValue;

public class ArithmeticExpression implements IExpression{
    private IExpression first;
    private IExpression second;
    private int operation; // 1 - PLUS, 2 - MINUS, 3 - MULTIPLY, 4 - DIVIDE

    public ArithmeticExpression(IExpression first, IExpression second, int operation){
        this.first = first;
        this.second = second;
        this.operation = operation;
    }

    @Override
    public IValue eval(ICustomDictionary<String, IValue> table, IHeap<Integer, IValue> heap) throws MyException{
        IValue value1 = first.eval(table, heap);
        if (!value1.getType().equals(new IntType()))
            throw new MyException("Invalid type for first value!");
        IValue value2 = second.eval(table, heap);
        if (!value2.getType().equals(new IntType()))
            throw new MyException("Invalid type for second value!");

        IntValue result1 = (IntValue)value1;
        IntValue result2 = (IntValue)value2;

        switch(operation){
            case 1 -> {
                return new IntValue(result1.getValue() + result2.getValue());
            }
            case 2 -> {
                return new IntValue(result1.getValue() - result2.getValue());
            }
            case 3 -> {
                return new IntValue(result1.getValue() * result2.getValue());
            }
            case 4 -> {
                if (result2.getValue() == 0)
                    throw new MyException("Division by 0!");
                return new IntValue(result1.getValue() / result2.getValue());
            }
            default -> {
                throw new MyException("Invalid operation!");
            }
        }
    }

    @Override
    public Type typeCheck(ICustomDictionary<String, Type> typeEnv) throws MyException{
        Type type1, type2;
        type1 = first.typeCheck(typeEnv);
        type2 = second.typeCheck(typeEnv);

        if (type1.equals(new IntType())){
            if (type2.equals(new IntType()))
                return new IntType();
            throw new MyException("Second operand is not of type int!");
        }
        else
            throw new MyException("First operand is not of type int!");
    }

    public String toString(){
        switch(operation){
            case 1 ->{
                return first.toString() + "+" + second.toString();
            }
            case 2 ->{
                return first.toString() + "-" + second.toString();
            }
            case 3 ->{
                return first.toString() + "*" + second.toString();
            }
            case 4 ->{
                return first.toString() + "/" + second.toString();
            }
            default ->{
                return "";
            }
        }
    }
}
