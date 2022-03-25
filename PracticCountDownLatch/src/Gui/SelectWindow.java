package Gui;

import Controller.Controller;
import Model.Expressions.*;
import Model.ProgramState;
import Model.Statements.*;
import Model.Types.*;
import Model.Utilities.*;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Repository.IRepository;
import Repository.Repository;
import View.RunExample;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.StringConverter;

public class SelectWindow {

    @FXML
    public ListView<RunExample> listViewExamples;

    public ListView<RunExample> getListViewExample(){
        return listViewExamples;
    }

    @FXML
    public void initialize() throws Exception{
        // -- 1 --
        IStatement statement1 = new CompoundStatement(new DeclareStatement("v", new IntType()),
                new CompoundStatement(new AssignStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));
        ProgramState programState1 = new ProgramState(new CustomStack<>(), new CustomDictionary<>(), new CustomList<>(),
                new CustomDictionary<>(), new Heap<>(), statement1, ProgramState.generateChildId(),
                new LatchTable<Integer, Integer>());
        IRepository repository1 = new Repository(programState1, "log1.txt");
        Controller controller1 = new Controller(repository1);

        // -- 2 --
        IStatement statement2 = new CompoundStatement(new DeclareStatement("a", new IntType()),
                new CompoundStatement(new DeclareStatement("b", new IntType()),
                        new CompoundStatement(new AssignStatement("a",
                                new ArithmeticExpression(new ValueExpression(new IntValue(2)),
                                        new ArithmeticExpression(new ValueExpression(new IntValue(3)),
                                                new ValueExpression(new IntValue(5)), 3), 1)),
                                new CompoundStatement(new AssignStatement("b",
                                        new ArithmeticExpression(new VariableExpression("a"),
                                                new ValueExpression(new IntValue(1)), 1)),
                                        new PrintStatement(new VariableExpression("b"))))));

        ProgramState programState2 = new ProgramState(new CustomStack<>(), new CustomDictionary<>(),
                new CustomList<>(), new CustomDictionary<>(), new Heap<>(), statement2, ProgramState.generateChildId(),
                new LatchTable<Integer, Integer>());
        IRepository repository2 = new Repository(programState2, "log2.txt");
        Controller controller2 = new Controller(repository2);

        // -- 3 --
        IStatement statement3 = new CompoundStatement(new DeclareStatement("a", new BoolType()),
                new CompoundStatement(new DeclareStatement("v", new IntType()),
                        new CompoundStatement(new AssignStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(new IfStatement( new VariableExpression("a"),
                                        new AssignStatement("v", new ValueExpression(new IntValue(2))),
                                        new AssignStatement("v", new ValueExpression(new IntValue(3)))),
                                        new PrintStatement(new VariableExpression("v"))))));

        ProgramState programState3 = new ProgramState(new CustomStack<>(), new CustomDictionary<>(),
                new CustomList<>(), new CustomDictionary<>(), new Heap<>(), statement3, ProgramState.generateChildId(),
                new LatchTable<Integer, Integer>());
        IRepository repository3 = new Repository(programState3, "log3.txt");
        Controller controller3 = new Controller(repository3);

        // -- 4 --
        IStatement statement4 = new CompoundStatement(new DeclareStatement("varf", new StringType()),
                new CompoundStatement(new AssignStatement("varf", new ValueExpression(new StringValue("test.in"))),
                        new CompoundStatement(new FileOpenStatement(new VariableExpression("varf")),
                                new CompoundStatement(new DeclareStatement("varc", new IntType()),
                                        new CompoundStatement(new FileReadStatement(
                                                new VariableExpression("varf"), "varc"),
                                                new CompoundStatement(new PrintStatement(
                                                        new VariableExpression("varc")),
                                                        new CompoundStatement(new FileReadStatement(
                                                                new VariableExpression("varf"), "varc"),
                                                                new CompoundStatement(new PrintStatement(
                                                                        new VariableExpression("varc")),
                                                                        new FileCloseStatement(
                                                                                new VariableExpression("varf"))
                                                                ))))))));

        ProgramState programState4 = new ProgramState(new CustomStack<>(), new CustomDictionary<>(),
                new CustomList<>(), new CustomDictionary<>(), new Heap<>(), statement4, ProgramState.generateChildId(),
                new LatchTable<Integer, Integer>());
        IRepository repository4 = new Repository(programState4, "log4.txt");
        Controller controller4 = new Controller(repository4);

        // -- 5 --
        IStatement statement5 = new CompoundStatement(new DeclareStatement("a", new IntType()),
                new CompoundStatement(new DeclareStatement("b", new IntType()),
                        new CompoundStatement(new AssignStatement("a", new ValueExpression(new IntValue(5))),
                                new CompoundStatement(new AssignStatement("b", new ValueExpression(new IntValue(4))),
                                        new IfStatement(new RelationalExpression(new VariableExpression("a"),
                                                new VariableExpression("b"), ">"),
                                                new PrintStatement(new VariableExpression("a")),
                                                new PrintStatement(new VariableExpression("b")))))));
        ProgramState programState5 = new ProgramState(new CustomStack<>(), new CustomDictionary<>(),
                new CustomList<>(), new CustomDictionary<>(), new Heap<>(), statement5, ProgramState.generateChildId(),
                new LatchTable<Integer, Integer>());
        IRepository repository5 = new Repository(programState5, "log5.txt");
        Controller controller5 = new Controller(repository5);

        // -- 6 --
        IStatement statement6 = new CompoundStatement(new DeclareStatement("varf", new StringType()),
                new CompoundStatement(new AssignStatement("varf", new ValueExpression(new StringValue("test.in"))),
                        new CompoundStatement(new FileOpenStatement(new VariableExpression("varf")),
                                new CompoundStatement(new FileCloseStatement(new VariableExpression("varf")),
                                        new FileCloseStatement(new VariableExpression("varf"))))));

        ProgramState programState6 = new ProgramState(new CustomStack<>(), new CustomDictionary<>(),
                new CustomList<>(), new CustomDictionary<>(), new Heap<>(), statement6, ProgramState.generateChildId(),
                new LatchTable<Integer, Integer>());
        IRepository repository6 = new Repository(programState6, "log6.txt");
        Controller controller6 = new Controller(repository6);

        // -- 7 --
        IStatement statement7 = new CompoundStatement(new DeclareStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new DeclareStatement("a",
                                new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(new NewStatement("a",
                                        new VariableExpression("v")),
                                        new CompoundStatement(new PrintStatement
                                                (new VariableExpression("v")),
                                                new PrintStatement(new VariableExpression("a")))))));

        ProgramState programState7 = new ProgramState(new CustomStack<>(), new CustomDictionary<>(),
                new CustomList<>(), new CustomDictionary<>(), new Heap<>(), statement7, ProgramState.generateChildId(),
                new LatchTable<Integer, Integer>());
        IRepository repo7 = new Repository(programState7, "log7.txt");
        Controller controller7 = new Controller(repo7);

        // -- 8 --
        IStatement statement8 = new CompoundStatement(
                new DeclareStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(
                        new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(
                                new DeclareStatement("a",
                                        new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(
                                        new NewStatement("a",
                                                new VariableExpression("v")),
                                        new CompoundStatement(
                                                new PrintStatement(new HeapReadExpression(
                                                        new VariableExpression("v"))),
                                                new PrintStatement(
                                                        new ArithmeticExpression(
                                                                new HeapReadExpression(
                                                                        new HeapReadExpression(
                                                                                new VariableExpression(
                                                                                        "a"
                                                                                )
                                                                        )),
                                                                new ValueExpression(new IntValue(5)), 1)
                                                )
                                        )
                                )
                        )
                )
        );

        ProgramState programState8 = new ProgramState(new CustomStack<>(), new CustomDictionary<>(),
                new CustomList<>(), new CustomDictionary<>(), new Heap<>(), statement8, ProgramState.generateChildId(),
                new LatchTable<Integer, Integer>());
        IRepository repo8 = new Repository(programState8, "log8.txt");
        Controller controller8 = new Controller(repo8);

        // -- 9 --
        IStatement statement9 = new CompoundStatement(
                new DeclareStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(
                        new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(
                                new PrintStatement(new HeapReadExpression(new VariableExpression("v"))),
                                new CompoundStatement(
                                        new HeapWriteStatement("v", new ValueExpression(new IntValue(30))),
                                        new PrintStatement(
                                                new ArithmeticExpression(
                                                        new HeapReadExpression(new VariableExpression("v")),
                                                        new ValueExpression(new IntValue(5)), 1
                                                )
                                        )
                                )
                        )
                )
        );

        ProgramState programState9 = new ProgramState(new CustomStack<>(), new CustomDictionary<>(),
                new CustomList<>(), new CustomDictionary<>(), new Heap<>(), statement9, ProgramState.generateChildId(),
                new LatchTable<Integer, Integer>());
        IRepository repo9 = new Repository(programState9, "log9.txt");
        Controller controller9 = new Controller(repo9);

        // -- 10 --
        IStatement statement10 = new CompoundStatement(
                new DeclareStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(
                        new NewStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(
                                new DeclareStatement("a",
                                        new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(
                                        new NewStatement("a",
                                                new VariableExpression("v")),
                                        new CompoundStatement(
                                                new NewStatement("v", new ValueExpression(new IntValue(30))),
                                                new PrintStatement(
                                                        new HeapReadExpression(new HeapReadExpression(
                                                                new VariableExpression("a")
                                                        )))
                                        )
                                )
                        )));

        ProgramState programState10 = new ProgramState(new CustomStack<>(), new CustomDictionary<>(),
                new CustomList<>(), new CustomDictionary<>(), new Heap<>(), statement10, ProgramState.generateChildId(),
                new LatchTable<Integer, Integer>());
        IRepository repo10 = new Repository(programState10, "log10.txt");
        Controller controller10 = new Controller(repo10);

        // -- 11 --
        IStatement statement11 = new CompoundStatement(
                new DeclareStatement("v", new IntType()),
                new CompoundStatement(
                        new AssignStatement("v", new ValueExpression(new IntValue(4))),
                        new CompoundStatement(
                                new WhileStatement(
                                        new RelationalExpression(new VariableExpression("v"),
                                                new ValueExpression(new IntValue(0)), ">"),
                                        new CompoundStatement(
                                                new PrintStatement(new VariableExpression("v")),
                                                new AssignStatement("v", new ArithmeticExpression(
                                                        new VariableExpression("v"),
                                                        new ValueExpression(new IntValue(1)),
                                                        2))
                                        )
                                )
                                , new PrintStatement(new VariableExpression("v")))));

        ProgramState programState11 = new ProgramState(new CustomStack<>(), new CustomDictionary<>(),
                new CustomList<>(), new CustomDictionary<>(), new Heap<>(), statement11, ProgramState.generateChildId(),
                new LatchTable<Integer, Integer>());
        IRepository repo11 = new Repository(programState11, "log11.txt");
        Controller controller11 = new Controller(repo11);

        // -- 12 --
        IStatement statement12 = new CompoundStatement(
                new DeclareStatement("v", new IntType()),
                new CompoundStatement(
                        new DeclareStatement("a", new ReferenceType(new IntType())),
                        new CompoundStatement(
                                new AssignStatement("v", new ValueExpression(new IntValue(10))),
                                new CompoundStatement(
                                        new NewStatement("a", new ValueExpression(new IntValue(22))),
                                        new CompoundStatement(
                                                new ForkStatement(
                                                        new CompoundStatement(new HeapWriteStatement("a",
                                                                new ValueExpression(new IntValue(30))),
                                                                new CompoundStatement(
                                                                        new AssignStatement("v",
                                                                                new ValueExpression(new IntValue(32))),
                                                                        new CompoundStatement(
                                                                                new PrintStatement(
                                                                                        new VariableExpression(
                                                                                                "v"
                                                                                        )),
                                                                                new PrintStatement(
                                                                                        new HeapReadExpression(
                                                                                                new VariableExpression("a")
                                                                                        )
                                                                                )
                                                                        )
                                                                )
                                                        )),
                                                new CompoundStatement(
                                                        new PrintStatement(new VariableExpression("v")),
                                                        new PrintStatement(
                                                                new HeapReadExpression(new VariableExpression("a"))
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
        ProgramState programState12 = new ProgramState(new CustomStack<>(), new CustomDictionary<>(),
                new CustomList<>(), new CustomDictionary<>(), new Heap<>(), statement12, ProgramState.generateChildId(),
                new LatchTable<Integer, Integer>());
        IRepository repo12 = new Repository(programState12, "log12.txt");
        Controller controller12 = new Controller(repo12);

        // -- 13 --
        IStatement statement13 = new CompoundStatement(
                new DeclareStatement("v", new IntType()),
                new CompoundStatement(
                        new DeclareStatement("a", new ReferenceType(new IntType())),
                        new CompoundStatement(
                                new AssignStatement("v", new ValueExpression(new IntValue(10))),
                                new CompoundStatement(
                                        new NewStatement("a", new ValueExpression(new IntValue(22))),
                                        new CompoundStatement(
                                                new ForkStatement(
                                                        new CompoundStatement(
                                                                new ForkStatement(new CompoundStatement(
                                                                        new AssignStatement("v",
                                                                            new ValueExpression(new IntValue(100))),
                                                                        new PrintStatement(new VariableExpression("a")))),
                                                                new CompoundStatement(new HeapWriteStatement("a",
                                                                        new ValueExpression(new IntValue(30))),
                                                                        new CompoundStatement(
                                                                                new AssignStatement("v",
                                                                                        new ValueExpression(new IntValue(32))),
                                                                                new CompoundStatement(
                                                                                        new PrintStatement(
                                                                                                new VariableExpression(
                                                                                                        "v"
                                                                                                )),
                                                                                        new PrintStatement(
                                                                                                new HeapReadExpression(
                                                                                                        new VariableExpression("a")
                                                                                                )
                                                                                        )
                                                                                )
                                                                        )
                                                                ))),
                                                new CompoundStatement(
                                                        new PrintStatement(new VariableExpression("v")),
                                                        new PrintStatement(
                                                                new HeapReadExpression(new VariableExpression("a"))
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
        ProgramState programState13 = new ProgramState(new CustomStack<>(), new CustomDictionary<>(),
                new CustomList<>(), new CustomDictionary<>(), new Heap<>(), statement13, ProgramState.generateChildId(),
                new LatchTable<Integer, Integer>());
        IRepository repo13 = new Repository(programState13, "log13.txt");
        Controller controller13 = new Controller(repo13);

        IStatement declaration_v1_15 = new DeclareStatement("v1", new ReferenceType(new IntType()));
        IStatement declaration_v2_15 = new DeclareStatement("v2", new ReferenceType(new IntType()));
        IStatement declaration_v3_15 = new DeclareStatement("v3", new ReferenceType(new IntType()));
        IStatement declaration_cnt_15 = new DeclareStatement("cnt", new IntType());

        IStatement heapAllocation_v1_15 = new NewStatement("v1", new ValueExpression(new IntValue(2)));
        IStatement heapAllocation_v2_15 = new NewStatement("v2", new ValueExpression(new IntValue(3)));
        IStatement heapAllocation_v3_15 = new NewStatement("v3", new ValueExpression(new IntValue(4)));

        IStatement newLatch_15 = new NewLatchStatement("cnt", new HeapReadExpression(new VariableExpression("v2")));


        IStatement print_v1_15 = new PrintStatement(new HeapReadExpression( new VariableExpression("v1")));
        IStatement fork_v1_15 = new ForkStatement(new CompoundStatement(new HeapWriteStatement("v1", new ArithmeticExpression(new HeapReadExpression(new VariableExpression("v1")), new ValueExpression(new IntValue(10)), 3)), new CompoundStatement(print_v1_15, new CountDownStatement("cnt"))));

        IStatement print_v2_15 = new PrintStatement(new HeapReadExpression(new VariableExpression( "v2")));
        IStatement fork_v2_15 = new ForkStatement(new CompoundStatement(new HeapWriteStatement("v2", new ArithmeticExpression(new HeapReadExpression(new VariableExpression("v2")), new ValueExpression(new IntValue(10)), 3)), new CompoundStatement(print_v2_15, new CountDownStatement("cnt"))));

        IStatement print_v3_15 = new PrintStatement(new HeapReadExpression(new VariableExpression("v3")));
        IStatement fork_v3_15 = new ForkStatement(new CompoundStatement(new HeapWriteStatement("v3", new ArithmeticExpression(new HeapReadExpression(new VariableExpression("v3")), new ValueExpression(new IntValue(10)), 3)), new CompoundStatement(print_v3_15, new CountDownStatement("cnt"))));


        IStatement await_15 = new AwaitStatement("cnt");

        IStatement print_100_15 = new PrintStatement(new ValueExpression(new IntValue(100)));

        IStatement statement14 = new CompoundStatement(declaration_v1_15, new CompoundStatement(declaration_v2_15, new CompoundStatement(declaration_v3_15, new CompoundStatement(declaration_cnt_15,
                new CompoundStatement(heapAllocation_v1_15, new CompoundStatement(heapAllocation_v2_15, new CompoundStatement(heapAllocation_v3_15, new CompoundStatement(newLatch_15, new CompoundStatement(fork_v1_15,
                        new CompoundStatement(fork_v2_15, new CompoundStatement(fork_v3_15, new CompoundStatement(await_15, new CompoundStatement(print_100_15, new CompoundStatement(new CountDownStatement("cnt"), print_100_15))))))))))))));

        ProgramState programState14 = new ProgramState(new CustomStack<>(), new CustomDictionary<>(),
                new CustomList<>(), new CustomDictionary<>(), new Heap<>(), statement14, ProgramState.generateChildId(),
                new LatchTable<Integer, Integer>());
        IRepository repo14 = new Repository(programState14, "log14.txt");
        Controller controller14 = new Controller(repo14);


        statement1.typeCheck(new CustomDictionary<String, Type>());
        statement2.typeCheck(new CustomDictionary<String, Type>());
        statement3.typeCheck(new CustomDictionary<String, Type>());
        statement4.typeCheck(new CustomDictionary<String, Type>());
        statement5.typeCheck(new CustomDictionary<String, Type>());
        statement6.typeCheck(new CustomDictionary<String, Type>());
        statement7.typeCheck(new CustomDictionary<String, Type>());
        statement8.typeCheck(new CustomDictionary<String, Type>());
        statement9.typeCheck(new CustomDictionary<String, Type>());
        statement10.typeCheck(new CustomDictionary<String, Type>());
        statement11.typeCheck(new CustomDictionary<String, Type>());
        statement12.typeCheck(new CustomDictionary<String, Type>());
        statement13.typeCheck(new CustomDictionary<String, Type>());


        this.listViewExamples.setCellFactory(TextFieldListCell.forListView(new StringConverter<RunExample>() {
            @Override
            public String toString(RunExample runExample) {
                return runExample.getKey() + ". " + runExample.getDescription();
            }

            @Override
            public RunExample fromString(String s) {
                return null;
            }
        }));

        this.listViewExamples.getItems().add(new RunExample("1", statement1.toString(), controller1));
        this.listViewExamples.getItems().add(new RunExample("2", statement2.toString(), controller2));
        this.listViewExamples.getItems().add(new RunExample("3", statement3.toString(), controller3));
        this.listViewExamples.getItems().add(new RunExample("4", statement4.toString(), controller4));
        this.listViewExamples.getItems().add(new RunExample("5", statement5.toString(), controller5));
        this.listViewExamples.getItems().add(new RunExample("6", statement6.toString(), controller6));
        this.listViewExamples.getItems().add(new RunExample("7", statement7.toString(), controller7));
        this.listViewExamples.getItems().add(new RunExample("8", statement8.toString(), controller8));
        this.listViewExamples.getItems().add(new RunExample("9", statement9.toString(), controller9));
        this.listViewExamples.getItems().add(new RunExample("10", statement10.toString(), controller10));
        this.listViewExamples.getItems().add(new RunExample("11", statement11.toString(), controller11));
        this.listViewExamples.getItems().add(new RunExample("12", statement12.toString(), controller12));
        this.listViewExamples.getItems().add(new RunExample("13", statement13.toString(), controller13));
        this.listViewExamples.getItems().add(new RunExample("14", statement14.toString(), controller14));

        this.listViewExamples.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }
}
