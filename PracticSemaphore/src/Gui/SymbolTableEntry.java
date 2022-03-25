package Gui;

import Model.Values.IValue;
import javafx.beans.property.SimpleStringProperty;

public class SymbolTableEntry {
    private SimpleStringProperty variableName;
    private SimpleStringProperty value;

    public SymbolTableEntry(String variableName, IValue value){
        this.variableName = new SimpleStringProperty(variableName);
        this.value = new SimpleStringProperty(value.toString());
    }

    public SimpleStringProperty variableNameProperty(){
        return variableName;
    }

    public SimpleStringProperty valueProperty(){
        return value;
    }

    public String getVariableName(){
        return variableName.get();
    }

    public String getValue(){
        return value.get();
    }

    public void setVariableName(String variableName){
        this.variableName.set(variableName);
    }

    public void setValue(String value){
        this.value.set(value);
    }

}
