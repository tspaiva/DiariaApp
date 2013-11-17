package br.com.anhanguera.atps.diariasmarau.model;

public class Hospedagem {
	public Long id;
	private String numeroQuarto;
	private String numeroHospede;
	private String dataEntrada;
	private String dataSaida;
	private String valorTotal;
	
	public String toString(){
		return id + " - " + dataEntrada;
	}

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

	public String getNumeroHospede() {
		return numeroHospede;
	}

	public void setNumeroHospede(String numeroHospede) {
		this.numeroHospede = numeroHospede;
	}

	public String getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public String getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(String dataSaida) {
		this.dataSaida = dataSaida;
	}

	public String getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	
}