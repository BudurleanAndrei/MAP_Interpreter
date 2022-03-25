package Model.Statements;

import Model.Expressions.IExpression;
import Model.ProgramState;
import Model.Types.BoolType;
import Model.Types.Type;
import Model.Utilities.ICustomDictionary;
import Model.Utilities.MyException;
import Model.Values.BoolValue;
import Model.Values.IValue;

public class IfStatement implements IStatement{
    IExpression condition;
    IStatement trueStatement;
    IStatement falseStatement;

    public IfStatement(IExpression condition, IStatement trueStatement, IStatement falseStatement){
        this.condition = condition;
        this.trueStatement = trueStatement;
        this.falseStatement = falseStatement;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws MyException {
        IValue value = condition.eval(programState.getSymbolTable(), programState.getHeap());

        if (!value.getType().equals(new BoolType()))
            throw new MyException("Invalid condition type!");

        BoolValue result = (BoolValue)value;
        if (result.getValue())
            programState.getExecutionStack().push(trueStatement);
        else
            programState.getExecutionStack().push(falseStatement);

        return null;
    }

    @Override
    public ICustomDictionary<String, Type> typeCheck(ICustomDictionary<String, Type> typeEnv) throws MyException {
        Type typeCond = condition.typeCheck(typeEnv);
        if (typeCond.equals(new BoolType())){
            trueStatement.typeCheck(typeEnv.copy());
            falseStatement.typeCheck(typeEnv.copy());
            return typeEnv;
        }
        throw new MyException("Condition must evaluate to bool type!");
    }

    @Override
    public String toString(){
        return "(if(" + condition.toString() + ")then(" + trueStatement.toString() + ")else(" +
                falseStatement.toString() + "))";
    }
}
