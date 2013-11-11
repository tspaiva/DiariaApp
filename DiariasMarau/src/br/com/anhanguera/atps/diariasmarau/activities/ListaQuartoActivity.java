package br.com.anhanguera.atps.diariasmarau.activities;

import java.util.List;

import br.com.anhanguera.atps.diariasmarau.R;
import br.com.anhanguera.atps.diariasmarau.R.layout;
import br.com.anhanguera.atps.diariasmarau.R.menu;
import br.com.anhanguera.atps.diariasmarau.dao.PousadaDAO;
import br.com.anhanguera.atps.diariasmarau.model.Quarto;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListaQuartoActivity extends Activity {
	private ListView listaQuartos;
	private List<Quarto> quartos;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_quarto);
		
		listaQuartos = (ListView) findViewById(R.id.listaQuartos);
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
		getMenuInflater().inflate(R.menu.lista_quarto, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.novo_quarto:		
			Intent intentAddQuarto = new Intent(this, FormularioQuartoActivity.class);
			startActivity(intentAddQuarto);
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void carregaLista(){
		PousadaDAO dao = new PousadaDAO(this);
		quartos = dao.getListaQuartos();
		dao.close();
		
		ArrayAdapter<Quarto> adapter = new ArrayAdapter<Quarto>(this, android.R.layout.simple_expandable_list_item_1, quartos);
		listaQuartos.setAdapter(adapter);
	}

}
