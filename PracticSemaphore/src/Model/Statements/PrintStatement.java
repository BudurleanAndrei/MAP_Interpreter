package Model.Statements;

import Model.Expressions.IExpression;
import Model.ProgramState;
import Model.Types.Type;
import Model.Utilities.ICustomDictionary;
import Model.Utilities.MyException;

public class PrintStatement implements IStatement{
    private IExpression expression;

    public PrintStatement(IExpression expression){
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws MyException {
        programState.getOutput().add(expression.eval(programState.getSymbolTable(), programState.getHeap()));
        return null;
    }

    @Override
    public ICustomDictionary<String, Type> typeCheck(ICustomDictionary<String, Type> typeEnv) throws MyException {
        expression.typeCheck(typeEnv);
        return typeEnv;
    }

    @Override
    public String toString(){
        return "print(" + expression.toString() + ")";
    }
}
