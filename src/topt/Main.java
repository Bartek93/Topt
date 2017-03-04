package topt;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
		stage.setTitle("TOPT Projekt");
		stage.setScene(new Scene(root));
        stage.setResizable(false);
		
		stage.show();
		populateElementToChoose(root);
	}

	private void populateElementToChoose(Parent root) {
		RowData splitter1 = new RowData("Rozdzielacz 1:2", 1, 0.2, 3.5);
		RowData splitter4 = new RowData("Rozdzielacz 1:4", 1, 0.3, 6.7);
		RowData splitter6 = new RowData("Rozdzielacz 1:6", 1, 0.3, 8.7);
		RowData splitter8 = new RowData("Rozdzielacz 1:8", 1, 0.55, 9.8);
		RowData splitter16 = new RowData("Rozdzielacz 1:16", 1, 0.67, 13.1);
		RowData splitter32 = new RowData("Rozdzielacz 1:32", 1, 0.9, 17);
		RowData splitter64 = new RowData("Rozdzielacz 1:64", 1, 1.2, 20.8);
		RowData elementWDM = new RowData("Element WDM", 1, 0.1, 0.5);
		RowData fibre = new RowData("Swiatlowod", 1, 0.1, 0.35);
		RowData separableConnector = new RowData("zlacze rozlaczne", 1, 0.1, 0.4);
		RowData weld = new RowData("spaw ", 1, 0.05, 0.1);

		ComboBox elementList = (ComboBox) root.lookup("#elementsCBox");
		ObservableList<RowData> data = FXCollections.observableArrayList();
		data.addAll(splitter1, splitter4, splitter6, splitter8, splitter16, splitter32, splitter64,
				elementWDM, fibre, separableConnector, weld);
		elementList.setItems(data);

	}

	public static void main(String[] args) {
		launch(args);
	}

}
