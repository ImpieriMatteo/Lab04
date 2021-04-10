package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	public List<Studente> getTuttiGliStudenti() {

		final String sql = "SELECT * FROM studente ORDER by matricola";

		List<Studente> studenti = new LinkedList<Studente>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Integer matricola = rs.getInt("matricola");
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String CDS = rs.getString("CDS");

				Studente s = new Studente(matricola, cognome, nome, CDS);
				studenti.add(s);
			}

			rs.close();
			st.close();
			conn.close();
			
			return studenti;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	public List<Corso> getCorsiPerStudente(Integer matricola) {

		final String sql = "SELECT c.codins, c.crediti, c.nome, c.pd "
				+ "FROM iscrizione i, corso c "
				+ "WHERE i.codins = c.codins AND i.matricola = ?";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				Corso c = new Corso(codins, numeroCrediti, nome, periodoDidattico);
				corsi.add(c);
			}

			rs.close();
			st.close();
			conn.close();
			
			return corsi;
			

		} catch (SQLException e) {
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	public boolean studenteIscrittoAlCorso(Integer matricola, String codins) {
		
		final String sql = "SELECT codins FROM iscrizione WHERE codins = ? AND matricola = ?";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, codins);
			st.setInt(2, matricola);
			
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				rs.close();
				st.close();
				conn.close();
				
				return true;
			}
			else {
				rs.close();
				st.close();
				
				final String sql2 = "INSERT INTO iscrizione VALUES (?, ?)";
				
				PreparedStatement st2 = conn.prepareStatement(sql2);
				st2.setInt(1, matricola);
				st2.setString(2, codins);
				
				ResultSet rs2 = st2.executeQuery();
				
				rs2.close();
				st2.close();
				conn.close();
				
				return false;
			}
			
		} catch(SQLException e) {
			throw new RuntimeException("Errore Db", e);
		}
	}
}
