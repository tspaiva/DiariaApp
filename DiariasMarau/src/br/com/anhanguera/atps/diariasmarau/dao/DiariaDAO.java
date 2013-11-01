package br.com.anhanguera.atps.diariasmarau.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.anhanguera.atps.diariasmarau.model.Diaria;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DiariaDAO extends SQLiteOpenHelper{

	private static final int VERSAO = 1; //versão da tabela para marcar que foi alterado algum detalhe do modelo
	private static final String TABELA = "diarias";
	private static final String DATABASE = "marau.db";
	private static final String[] COLUNAS = {"id", "nome", "numeroQuarto", "numeroPessoas", "dia", "mes", "ano", "valor"};
	
	public DiariaDAO(Context context){
		super(context, DATABASE, null, VERSAO);
	}
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		String ddl = "CREATE TABLE " + TABELA + " (id INTEGER PRIMARY KEY, " +
				" nome TEXT NOT NULL, numeroQuarto TEXT NOT NULL, numeroPessoas TEXT NOT NULL, dia TEXT NOT NULL, " +
				" mes TEXT NOT NULL, ano TEXT NOT NULL, valor TEXT NOT NULL);";
			db.execSQL(ddl);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql ="DROP TABLE IF EXISTS " + TABELA;
		db.execSQL(sql);
		onCreate(db);
	}
	
	public List<Diaria> getList(){
		List<Diaria> diarias = new ArrayList<Diaria>();
		
		Cursor c = getWritableDatabase().query(TABELA, COLUNAS, null, null, null, null, null);
		
		c.moveToFirst();
		while (c.moveToNext()){
			Diaria diaria = new Diaria();
			diaria.setId(c.getLong(0));
			diaria.setNomeLocatario(c.getString(1));
			diaria.setNumeroQuarto(c.getString(2));
			diaria.setNumeroPessoas(c.getString(3));
			diaria.setDia(c.getString(4));
			diaria.setMes(c.getString(5));
			diaria.setAno(c.getString(6));
			diaria.setValorDiaria(c.getString(7));
			
			diarias.add(diaria);
		}
		
		c.close();
		return diarias;
	}
	
	public void insert(Diaria diaria){
		ContentValues values = new ContentValues();
		
		values.put("nome", diaria.getNomeLocatario());
		values.put("numeroQuarto", diaria.getNumeroQuarto());
		values.put("numeroPessoas", diaria.getNumeroPessoas());
		values.put("dia", diaria.getDia());
		values.put("mes", diaria.getMes());
		values.put("ano", diaria.getAno());
		values.put("valor", diaria.getValorDiaria());
		getWritableDatabase().insert(TABELA, null, values);
	}
}
