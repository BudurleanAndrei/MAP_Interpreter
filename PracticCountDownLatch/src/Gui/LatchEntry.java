package Gui;

import Model.Values.IValue;
import javafx.beans.property.SimpleStringProperty;

public class LatchEntry {
    private SimpleStringProperty latchLatch;
    private SimpleStringProperty latchCountdown;

    public LatchEntry(Integer latchLatch, Integer latchCountdown){
        this.latchLatch = new SimpleStringProperty(Integer.toString(latchLatch));
        this.latchCountdown = new SimpleStringProperty(latchCountdown.toString());
    }

    public SimpleStringProperty latchLatchProperty(){
        return latchLatch;
    }

    public SimpleStringProperty latchCountdownProperty(){
        return latchCountdown;
    }

    public String getLatchLatch(){
        return latchLatch.get();
    }

    public String getLatchCountdown(){
        return latchCountdown.get();
    }

    public void setLatchLatch(Integer latchLatch){
        this.latchLatch.set(String.valueOf(latchLatch));
    }

    public void setLatchCountdown(Integer latchCountdown){
        this.latchCountdown.set(String.valueOf(latchCountdown));
    }
}
