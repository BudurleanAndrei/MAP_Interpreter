package Gui;

import Model.Values.IValue;
import javafx.beans.property.SimpleStringProperty;

public class HeapEntry {
    private SimpleStringProperty heapAddress;
    private SimpleStringProperty heapValue;

    public HeapEntry(Integer heapAddress, IValue heapValue){
        this.heapAddress = new SimpleStringProperty(Integer.toString(heapAddress));
        this.heapValue = new SimpleStringProperty(heapValue.toString());
    }

    public SimpleStringProperty heapAddressProperty(){
        return heapAddress;
    }

    public SimpleStringProperty heapValueProperty(){
        return heapValue;
    }

    public String getHeapAddress(){
        return heapAddress.get();
    }

    public String getHeapValue(){
        return heapValue.get();
    }

    public void setHeapAddress(Integer heapAddress){
        this.heapAddress.set(String.valueOf(heapAddress));
    }

    public void setHeapValue(IValue heapValue){
        this.heapValue.set(heapValue.toString());
    }
}
