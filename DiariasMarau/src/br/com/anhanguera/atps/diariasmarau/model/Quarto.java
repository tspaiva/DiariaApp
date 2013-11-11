package br.com.anhanguera.atps.diariasmarau.model;

public class Quarto {
	private Long id;
	private String descricao;
	private String valor;
	
	public String toString(){
		return id + " - " + descricao;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
}
