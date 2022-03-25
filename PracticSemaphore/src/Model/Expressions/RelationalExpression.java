package Model.Expressions;

import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Utilities.ICustomDictionary;
import Model.Utilities.IHeap;
import Model.Utilities.MyException;
import Model.Values.BoolValue;
import Model.Values.IValue;
import Model.Values.IntValue;

public class RelationalExpression implements IExpression{
    private IExpression expression1;
    private IExpression expression2;
    private Relation relation;

    public RelationalExpression(IExpression expression1, IExpression expression2, String relation){
        this.expression1 = expression1;
        this.expression2 = expression2;
        switch(relation){
            case "<" -> this.relation = Relation.LESS;
            case "<=" -> this.relation = Relation.LESS_OR_EQUAL;
            case "==" -> this.relation = Relation.EQUAL;
            case "!=" -> this.relation = Relation.NOT_EQUAL;
            case ">=" -> this.relation = Relation.GREATER_OR_EQUAL;
            case ">" -> this.relation = Relation.GREATER;
        }
    }

    @Override
    public IValue eval(ICustomDictionary<String, IValue> table, IHeap<Integer, IValue> heap) throws MyException {
        IValue value1 = expression1.eval(table, heap);
        if (!value1.getType().equals(new IntType()))
            throw new MyException("First expression must be of type int!");
        IValue value2 = expression2.eval(table, heap);
        if (!value2.getType().equals(new IntType()))
            throw new MyException("Second expression must be of type int!");

        IntValue result1 = (IntValue) value1;
        IntValue result2 = (IntValue) value2;

        switch(relation){
            case LESS -> {
                return new BoolValue(result1.getValue() < result2.getValue());
            }
            case LESS_OR_EQUAL -> {
                return new BoolValue(result1.getValue() <= result2.getValue());
            }
            case EQUAL -> {
                return new BoolValue(result1.getValue() == result2.getValue());
            }
            case NOT_EQUAL -> {
                return new BoolValue(result1.getValue() != result2.getValue());
            }
            case GREATER_OR_EQUAL -> {
                return new BoolValue(result1.getValue() >= result2.getValue());
            }
            case GREATER -> {
                return new BoolValue(result1.getValue() > result2.getValue());
            }
        }
        return null;
    }

    @Override
    public Type typeCheck(ICustomDictionary<String, Type> typeEnv) throws MyException {
        Type type1, type2;
        type1 = expression1.typeCheck(typeEnv);
        type2 = expression2.typeCheck(typeEnv);

        if (type1.equals(new IntType())){
            if (type2.equals(new IntType()))
                return new BoolType();
            throw new MyException("Second operand is not of type int!");
        }
        else
            throw new MyException("First operand is not of type int!");
    }

    @Override
    public String toString(){
        switch(relation){
            case LESS -> {
                return expression1.toString() + " < " + expression2.toString();
            }
            case LESS_OR_EQUAL -> {
                return expression1.toString() + " <= " + expression2.toString();
            }
            case EQUAL -> {
                return expression1.toString() + " = " + expression2.toString();
            }
            case NOT_EQUAL -> {
                return expression1.toString() + " != " + expression2.toString();
            }
            case GREATER_OR_EQUAL -> {
                return expression1.toString() + " >= " + expression2.toString();
            }
            case GREATER -> {
                return expression1.toString() + " > " + expression2.toString();
            }
        }
        return null;
    }
}

enum Relation{
    LESS,
    LESS_OR_EQUAL,
    EQUAL,
    NOT_EQUAL,
    GREATER_OR_EQUAL,
    GREATER
}