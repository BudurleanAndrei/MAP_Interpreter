package Gui;

import Model.Values.IValue;
import javafx.beans.property.SimpleStringProperty;

public class LockTableEntry {
    private SimpleStringProperty lockIndex;
    private SimpleStringProperty lockValue;

    public LockTableEntry(Integer lockIndex, Integer lockValue){
        this.lockIndex = new SimpleStringProperty(Integer.toString(lockIndex));
        this.lockValue = new SimpleStringProperty(Integer.toString(lockValue));
    }

    public SimpleStringProperty lockIndexProperty(){
        return lockIndex;
    }

    public SimpleStringProperty lockValueProperty(){
        return lockValue;
    }

    public String getLockIndex(){
        return lockIndex.get();
    }

    public String getLockValue(){
        return lockValue.get();
    }

    public void setLockIndex(Integer lockIndex){
        this.lockIndex.set(String.valueOf(lockIndex));
    }

    public void setLockValue(IValue lockValue){
        this.lockValue.set(String.valueOf(lockValue));
    }
}
