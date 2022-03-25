package View;

import Controller.Controller;
import Model.Utilities.MyException;

public class RunExample extends Command {
    private Controller controller;

    public RunExample(String key, String desc, Controller controller){
        super(key, desc);
        this.controller=controller;
    }
    @Override
    public void execute(){
        try{
            controller.allSteps();
        }
        catch (MyException | InterruptedException exception) {
            System.out.println(exception);
        }
    }

    public Controller getController(){
        return controller;
    }
}
