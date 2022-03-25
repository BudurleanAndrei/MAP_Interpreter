package Model.Statements;

import Model.Expressions.IExpression;
import Model.ProgramState;
import Model.Types.BoolType;
import Model.Types.Type;
import Model.Utilities.ICustomDictionary;
import Model.Utilities.MyException;
import Model.Values.BoolValue;
import Model.Values.IValue;

public class WhileStatement implements IStatement{
    private IExpression condition;
    private IStatement statement;

    public WhileStatement(IExpression condition, IStatement statement){
        this.condition = condition;
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws MyException {
        IValue value = condition.eval(programState.getSymbolTable(), programState.getHeap());

        if (!value.getType().equals(new BoolType()))
            throw new MyException("Expression must evaluate to bool!");

        BoolValue boolValue = (BoolValue)value;

        if (boolValue.getValue()){
            programState.getExecutionStack().push(new WhileStatement(condition, statement));
            programState.getExecutionStack().push(statement);
        }

        return null;
    }

    @Override
    public ICustomDictionary<String, Type> typeCheck(ICustomDictionary<String, Type> typeEnv) throws MyException {
        Type typeCond = condition.typeCheck(typeEnv);
        if (typeCond.equals(new BoolType())){
            statement.typeCheck(typeEnv.copy());
            return typeEnv;
        }
        throw new MyException("Condition must evaluate to bool type!");
    }

    @Override
    public String toString(){
        return "while(" + condition.toString() + "):(" + statement.toString() + ")";
    }
}
