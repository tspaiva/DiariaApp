package br.com.anhanguera.atps.diariasmarau.activities;

import br.com.anhanguera.atps.diariasmarau.R;
import br.com.anhanguera.atps.diariasmarau.dao.PousadaDAO;
import br.com.anhanguera.atps.diariasmarau.model.Hospede;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class FormularioHospedeActivity extends Activity {
	private EditText nomeHospede;
	private EditText endHospede;
	private EditText telHospede;
	
	private Hospede hospede = new Hospede();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_formulario_hospede);
		
		this.buscaComponentes();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.formulario_hospede, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){
		case R.id.saveHospede:
			insereHospedeComDadosDaTela();
			
			PousadaDAO dao = new PousadaDAO(FormularioHospedeActivity.this);
			dao.insereHospede(hospede);
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
		this.nomeHospede = (EditText) findViewById(R.id.nomeHospede);
		this.endHospede = (EditText) findViewById(R.id.endHospede);
		this.telHospede = (EditText) findViewById(R.id.telHospede);
	}
	
	private void insereHospedeComDadosDaTela(){
		this.hospede.setNome(this.nomeHospede.getEditableText().toString());
		this.hospede.setEndereco(this.endHospede.getEditableText().toString());
		this.hospede.setTelefone(this.telHospede.getEditableText().toString());
	}
}
