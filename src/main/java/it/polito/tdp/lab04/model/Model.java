package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	CorsoDAO corsoDAO;
    StudenteDAO studenteDAO;
    
    public Model() {
		this.corsoDAO = new CorsoDAO();
		this.studenteDAO = new StudenteDAO();
	}
	public List <Corso> getTuttiICorsi(){
		return this.corsoDAO.getTuttiICorsi();
	}
    
    
}
