package br.com.anhanguera.atps.diariasmarau.strings;

public class Banco {
	
	private static String TABELA_HOSPEDE = "hospedes";
	private static String TABELA_HOSPEDAGEM = "hospedagem";
	private static String TABELA_QUARTO = "quartos";
	private String[] COLUNAS_HOSPEDE = {"id", "nome", "endereco", "telefone"};
	private String[] COLUNAS_HOSPEDAGEM = {"id", "id_quarto", "id_hospede", "data_entrada", "data_saida"};
	private String[] COLUNAS_QUARTO = {"id", "descricao", "valor"};
	
	//QUERIES PARA CRIAÇÃO DAS TABELAS DO BANCO
	private static String CREATE_HOSPEDE = "CREATE TABLE " + TABELA_HOSPEDE + " (id INTEGER PRIMARY KEY," +
			" nome TEXT NOT NULL, endereco TEXT NOT NULL, telefone TEXT NOT NULL);";
	
	private static String CREATE_HOSPEDAGEM = "CREATE TABLE " + TABELA_HOSPEDAGEM + " (id INTEGER PRIMARY KEY," +
			" id_quarto INTEGER NOT NULL, id_hospede INTEGER NOT NULL, data_entrada TEXT NOT NULL, data_saida TEXT);";
	
	private static String CREATE_QUARTO = "CREATE TABLE " + TABELA_QUARTO + " (id INTEGER PRIMARY KEY," +
			" descricao TEXT NOT NULL, valor REAL NOT NULL)";

	public String[] getCOLUNAS_QUARTO() {
		return COLUNAS_QUARTO;
	}

	public void setCOLUNAS_QUARTO(String[] cOLUNAS_QUARTO) {
		COLUNAS_QUARTO = cOLUNAS_QUARTO;
	}

	public String getTABELA_HOSPEDE() {
		return TABELA_HOSPEDE;
	}

	public String getTABELA_HOSPEDAGEM() {
		return TABELA_HOSPEDAGEM;
	}

	public String getTABELA_QUARTO() {
		return TABELA_QUARTO;
	}

	public String[] getCOLUNAS_HOSPEDE() {
		return COLUNAS_HOSPEDE;
	}

	public String[] getCOLUNAS_HOSPEDAGEM() {
		return COLUNAS_HOSPEDAGEM;
	}

	public String getCREATE_HOSPEDE() {
		return CREATE_HOSPEDE;
	}

	public String getCREATE_HOSPEDAGEM() {
		return CREATE_HOSPEDAGEM;
	}

	public String getCREATE_QUARTO() {
		return CREATE_QUARTO;
	}
	
	//GETS PARA QUERIES E COLUNAS
	
}
