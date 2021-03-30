package it.polito.tdp.lab04;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
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
    private ComboBox<String> boxLanguage;

    @FXML
    private TextField txtMatricola;

    @FXML
    private Button btnCompletamentoAuto;

    @FXML
    private TextField txtNomeMatr;

    @FXML
    private TextField txtCognomeMatr;

    @FXML
    private TextArea txtResult;

    @FXML
    void handleCercaCorsi(ActionEvent event) {

    }

    @FXML
    void handleCercaIscrittiCorso(ActionEvent event) {
    	this.txtResult.clear();
    	String txtCorso = this.boxLanguage.getValue();
    	
    	if(txtCorso==null) {
    		this.txtResult.setText("Devi scegliere un corso!!");
    		return;
    	}
    	else {
    		String[] codins = txtCorso.split("-");
    		for(Studente s : this.model.getStudentiIscrittiAlCorso(codins[0])) {
    			this.txtResult.appendText(String.format("%-10s ", s.getMatricola()));
    			this.txtResult.appendText(String.format("%-40s ", s.getCognome()));
    			this.txtResult.appendText(String.format("%-40s ", s.getNome()));
    			this.txtResult.appendText(String.format("%-10s ", s.getCDS()));
    			this.txtResult.appendText("\n");
    		}
    	}
    }

    @FXML
    void handleCompletamentoAuto(ActionEvent event) {
    	Integer matricola;
    	
    	try {
    		matricola = Integer.parseInt(this.txtMatricola.getText());
    	}catch(NumberFormatException e) {
    		this.txtResult.setText("Formato della matricola non corretto!!");
    		return;
    	}
    	
    	try {
			List<String> cognomeNome = this.model.getCognomeNomeStudente(matricola);
			this.txtCognomeMatr.setText(cognomeNome.get(0));
			this.txtNomeMatr.setText(cognomeNome.get(1));
		}catch(Exception e) {
			this.txtResult.setText("Matricola non presente nel DataBase!!");
			return;
		}
    	
    }

    @FXML
    void handleIscrivi(ActionEvent event) {

    }

    @FXML
    void handleReset(ActionEvent event) {
    	this.txtResult.clear();
    	this.txtMatricola.clear();
    	this.txtCognomeMatr.clear();
    	this.txtNomeMatr.clear();
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
		
		this.boxLanguage.getItems().add("");
		for(Corso c : this.model.getTuttiICorsi()) {
			this.boxLanguage.getItems().add(c.getCodins()+"-"+c.getNome());
		}
	}
}
