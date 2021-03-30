package it.polito.tdp.lab04.model;

public class Corso {
	
	private String codins;
	private Integer numeroCrediti;
	private String nome;
	private Integer periodoDidattico;
	
	public Corso(String codins, Integer numeroCrediti, String nome, Integer periodoDidattico) {
		this.codins = codins;
		this.numeroCrediti = numeroCrediti;
		this.nome = nome;
		this.periodoDidattico = periodoDidattico;
	}

	public String getCodins() {
		return codins;
	}

	public void setCodins(String codins) {
		this.codins = codins;
	}

	public Integer getNumeroCrediti() {
		return numeroCrediti;
	}

	public void setNumeroCrediti(Integer numeroCrediti) {
		this.numeroCrediti = numeroCrediti;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getPeriodoDidattico() {
		return periodoDidattico;
	}

	public void setPeriodoDidattico(Integer periodoDidattico) {
		this.periodoDidattico = periodoDidattico;
	}

}
