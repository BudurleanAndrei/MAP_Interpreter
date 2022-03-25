package Gui;

import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public class SemaphoreTableEntry {
    private SimpleStringProperty index;
    private SimpleStringProperty value;
    private SimpleStringProperty list;

    public SemaphoreTableEntry(int index, int value, ArrayList<Integer> list){
        this.index = new SimpleStringProperty(Integer.toString(index));
        this.value = new SimpleStringProperty(Integer.toString(value));
        this.list = new SimpleStringProperty(list.toString());
    }

    public SimpleStringProperty indexProperty(){
        return index;
    }
    public SimpleStringProperty valueProperty(){
        return value;
    }
    public SimpleStringProperty listProperty(){
        return list;
    }

    public String getIndex(){
        return index.get();
    }
    public String getValue(){
        return value.get();
    }
    public String getList(){
        return list.get();
    }

    public void setIndex(Integer index){
        this.index = new SimpleStringProperty(Integer.toString(index));
    }
    public void setValue(Integer value){
        this.value = new SimpleStringProperty(Integer.toString(value));
    }
    public void setList(ArrayList<Integer> list){
        this.list = new SimpleStringProperty(list.toString());
    }
}
