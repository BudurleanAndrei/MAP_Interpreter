package Model;

import Model.Statements.*;
import Model.Utilities.*;
import Model.Values.IValue;
import Model.Values.StringValue;

import java.io.BufferedReader;
import java.io.BufferedWriter;

public class ProgramState {
    private ICustomStack<IStatement> executionStack;
    private ICustomDictionary<String, IValue> symbolTable;
    private ICustomList<IValue> output;
    private ICustomDictionary<StringValue, BufferedReader> fileTable;
    private IHeap<Integer, IValue> heap;
    private IStatement initialProgram;
    private int id;
    private static int childId = 0;
    private ILockTable lockTable;

    public static synchronized int generateChildId(){
        return ++childId;
    }

    public ProgramState(ICustomStack<IStatement> executionStack, ICustomDictionary<String, IValue> symbolTable,
                        ICustomList<IValue> output, ICustomDictionary<StringValue, BufferedReader> fileTable,
                        IHeap<Integer, IValue> heap, IStatement initialProgram, int id,
                        ILockTable lockTable){
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.output = output;
        this.fileTable = fileTable;
        this.heap = heap;
        this.executionStack.push(initialProgram);
        this.id = id;
        this.lockTable = lockTable;
    }

    public ICustomStack<IStatement>getExecutionStack(){
        return executionStack;
    }

    public void setExecutionStack(ICustomStack<IStatement> executionStack) {
        this.executionStack = executionStack;
    }

    public ICustomDictionary<String, IValue> getSymbolTable(){
        return symbolTable;
    }

    public void setSymbolTable(ICustomDictionary<String, IValue> symbolTable){
        this.symbolTable = symbolTable;
    }

    public ICustomList<IValue> getOutput(){
        return output;
    }

    public void setOutput(ICustomList<IValue> output){
        this.output = output;
    }

    public ICustomDictionary<StringValue, BufferedReader> getFileTable(){
        return fileTable;
    }

    public void setFileTable(ICustomDictionary<StringValue, BufferedReader> fileTable){
        this.fileTable = fileTable;
    }

    public IHeap<Integer, IValue> getHeap(){
        return heap;
    }

    public void setHeap(IHeap<Integer, IValue> heap){
        this.heap = heap;
    }

    public ILockTable getLockTable(){
        return lockTable;
    }

    public void setLockTable(ILockTable lockTable){
        this.lockTable = lockTable;
    }

    public Boolean isNotCompleted(){
        return !executionStack.isEmpty();
    }

    public ProgramState oneStep() throws MyException{
        if (executionStack.isEmpty())
            throw new MyException("Execution stack is empty!");
        IStatement statement = executionStack.pop();
        return statement.execute(this);
    }

    public int getId(){
        return id;
    }

    public String toString() {
        return "Id: " + id +
                "\nExecution Stack: " + executionStack.toString() +
                 "\nSymbol Table: " + symbolTable.toString() +
                  "\nOutput: " + output.toString() +
                   "\nFile Table: " + fileTable.toString() +
                    "\nHeap: " + heap.toString() +
                     "\n";
    }
}
