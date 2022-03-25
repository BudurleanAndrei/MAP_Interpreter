package Gui;

import Model.ProgramState;
import Model.Statements.IStatement;
import Model.Utilities.*;
import Model.Values.IValue;
import Model.Values.StringValue;
import View.RunExample;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.StringConverter;

import java.io.BufferedReader;
import java.util.List;
import java.util.concurrent.locks.Lock;

public class MainWindow{
    public TableView<LockTableEntry> tableViewLockTable;
    public TableColumn<LockTableEntry, String> lockTableIndex;
    public TableColumn<LockTableEntry, String> lockTableValue;
    @FXML
    private TableView<HeapEntry> tableViewHeapTable;
    @FXML
    private TableColumn<HeapEntry, String> heapAddress;
    @FXML
    private TableColumn<HeapEntry, String> heapValue;

    @FXML
    private ListView<IValue> listViewOut;
    @FXML
    private ListView<StringValue> listViewFileTable;
    @FXML
    private ListView<ProgramState> listViewProgramStates;

    @FXML
    private TableView<SymbolTableEntry> tableViewSymbolTable;
    @FXML
    private TableColumn<SymbolTableEntry, String> symbolTableVariable;
    @FXML
    private TableColumn<SymbolTableEntry, String> symbolTableValue;

    @FXML
    private ListView<IStatement> listViewExecutionStack;
    @FXML
    private TextField textFieldProgramStates;
    @FXML
    private Button buttonOneStep;

    private SelectWindow selectWindow;
    private ProgramState programState;

    public void setSelectWindow(SelectWindow selectWindow){
        this.selectWindow = selectWindow;
        this.selectWindow.getListViewExample().getSelectionModel().selectedItemProperty().addListener(
                (a, b, ex)->this.showDataForSelectedExample(ex));
    }

    private void showDataForSelectedExample(RunExample example) {
        tableViewHeapTable.getItems().clear();
        listViewOut.getItems().clear();
        listViewFileTable.getItems().clear();
        listViewProgramStates.getItems().clear();
        tableViewSymbolTable.getItems().clear();
        listViewExecutionStack.getItems().clear();
        tableViewLockTable.getItems().clear();

        List<ProgramState> programStates = example.getController().getRepository().getPrograms();

        if (programStates.size() != 0)
            programState = programStates.get(0);

        IHeap<Integer, IValue> heap = programState.getHeap();
        heap.getContent().forEach((address, value) -> tableViewHeapTable.getItems().add(new HeapEntry(address, value)));
        programState.getLockTable().getContent().forEach((index, value) ->
                tableViewLockTable.getItems().add(new LockTableEntry(index, value)));

        ICustomDictionary<StringValue, BufferedReader> fileTable = programState.getFileTable();
        fileTable.getContent().forEach((fileName, filePath) -> listViewFileTable.getItems().add(fileName));

        ICustomList<IValue> output = programState.getOutput();
        output.getContent().forEach((value) -> listViewOut.getItems().add(value));

        programStates.forEach((progState) -> listViewProgramStates.getItems().add(progState));

        this.textFieldProgramStates.setText("Number of program states: " + programStates.size());

        if (programStates.size() > 0)
            showDataForSelectedProgram(programStates.get(0));
    }

    private void showDataForSelectedProgram(ProgramState programState){
        this.tableViewSymbolTable.getItems().clear();
        this.listViewExecutionStack.getItems().clear();

        programState.getSymbolTable().getContent().forEach((key, value)->
                this.tableViewSymbolTable.getItems().add(new SymbolTableEntry(key, value)));
        programState.getExecutionStack().getContent().forEach((statement)->
                this.listViewExecutionStack.getItems().add(statement));
    }

    private void ExecuteOneStep(RunExample example) throws MyException, InterruptedException {
        try{
            example.getController().oneStepForAll();
        }
        catch (Exception exception){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(exception.toString());
            alert.show();
        }
        showDataForSelectedExample(example);
    }

    @FXML
    private void initialize(){
        heapAddress.setCellValueFactory(new PropertyValueFactory<HeapEntry, String>("heapAddress"));
        heapValue.setCellValueFactory(new PropertyValueFactory<HeapEntry, String>("heapValue"));

        symbolTableVariable.setCellValueFactory(new PropertyValueFactory<SymbolTableEntry, String>("variableName"));
        symbolTableValue.setCellValueFactory(new PropertyValueFactory<SymbolTableEntry, String>("value"));

        lockTableIndex.setCellValueFactory(new PropertyValueFactory<LockTableEntry, String>("lockIndex"));
        lockTableValue.setCellValueFactory(new PropertyValueFactory<LockTableEntry, String>("lockValue"));

        listViewOut.setCellFactory(TextFieldListCell.forListView(new StringConverter<IValue>() {
            @Override
            public String toString(IValue iValue) {
                return iValue.toString();
            }

            @Override
            public IValue fromString(String s) {
                return null;
            }
        }));

        listViewFileTable.setCellFactory(TextFieldListCell.forListView(new StringConverter<StringValue>() {
            @Override
            public String toString(StringValue stringValue) {
                return stringValue.toString();
            }

            @Override
            public StringValue fromString(String s) {
                return null;
            }
        }));

        listViewProgramStates.setCellFactory(TextFieldListCell.forListView(new StringConverter<ProgramState>() {
            @Override
            public String toString(ProgramState programState) {
                return Integer.toString(programState.getId());
            }

            @Override
            public ProgramState fromString(String s) {
                return null;
            }
        }));

        listViewExecutionStack.setCellFactory(TextFieldListCell.forListView(new StringConverter<IStatement>() {
            @Override
            public String toString(IStatement statement) {
                return statement.toString();
            }

            @Override
            public IStatement fromString(String s) {
                return null;
            }
        }));

        listViewProgramStates.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listViewProgramStates.getSelectionModel().selectedItemProperty().addListener((a, b, progState)->
        {
            if (progState != null)
                showDataForSelectedProgram(progState);
        });

        buttonOneStep.setOnAction(actionEvent -> {
            try {
                ExecuteOneStep(selectWindow.getListViewExample().getSelectionModel().getSelectedItems().get(0));
            } catch (Exception e) {
                //e.printStackTrace();
            }
        });
    }
}
