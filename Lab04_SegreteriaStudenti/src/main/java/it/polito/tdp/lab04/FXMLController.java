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
    private ComboBox<Corso> boxLanguage;

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
    	this.txtResult.clear();
    	Integer matricola;
    	
    	try {
    		matricola = Integer.parseInt(this.txtMatricola.getText());
    	}catch(NumberFormatException e) {
    		this.txtResult.setText("Formato della matricola non corretto!!");
    		return;
    	}
    	
    	try {
    		StringBuilder sb = new StringBuilder();
    		
    		for(Corso c : this.model.getCorsiPerStudente(matricola)) {
    			sb.append(String.format("%-10s %-4d %-55s %-2d", c.getCodins(), c.getNumeroCrediti(), c.getNome(), c.getPeriodoDidattico()));
    			sb.append("\n");
    		}
    		this.txtResult.appendText(sb.toString());
    		
    	}catch(NullPointerException npe) {
    		this.txtResult.setText("Studente non iscritto ad alcun corso");
    		return;
    	}catch(Exception e) {
    		this.txtResult.setText("Studente non presente nel DataBase!!");
			return;
    	}
    }

    @FXML
    void handleCercaIscrittiCorso(ActionEvent event) {
    	this.txtResult.clear();
    	String txtCorso = this.boxLanguage.getValue().getCodins();
    	
    	if(txtCorso==null || txtCorso.equals("")) {
    		this.txtResult.setText("Devi scegliere un corso!!");
    		return;
    	}
    	else {
    		try {
    			StringBuilder sb = new StringBuilder();
    			
    			for(Studente s : this.model.getStudentiIscrittiAlCorso(txtCorso)) {
    				sb.append(String.format("%-10s %-25s %-25s %-10s", s.getMatricola(), s.getCognome(), s.getNome(), s.getCDS()));
    				sb.append("\n");
    			}
    			this.txtResult.appendText(sb.toString());
    			
    		}catch(NullPointerException npe) {
    			this.txtResult.setText("Nessuno studente iscritto al corso");
    	   		return;
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
			this.txtResult.setText("Studente non presente nel DataBase!!");
			return;
		}
    	
    }

    @FXML
    void handleIscrivi(ActionEvent event) {
    	this.txtResult.clear();
    	Integer matricola;
    	String txtCorso = this.boxLanguage.getValue().getCodins();
    	
    	try {
    		matricola = Integer.parseInt(this.txtMatricola.getText());
    	}catch(NumberFormatException e) {
    		this.txtResult.setText("Formato della matricola non corretto!!");
    		return;
    	}
    	
    	if(txtCorso!=null && !txtCorso.equals("")) {
    
    		try {
    			if(this.model.studenteIscrittoAlCorso(matricola, txtCorso))
    				this.txtResult.setText("Studente già iscritto a questo corso");
    			else
    				this.txtResult.setText("Studente iscritto al corso!!");
    		}catch(Exception e) {
        		this.txtResult.setText("Matricola non presente nel DataBase!!");
    			return;
        	}
    	}
    	else {
    		this.txtResult.setText("Devi scegliere un corso!!");
    	}
    }

    @FXML
    void handleReset(ActionEvent event) {
    	this.txtResult.clear();
    	this.txtMatricola.clear();
    	this.txtCognomeMatr.clear();
    	this.txtNomeMatr.clear();
    	this.boxLanguage.setValue(null);
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
		this.txtResult.setStyle("-fx-font-family: monospace");
		
		this.boxLanguage.getItems().add(new Corso("", null, null, null));
		for(Corso c : this.model.getTuttiICorsi()) {
			this.boxLanguage.getItems().add(c);
		}
	}
}
