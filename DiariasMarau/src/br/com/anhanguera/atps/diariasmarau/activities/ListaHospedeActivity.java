package br.com.anhanguera.atps.diariasmarau.activities;

import java.util.List;

import br.com.anhanguera.atps.diariasmarau.R;
import br.com.anhanguera.atps.diariasmarau.dao.PousadaDAO;
import br.com.anhanguera.atps.diariasmarau.model.Hospede;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListaHospedeActivity extends Activity {
	private ListView listaHospedes;
	private List<Hospede> hospedes;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_hospede);
		
		listaHospedes = (ListView) findViewById(R.id.listaHospedes);
		carregaLista();
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		carregaLista();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lista_hospede, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.novo_hospede:
			Intent intentAddHospede = new Intent(this, FormularioHospedeActivity.class);
			startActivity(intentAddHospede);
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void carregaLista(){
		PousadaDAO dao = new PousadaDAO(this);
		hospedes = dao.getListaHospedes();
		dao.close();
		
		ArrayAdapter<Hospede> adapter = new ArrayAdapter<Hospede>(this, android.R.layout.simple_list_item_1, hospedes);
		listaHospedes.setAdapter(adapter);
	}

}
