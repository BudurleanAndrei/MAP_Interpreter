package Controller;

import Model.ProgramState;
import Model.Statements.IStatement;
import Model.Utilities.MyException;
import Model.Values.IValue;
import Model.Values.ReferenceValue;
import Repository.IRepository;
import Repository.Repository;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    private IRepository repository;
    private ExecutorService executor;
    private boolean print;

    public Controller(IRepository repository){
        this.repository = repository;
        this.print = true;
    }

    public void addProgram(ProgramState programState){
        repository.addProgramState(programState);
    }

    public void allSteps() throws MyException, InterruptedException {
        executor = Executors.newFixedThreadPool(2);

        repository.clearLogFile();

        List<ProgramState> programs = removeCompletedPrograms(repository.getPrograms());

        programs.forEach(program -> {
            try{
                repository.logProgramState(program);
            }
            catch(MyException ex){
                ex.printStackTrace();
            }
        });

        while(programs.size() > 0){
            List<IValue> adresses = programs.stream()
                            .flatMap(program -> program.getSymbolTable().getContent().values().stream())
                            .collect(Collectors.toList());
            programs.get(0).getHeap().setContent(safeGarbageCollector(getAddresses(adresses),
                    getUsedHeapAddresses(programs.get(0).getHeap().getContent()),
                    programs.get(0).getHeap().getContent()));

            oneStepForAllPrograms(programs);
            programs = removeCompletedPrograms(programs);
        }
        executor.shutdownNow();

        repository.setProgramsList(programs);
    }

    public void oneStepForAllPrograms(List<ProgramState> programs) throws InterruptedException {

        List<Callable<ProgramState>> callList = programs.stream()
                .map((ProgramState p) -> (Callable<ProgramState>)(p::oneStep))
                .collect(Collectors.toList());

        List<ProgramState> newProgramList = executor.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    }
                    catch(ExecutionException | InterruptedException ex){
                        System.out.println(ex.toString());
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        programs.addAll(newProgramList);

        programs.forEach(program -> {
            try{
                repository.logProgramState(program);
            }
            catch(MyException ex){
                ex.printStackTrace();
            }
        });

        repository.setProgramsList(programs);
    }

    private Map<Integer, IValue> safeGarbageCollector(List<Integer> addresses, List<Integer> heapAddresses, Map<Integer, IValue> heap) {
        return heap.entrySet().stream().
                filter(e->addresses.contains(e.getKey()) || heapAddresses.contains(e.getKey())).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private List<Integer> getAddresses(Collection<IValue> symTableValues) {
        return symTableValues.stream()
                .filter(v-> v instanceof ReferenceValue)
                .map(v-> {
                    ReferenceValue v1 = (ReferenceValue)v; return v1.getAddress();
                })
                .collect(Collectors.toList());
    }

    private List<Integer> getUsedHeapAddresses(Map<Integer, IValue> heap) {
        return heap.values().stream().
                filter(value -> value instanceof ReferenceValue).
                map(iValue -> {
                    ReferenceValue val = (ReferenceValue) iValue;
                    return val.getAddress();
                }).collect(Collectors.toList());
    }

    public List<ProgramState> removeCompletedPrograms(List<ProgramState> programs){
        return programs.stream()
                .filter(ProgramState::isNotCompleted)
                .collect(Collectors.toList());
    }

    public IRepository getRepository(){
        return repository;
    }

    public void oneStepForAll() throws MyException, InterruptedException {
        if (repository.getPrograms().size() == 0)
            throw new MyException("Execution has finished!");

        executor = Executors.newFixedThreadPool(3);

        List<ProgramState> programs = removeCompletedPrograms(repository.getPrograms());

        programs.forEach(program -> {
            try{
                repository.logProgramState(program);
            }
            catch(MyException ex){
                ex.printStackTrace();
            }
        });

        if(programs.size() > 0){
            List<IValue> adresses = programs.stream()
                    .flatMap(program -> program.getSymbolTable().getContent().values().stream())
                    .collect(Collectors.toList());
            programs.get(0).getHeap().setContent(safeGarbageCollector(getAddresses(adresses),
                    getUsedHeapAddresses(programs.get(0).getHeap().getContent()),
                    programs.get(0).getHeap().getContent()));

            oneStepForAllPrograms(programs);
            programs = removeCompletedPrograms(programs);
        }
        executor.shutdownNow();

        repository.setProgramsList(programs);
    }
}
