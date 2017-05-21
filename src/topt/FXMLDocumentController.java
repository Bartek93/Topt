/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package topt;

import java.net.URL;
import java.util.ResourceBundle;

import org.apache.commons.math3.distribution.NormalDistribution;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author Bartolio
 */
public class FXMLDocumentController implements Initializable {
	
	@FXML private TextField elementsNr;
    @FXML private TextField standardDev;
    @FXML private TextField expectedValue;
    @FXML private TextField tfError;
    @FXML private TextArea result;
	
	@FXML 
	private ComboBox<RowData> elementsCBox;
	@FXML private TableView<RowData> addedElementList;
    ObservableList<RowData> data = FXCollections.observableArrayList();
	
	@FXML
	private void handleComboBoxOnChanged(ActionEvent event) {
		System.out.println("Combobox");
		elementsNr.setText(Integer.toString(elementsCBox.getValue().getNrOfElements()));
//        if(elementsCBox.getValue().getName().startsWith("Świat")){
//            elementNrLabel.setText("Długość światłowodu [km]");
//        }else{
//            elementNrLabel.setText("Ilość elementów ");
//        }
        standardDev.setText(Double.toString(elementsCBox.getValue().getStandardDev()));
        expectedValue.setText(Double.toString(elementsCBox.getValue().getExpectedValue()));
	}
	
	@FXML
	private void handleButtonRemoveLastElement(ActionEvent event) {
		data.remove(data.size()-1);
		addedElementList.refresh();
	}
	
	@FXML
	private void handleButtonClearElementList(ActionEvent event) {
		data.clear();
        addedElementList.setItems(data);
	}
	
	@FXML
	private void handleButtonAddElement(ActionEvent event) {
		try {
            if(elementsCBox.getValue() != null &&!elementsCBox.getValue().toString().isEmpty()) {
                int numberOfEl = Integer.parseInt(elementsNr.getText());
                double standardDevDouble = Double.parseDouble(standardDev.getText());
                double expectedValueDouble = Double.parseDouble(expectedValue.getText());


                data.add(data.size(), new RowData(elementsCBox.getValue().toString(), numberOfEl, standardDevDouble
                        , expectedValueDouble));

                addedElementList.setItems(data);
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR, "Wybierz element", ButtonType.YES);
                alert.showAndWait();
            }
        }catch (NumberFormatException nfe){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Niepoprawny format danych (liczb)", ButtonType.YES);
            alert.showAndWait();
        }
	}
	
	@FXML
	private void handleButtonCompute(ActionEvent event) {
		System.out.println("Compute!");
		double sum = 0;
        double sumInSquare = 0;
        double expectedError = 0;
        for(RowData element : addedElementList.getItems()){
            sum += element.getExpectedValue() * element.getNrOfElements();
            sumInSquare += Math.pow(element.getStandardDev(),2) * element.getNrOfElements();
        }
        
        if(!tfError.getText().isEmpty())
        	expectedError = Double.parseDouble(tfError.getText())/100;
        else {
        	Alert alert = new Alert(Alert.AlertType.ERROR, "Proszę podać wartość dopuszczalnego błędu [%]", ButtonType.YES);
        	alert.showAndWait();
        	return;
        }

        double nrOfStDev = computeNrStDev(sum,Math.sqrt(sumInSquare),expectedError);
        double attenuationMax = sum + nrOfStDev*Math.sqrt(sumInSquare);
        double attenuationMin = sum - nrOfStDev*Math.sqrt(sumInSquare);
        attenuationMax = roundResult(attenuationMax);
        attenuationMin = roundResult(attenuationMin);
        
        result.setText("Zakres tłumienia toru optycznego mieści się w zakresie od: " +attenuationMin + "[dB] do "+ attenuationMax+"[dB]");

	}
	
	public double computeNrStDev(double mean, double stDev, double expectedError){
        System.out.println(mean+" "+stDev);

        NormalDistribution normalDistribution = new NormalDistribution(mean, stDev);
        double nrOfStDev = (mean - normalDistribution.inverseCumulativeProbability(expectedError/2))/stDev;
        System.out.println(normalDistribution.inverseCumulativeProbability(expectedError/2));
        System.out.println(nrOfStDev);
        nrOfStDev = roundResult(nrOfStDev);
//        this.nrOfStDev.setText("Prawdopodobienśtwo błędu wynosi " + Double.toString(nrOfStDev)+"%");
//        this.nrOfStDevLabel.setText("Liczba ochyleń standardowych przy danym błędzie : " + Double.toString(nrOfStDev));
        return nrOfStDev;
    }
	
	private double roundResult(double result){
        //zaokraglanie
        result *=100; //mnozenie razy 100

        result = Math.round(result);
        result /=100; //dzielenie przez 100
        return result;
    }
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}	
	
}
