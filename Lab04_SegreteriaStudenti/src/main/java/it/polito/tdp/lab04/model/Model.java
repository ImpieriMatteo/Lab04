package it.polito.tdp.lab04.model;

import java.util.ArrayList;
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
	
	public List<Corso> getTuttiICorsi() {
		return this.corsoDAO.getTuttiICorsi();
	}
	
	public List<String> getCognomeNomeStudente(Integer matricola) throws Exception {
		List<Studente> studenti = this.studenteDAO.getTuttiGliStudenti();
		Studente temp = ricercaDicotomicaStudente(studenti, matricola, 0, studenti.size());
		
		if(temp==null)
			throw new Exception();
		else {
			List<String> tempL = new ArrayList<>();
			tempL.add(temp.getCognome());
			tempL.add(temp.getNome());
			return tempL;
		}
		
	}
	
	public List<Studente> getStudentiIscrittiAlCorso(String codins){
		return this.corsoDAO.getStudentiIscrittiAlCorso(codins);
	}
	
	public List<Corso> getCorsiPerStudente(Integer matricola) throws Exception {
		List<Studente> studenti = this.studenteDAO.getTuttiGliStudenti();
		Studente temp = ricercaDicotomicaStudente(studenti, matricola, 0, studenti.size());
		
		if(temp==null)
			throw new Exception();
		else {
			return this.studenteDAO.getCorsiPerStudente(matricola);
		}
	}
	
	public void isStudenteIscrittoAlCorso(Integer matricola, String codins) throws Exception{
		List<Studente> studenti = this.studenteDAO.getTuttiGliStudenti();
		Studente temp = ricercaDicotomicaStudente(studenti, matricola, 0, studenti.size());
		
		if(temp==null)
			throw new Exception();
		else {
			try {
				this.studenteDAO.isStudenteIscrittoAlCorso(matricola, codins);
			}catch(RuntimeException re) {
				throw new RuntimeException();
			}
		}
	}
	
	private Studente ricercaDicotomicaStudente(List<Studente> studenti, Integer matricola, Integer low, Integer high) {
		Integer mid = (low+high)/2;
		
		if(low>high) {
			return null;
		}
		else {
			if(matricola.equals(studenti.get(mid).getMatricola()))
				return studenti.get(mid);
			else {
				if(matricola>studenti.get(mid).getMatricola())
					return ricercaDicotomicaStudente(studenti, matricola, mid+1, high);
				else
					return ricercaDicotomicaStudente(studenti, matricola, low, mid-1);
			}
		}
	}
}
