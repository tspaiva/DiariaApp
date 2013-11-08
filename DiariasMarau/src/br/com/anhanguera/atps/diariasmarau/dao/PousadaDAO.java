package br.com.anhanguera.atps.diariasmarau.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.anhanguera.atps.diariasmarau.model.Hospedagem;
import br.com.anhanguera.atps.diariasmarau.strings.Banco;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PousadaDAO extends SQLiteOpenHelper{

	private static final String DATABASE = "marau.db";
	private static final int VERSAO = 1; //versão da tabela para marcar que foi alterado algum detalhe do modelo
	private Banco banco;

	
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
	
	public List<Hospedagem> getList(){
		List<Hospedagem> hospedagems = new ArrayList<Hospedagem>();
		
		Cursor c = getWritableDatabase().query(banco.getTABELA_HOSPEDAGEM(), banco.getCOLUNAS_HOSPEDAGEM(), null, null, null, null, null);
		
		c.moveToFirst();
		while (c.moveToNext()){
			Hospedagem hospedagem = new Hospedagem();
			hospedagem.setId(c.getLong(0));
			hospedagem.setNumeroQuarto(c.getString(1));
			hospedagem.setNumeroHospede(c.getString(2));
			hospedagem.setDataEntrada(c.getString(3));
			hospedagem.setDataSaida(c.getString(4));
						
			hospedagems.add(hospedagem);
		}
		
		c.close();
		return hospedagems;
	}
	
	public void insereHospedagem(Hospedagem hospedagem){
		ContentValues values = new ContentValues();
		
		values.put("nome", hospedagem.getNumeroQuarto());
		values.put("numeroQuarto", hospedagem.getNumeroHospede());
		values.put("numeroPessoas", hospedagem.getDataEntrada());
		values.put("dia", hospedagem.getDataSaida());
		values.put("valor", hospedagem.getValorTotal());
		getWritableDatabase().insert(banco.getTABELA_HOSPEDAGEM(), null, values);
	}
}
