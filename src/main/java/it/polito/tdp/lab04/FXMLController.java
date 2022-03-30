package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cmbCorso;

    @FXML
    private TextField cognome;

    @FXML
    private TextField nome;

    @FXML
    private TextField txtMatricola;

    @FXML
    private TextArea txtResult;

    @FXML
    void cercaCorsi(ActionEvent event) {
      String matr=txtMatricola.getText();
      String result = "";
      for (Corso c :  model.getCorsiStudente(matr)) {
    	  result+=c.getCodins()+" "+c.getCrediti()+" "+c.getNome()+" "+c.getPd() +"\n";
      }
      txtResult.setText(result); 
    }

    @FXML
    void cercaIscritti(ActionEvent event) {
      String codins=cmbCorso.getValue();
      String result = "";
      for (Studente s :  model.getStudentiIscrittiAlCorso(codins)) {
    	  result+=s.getMatricola()+" "+s.getNome()+" "+s.getCognome()+" "+s.getCDS() +"\n";
      }
      txtResult.setText(result); 
    }

    @FXML
    void iscrivi(ActionEvent event) {
     String matr = txtMatricola.getText();
     String codins=cmbCorso.getValue();	
     if(model.studenteIscritto(matr, codins)==true)
    	 txtResult.setText("Studente già iscritto a questo corso");
     else
    	 txtResult.setText("Studente non iscritto a questo corso");
    }

    @FXML
    void reset(ActionEvent event) {

    }
    
    @FXML
    void handleOK(ActionEvent event) {
       String matricola = txtMatricola.getText();
       Studente s = model.getNomeCognome(matricola);
       cognome.setText(s.getCognome());
       nome.setText(s.getNome());
       
    }

    @FXML
    void initialize() {
        assert cmbCorso != null : "fx:id=\"cmbCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cognome != null : "fx:id=\"cognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert nome != null : "fx:id=\"nome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        
    }
    
    public void setModel(Model model) {
		this.model = model;
		List<Corso> corsiTot = new ArrayList<Corso>();
		cmbCorso.getItems().clear();
        for(Corso c : model.getTuttiICorsi()){
				corsiTot.add(c);
				cmbCorso.getItems().add(c.getCodins());
		
        }

		cmbCorso.getItems().add(" ");
        
	}

}
