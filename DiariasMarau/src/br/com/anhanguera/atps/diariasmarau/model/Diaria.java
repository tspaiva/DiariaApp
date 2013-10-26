package br.com.anhanguera.atps.diariasmarau.model;

public class Diaria {
	public Long id;
	private String numeroQuarto;
	private String nomeLocatario;
	private String numeroPessoas;
	private String valorDiaria;
	private String dia;
	private String mes;
	private String ano;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumeroQuarto() {
		return numeroQuarto;
	}
	public void setNumeroQuarto(String numeroQuarto) {
		this.numeroQuarto = numeroQuarto;
	}
	public String getNomeLocatario() {
		return nomeLocatario;
	}
	public void setNomeLocatario(String nomeLocatario) {
		this.nomeLocatario = nomeLocatario;
	}
	public String getNumeroPessoas() {
		return numeroPessoas;
	}
	public void setNumeroPessoas(String string) {
		this.numeroPessoas = string;
	}
	public String getValorDiaria() {
		return valorDiaria;
	}
	public void setValorDiaria(String string) {
		this.valorDiaria = string;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String string) {
		this.dia = string;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String string) {
		this.mes = string;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String string) {
		this.ano = string;
	}
	
	
	
	
}
