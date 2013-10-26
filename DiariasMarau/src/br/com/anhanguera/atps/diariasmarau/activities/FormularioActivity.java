package br.com.anhanguera.atps.diariasmarau.activities;

import br.com.anhanguera.atps.diariasmarau.R;
import br.com.anhanguera.atps.diariasmarau.dao.DiariaDAO;
import br.com.anhanguera.atps.diariasmarau.model.Diaria;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


public class FormularioActivity extends Activity {
	private EditText nome; 
	private EditText numQuarto; 
	private EditText numPessoas;
	private EditText dia;
	private EditText mes;
	private EditText ano;
	private EditText valor;
	
	private Diaria diaria = new Diaria();
	
	
	private void buscaComponentes(){
		this.nome = (EditText) findViewById(R.id.nome);
		this.numQuarto = (EditText) findViewById(R.id.numQuarto);
		this.numPessoas = (EditText) findViewById(R.id.numPessoas);
		this.dia = (EditText) findViewById(R.id.dia);
		this.mes = (EditText) findViewById(R.id.mes);
		this.ano = (EditText) findViewById(R.id.ano);
		this.valor = (EditText) findViewById(R.id.valor);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_formulario);
		
		this.buscaComponentes();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.formulario, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
			case R.id.saveHospede:
				insereDiariaComDadosDaTela();
				
				DiariaDAO dao = new DiariaDAO(FormularioActivity.this);
				dao.insert(diaria);
				dao.close();
			
				finish();
			break;

			default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void insereDiariaComDadosDaTela(){
		this.diaria.setNomeLocatario(this.nome.getEditableText().toString());
		this.diaria.setNumeroQuarto(this.numQuarto.getEditableText().toString());
		this.diaria.setNumeroPessoas(this.numPessoas.getEditableText().toString());
		this.diaria.setDia(this.dia.getEditableText().toString());
		this.diaria.setMes(this.mes.getEditableText().toString());
		this.diaria.setAno(this.ano.getEditableText().toString());
		this.diaria.setValorDiaria(this.valor.getEditableText().toString());
	}
	
	
}
