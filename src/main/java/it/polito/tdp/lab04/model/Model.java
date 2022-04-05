package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	private CorsoDAO corsoDAO;
    private StudenteDAO studenteDAO;
    
    public Model() {
		this.corsoDAO = new CorsoDAO();
		this.studenteDAO = new StudenteDAO();
	}
	public List <Corso> getTuttiICorsi(){
		return this.corsoDAO.getTuttiICorsi();
	}
    
	public Studente getNomeCognome(String matricola){
		return this.studenteDAO.getNomeCognome(matricola);
	}
	
	public List<Studente> getStudentiIscrittiAlCorso(String codins) {
		return this.corsoDAO.getStudentiIscrittiAlCorso(codins);
	}
	
	public List<Corso> getCorsiStudente(String matr){
	   return this.studenteDAO.getCorsiStudente(matr);
	}
	
	public boolean studenteIscritto(String matr, String codins) {
		return this.studenteDAO.studenteIscritto(matr, codins);
	}
	
	public boolean inscriviStudenteACorso(String matr, String codins) {
		if(this.studenteIscritto(matr, codins)==false) {
			return this.corsoDAO.inscriviStudenteACorso(matr, codins);
		}
		return false;
	}
	
}
