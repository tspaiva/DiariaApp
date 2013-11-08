package br.com.anhanguera.atps.diariasmarau.activities;

import br.com.anhanguera.atps.diariasmarau.R;
import br.com.anhanguera.atps.diariasmarau.dao.PousadaDAO;
import br.com.anhanguera.atps.diariasmarau.model.Hospedagem;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Camera;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

//TODO Arrumar Campos do formulário
public class FormularioActivity extends Activity {
	private EditText nome; 
	private EditText numQuarto; 
	private EditText numPessoas;
	private EditText dia;
	private EditText mes;
	private EditText ano;
	private EditText valor;
	
	private Hospedagem hospedagem = new Hospedagem();
	
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
				insereHospedagemComDadosDaTela();
				
				PousadaDAO dao = new PousadaDAO(FormularioActivity.this);
				dao.insereHospedagem(hospedagem);
				dao.close();
			
				finish();
			break;
			case R.id.fotoHospede:
				
				

			default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	//TODO Arrumar campos no XML e no código
	private void buscaComponentes(){
		this.nome = (EditText) findViewById(R.id.nome);
		this.numQuarto = (EditText) findViewById(R.id.numQuarto);
		this.numPessoas = (EditText) findViewById(R.id.numPessoas);
		this.dia = (EditText) findViewById(R.id.dia);
		this.mes = (EditText) findViewById(R.id.mes);
		this.ano = (EditText) findViewById(R.id.ano);
		this.valor = (EditText) findViewById(R.id.valor);
	}
	
	private void insereHospedagemComDadosDaTela(){
		this.hospedagem.setNumeroQuarto(this.nome.getEditableText().toString());
		this.hospedagem.setNumeroHospede(this.numQuarto.getEditableText().toString());
		this.hospedagem.setDataEntrada(this.numPessoas.getEditableText().toString());
		this.hospedagem.setDataSaida(this.numPessoas.getEditableText().toString());
		this.hospedagem.setValorTotal(this.valor.getEditableText().toString());
	}
	
	
}
