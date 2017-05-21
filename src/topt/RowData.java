package topt;

import javafx.beans.property.*;

public class RowData {
	
    private StringProperty name;
    private IntegerProperty nrOfElements;
    private DoubleProperty standardDev;
    private DoubleProperty expectedValue;

    private StringProperty nameProperty() {
        if (name == null) name = new SimpleStringProperty(this, "name");
        return name;
    }
    
    private IntegerProperty nrOfElementsProperty() {
        if (nrOfElements == null) nrOfElements = new SimpleIntegerProperty(this, "nrOfElements");
        return nrOfElements;
    }
    
    private DoubleProperty standardDevProperty() {
        if (standardDev == null) standardDev = new SimpleDoubleProperty(this, "standardDev");
        return standardDev ;
    }
    
    private DoubleProperty expectedValueProperty() {
        if (expectedValue == null) expectedValue = new SimpleDoubleProperty(this, "expectedValue");
        return expectedValue;
    }
    
    public RowData(String name,int nrOfElements,double standardDev,double expectedValue) {
       nameProperty().setValue(name);
       nrOfElementsProperty().setValue(nrOfElements);
       standardDevProperty().setValue(standardDev);
       expectedValueProperty().setValue(expectedValue);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getNrOfElements() {
        return nrOfElements.get();
    }

    public void setNrOfElements(int nrOfElements) {
        this.nrOfElements.set(nrOfElements);
    }

    public double getStandardDev() {
        return standardDev.get();
    }

    public void setStandardDev(double standardDev) {
        this.standardDev.set(standardDev);
    }

    public double getExpectedValue() {
        return expectedValue.get();
    }

    public void setExpectedValue(double expectedValue) {
        this.expectedValue.set(expectedValue);
    }
    public String toString(){
        return name.getValue();


    }
}
