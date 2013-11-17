package br.com.anhanguera.atps.diariasmarau.activities;

import java.util.ArrayList;
import java.util.List;

import br.com.anhanguera.atps.diariasmarau.R;
import br.com.anhanguera.atps.diariasmarau.dao.PousadaDAO;
import br.com.anhanguera.atps.diariasmarau.model.Hospedagem;
import br.com.anhanguera.atps.diariasmarau.model.Hospede;
import br.com.anhanguera.atps.diariasmarau.model.Quarto;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


public class FormularioHospedagemActivity extends Activity {
	//private EditText nome; 
	private EditText numQuarto;
	private EditText numHospede;
	private EditText dataEntrada;
	private EditText dataSaida;
	private String valorTotal;
	
	private List<Hospede> hospedes = new ArrayList<Hospede>();
	private List<Quarto> quartos = new ArrayList<Quarto>();
	
	private Hospedagem hospedagem = new Hospedagem();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_formulario_hospedagem);
		
		this.buscaComponentes();
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.formulario_hospedagem, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
			case R.id.saveHospedagem:
				insereHospedagemComDadosDaTela();
				
				PousadaDAO dao = new PousadaDAO(FormularioHospedagemActivity.this);
				dao.insereHospedagem(hospedagem);
				dao.close();
			
				finish();
			break;
			
				
			default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void buscaComponentes(){
		//this.nome = (EditText) findViewById(R.id.nome);
		this.numHospede = (EditText) findViewById(R.id.numHospede);
		this.numQuarto = (EditText) findViewById(R.id.numQuarto);
		this.dataEntrada = (EditText) findViewById(R.id.dataEntrada);
		//TODO componete data saida
		//TODO componente valor total
	}
	
	private void insereHospedagemComDadosDaTela(){		
		this.hospedagem.setNumeroHospede(this.numHospede.getEditableText().toString());
		this.hospedagem.setNumeroQuarto(this.numQuarto.getEditableText().toString());
		this.hospedagem.setDataEntrada(this.dataEntrada.getEditableText().toString());
		//TODO data saida
		//TODO valor total
	}
	
	
}
