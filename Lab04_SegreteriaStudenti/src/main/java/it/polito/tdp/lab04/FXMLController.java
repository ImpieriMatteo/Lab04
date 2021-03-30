package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class FXMLController {

	private Model model;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<?> boxLanguage;

    @FXML
    private TextField txtMatricola;

    @FXML
    private Button btnCompletamentoAuto;

    @FXML
    private TextField txtNomeMatr;

    @FXML
    private TextField txtCognomeMatr;

    @FXML
    private TextField txtResult;

    @FXML
    void handleCercaCorsi(ActionEvent event) {

    }

    @FXML
    void handleCercaIscrittiCorso(ActionEvent event) {

    }

    @FXML
    void handleCompletamentoAuto(ActionEvent event) {

    }

    @FXML
    void handleIscrivi(ActionEvent event) {

    }

    @FXML
    void handleReset(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert boxLanguage != null : "fx:id=\"boxLanguage\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCompletamentoAuto != null : "fx:id=\"btnCompletamentoAuto\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNomeMatr != null : "fx:id=\"txtNomeMatr\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognomeMatr != null : "fx:id=\"txtCognomeMatr\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		this.btnCompletamentoAuto.setBackground(new Background(new BackgroundFill(Color.LAWNGREEN, null, null)));
		
	}
}
