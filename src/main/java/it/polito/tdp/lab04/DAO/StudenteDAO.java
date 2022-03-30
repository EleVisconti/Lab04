package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	 Map<String, Studente> mappaStudenti = new HashMap<String, Studente>();

	public Studente getNomeCognome(String matr){
		final String sql = "SELECT c.codins, c.crediti, c.nome, c.pd "
				+ "FROM iscrizione i, corso c, studente s "
				+ "WHERE i.matricola= ? AND c.codins=i.codins";
		 List<Studente> studenti = new LinkedList<Studente>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, matr);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String matricola = rs.getString("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String CDS = rs.getString("CDS");

				System.out.println(matricola + " " + cognome + " " + nome + " " + CDS);

				Studente s = new Studente(matricola, cognome, nome, nome);
			    studenti.add(s);
				mappaStudenti.put(matricola, s);
				
				
			}

			conn.close();
			
			
			Studente s = mappaStudenti.get(matr);
			return s;
		

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}}
	
	
	public List<Corso> getCorsiStudente(String matr){
		final String sql = "SELECT c.codins, c.crediti, c.nome, c.pd "
				+ "FROM studente s, iscrizione i, corso c "
				+ "WHERE i.matricola=? AND i.codins = c.codins ";
		 List<Corso> corsi = new LinkedList<Corso>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, matr);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int crediti = Integer.parseInt(rs.getString("crediti"));
				String nome = rs.getString("nome");
				int pd = Integer.parseInt(rs.getString("pd"));

				System.out.println(codins + " " + crediti + " " + nome + " " + pd);

				Corso c = new Corso(codins, crediti, nome, pd);
			    corsi.add(c);
			}

			conn.close();

			return corsi;
		

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}	
	}
	
	public boolean studenteIscritto(String matr, String codins) {
		String matricola=null;
		
		final String sql = "SELECT i.matricola "
				+ "FROM studente s, iscrizione i, corso c "
				+ "WHERE i.matricola=? AND i.codins=?";
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, matr);
			st.setString(2, codins);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
			matricola = rs.getString("matricola");
			}

			conn.close();
			
			if (matricola==null)
			return false;
			
			else
				return true;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		
		
	}
	
	
}
