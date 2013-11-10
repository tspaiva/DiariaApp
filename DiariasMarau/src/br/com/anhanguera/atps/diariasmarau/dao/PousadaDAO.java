package br.com.anhanguera.atps.diariasmarau.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.anhanguera.atps.diariasmarau.model.Hospedagem;
import br.com.anhanguera.atps.diariasmarau.model.Hospede;
import br.com.anhanguera.atps.diariasmarau.model.Quarto;
import br.com.anhanguera.atps.diariasmarau.strings.Banco;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PousadaDAO extends SQLiteOpenHelper{

	private static final String DATABASE = "marau.db";
	private static final int VERSAO = 4; //versão da tabela para marcar que foi alterado algum detalhe do modelo
	private Banco banco = new Banco();
	
	//private static final String TAG = "PousadaDAO";

	
	public PousadaDAO(Context context){
		super(context, DATABASE, null, VERSAO);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(banco.getCREATE_HOSPEDE());
		db.execSQL(banco.getCREATE_HOSPEDAGEM());
		db.execSQL(banco.getCREATE_QUARTO());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String dropHospede ="DROP TABLE IF EXISTS " + banco.getTABELA_HOSPEDE();
		String dropHospedagem ="DROP TABLE IF EXISTS " + banco.getTABELA_HOSPEDAGEM();
		String dropQuarto ="DROP TABLE IF EXISTS " + banco.getTABELA_QUARTO();
		
		db.execSQL(dropHospede);
		db.execSQL(dropHospedagem);
		db.execSQL(dropQuarto);
		
		onCreate(db);
	}
	
	public List<Hospedagem> getListaHospedagens(){
		List<Hospedagem> hospedagens = new ArrayList<Hospedagem>();
		Cursor c = getWritableDatabase().query(banco.getTABELA_HOSPEDAGEM(), banco.getCOLUNAS_HOSPEDAGEM(), null, null, null, null, null);
		c.moveToFirst();
		while (c.moveToNext()){
			Hospedagem hospedagem = new Hospedagem();
			hospedagem.setId(c.getLong(0));
			hospedagem.setNumeroQuarto(c.getString(1));
			hospedagem.setNumeroHospede(c.getString(2));
			hospedagem.setDataEntrada(c.getString(3));
			hospedagem.setDataSaida(c.getString(4));
						
			hospedagens.add(hospedagem);
		}
		
		c.close();
		return hospedagens;
	}
	
	public List<Hospede> getListaHospedes(){
		List<Hospede> hospedes = new ArrayList<Hospede>();
		Cursor c = getWritableDatabase().query(banco.getTABELA_HOSPEDE(), banco.getCOLUNAS_HOSPEDE(), null, null, null, null, null);
		c.moveToFirst();
		while (c.moveToNext()){
			Hospede hospede = new Hospede();
			hospede.setId(c.getLong(0));
			hospede.setNome(c.getString(1));
			hospede.setEndereco(c.getString(2));
			hospede.setTelefone(c.getString(3));
						
			hospedes.add(hospede);
		}
		
		c.close();
		return hospedes;
	}

	public void insereHospedagem(Hospedagem hospedagem){
		ContentValues values = new ContentValues();
		
		values.put("id_quarto", hospedagem.getNumeroQuarto());
		values.put("id_hospede", hospedagem.getNumeroHospede());
		values.put("data_entrada", hospedagem.getDataEntrada());
		values.put("data_saida", hospedagem.getDataSaida());
		getWritableDatabase().insert(banco.getTABELA_HOSPEDAGEM(), null, values);
	}
	
	public void insereHospede(Hospede hospede){
		ContentValues values = new ContentValues();
		
		values.put("nome", hospede.getNome());
		values.put("endereco", hospede.getEndereco());
		values.put("telefone", hospede.getTelefone());
		getWritableDatabase().insert(banco.getTABELA_HOSPEDE(), null, values);
	}
	
	public void insereQuarto(Quarto quarto){
		ContentValues values = new ContentValues();
		
		values.put("descricao", quarto.getDescricao());
		values.put("valor", quarto.getValor());
		getWritableDatabase().insert(banco.getTABELA_QUARTO(), null, values);
		
	}
}
