package Model.Statements;

import Model.Expressions.IExpression;
import Model.Expressions.LogicExpression;
import Model.Expressions.RelationalExpression;
import Model.Expressions.VariableExpression;
import Model.ProgramState;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Utilities.ICustomDictionary;
import Model.Utilities.MyException;

public class ForStatement implements IStatement {
    public String var;
    public IExpression initialization;
    public IExpression condition;
    public IExpression assignment;
    public IStatement content;

    public ForStatement(String var, IExpression initialization, IExpression condition, IExpression assignment, IStatement content){
        this.var = var;
        this.initialization = initialization;
        this.condition = condition;
        this.assignment = assignment;
        this.content = content;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws MyException {
        IStatement result = new CompoundStatement(new DeclareStatement(var, new IntType()),
                new CompoundStatement(new AssignStatement(var, initialization),
                        new WhileStatement(new RelationalExpression(new VariableExpression(var), condition, "<"),
                                new CompoundStatement(content, new AssignStatement(var, assignment)))));

        programState.getExecutionStack().push(result);

        return null;
    }

    @Override
    public ICustomDictionary<String, Type> typeCheck(ICustomDictionary<String, Type> typeEnv) throws MyException {
        if (!initialization.typeCheck(typeEnv).equals(new IntType()))
            throw new MyException("Expression must evaluate to type int!");
        if (!condition.typeCheck(typeEnv).equals(new IntType()))
            throw new MyException("Expression must evaluate to type int!");
        if (!assignment.typeCheck(typeEnv).equals(new IntType()))
            throw new MyException("Expression must evaluate to type int!");
        if (!typeEnv.get(var).equals(new IntType()))
            throw new MyException("Variable must be of type int!");

        return typeEnv;
    }

    @Override
    public String toString(){
        return "for(" + initialization.toString() + ";" + condition.toString() + ";" + assignment.toString() + ") " +
                content.toString();
    }
}
