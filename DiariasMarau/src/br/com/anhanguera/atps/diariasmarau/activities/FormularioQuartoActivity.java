package br.com.anhanguera.atps.diariasmarau.activities;

import br.com.anhanguera.atps.diariasmarau.R;
import br.com.anhanguera.atps.diariasmarau.dao.PousadaDAO;
import br.com.anhanguera.atps.diariasmarau.model.Quarto;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class FormularioQuartoActivity extends Activity {
	private EditText descricao;
	private EditText valor;
	
	private Quarto quarto = new Quarto();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_formulario_quarto);
		
		this.buscaComponentes();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.formulario_quarto, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){
		case R.id.saveQuarto:
			insereQuartoComDadosDaTela();
			
			PousadaDAO dao = new PousadaDAO(FormularioQuartoActivity.this);
			dao.insereQuarto(quarto);
			dao.close();
		
			finish();
		break;
		case R.id.fotoHospede:
			
		default:
		break;
	}
		return super.onOptionsItemSelected(item);
	}
	
	private void buscaComponentes(){
		this.descricao = (EditText) findViewById(R.id.descricaoQuarto);
		this.valor = (EditText) findViewById(R.id.valorQuarto);
	}
	
	private void insereQuartoComDadosDaTela(){
		this.quarto.setDescricao(this.descricao.getEditableText().toString());
		this.quarto.setValor(this.valor.getEditableText().toString());
	}
}
