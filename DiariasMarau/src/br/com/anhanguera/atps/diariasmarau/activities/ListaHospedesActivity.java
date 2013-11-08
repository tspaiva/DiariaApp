package br.com.anhanguera.atps.diariasmarau.activities;

import java.util.List;

import br.com.anhanguera.atps.diariasmarau.R;
import br.com.anhanguera.atps.diariasmarau.dao.PousadaDAO;
import br.com.anhanguera.atps.diariasmarau.model.Hospedagem;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ListaHospedesActivity extends Activity {
private ListView listaHospedes;
private List<Hospedagem> diarias;
	
@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_hospedes);
		
		listaHospedes = (ListView) findViewById(R.id.listaHospedes);
		carregaLista();
		
		
/*		String[]hospedes = {"José", "João", "Renan", "Anderson", "Jonathan", "Thiago"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, hospedes);
		listaHospedes = (ListView) findViewById(R.id.listaHospedes);
		listaHospedes.setAdapter(adapter);
//		carregaLista();*/
		
		listaHospedes.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int posicao,
					long id) {
				Intent intent = new Intent(ListaHospedesActivity.this, FechamentoActivity.class);
				startActivity(intent);
			}
		});
	}
	
	@Override
		protected void onResume() {
			super.onResume();
			carregaLista();
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lista_hospedes, menu);
		return true;
	}
	/*private void carregaLista(){
		DiariaDAO dao = new DiariaDAO(this);
		diarias = dao.getList();
		dao.close();
		
		ArrayAdapter<Diaria> adapter = new ArrayAdapter<Diaria>(this, android.R.layout.simple_list_item_1, diarias);
		listaHospedes.setAdapter(adapter);
	}
	@Override
		protected void onResume() {
			super.onResume();
			//carregaLista();
		}*/
	
	
	@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			switch (item.getItemId()){
				case R.id.novo_hospede:
					Intent intent = new Intent(this, FormularioActivity.class);
					startActivity(intent);
					break;

				default:
					break;
			}
			return super.onOptionsItemSelected(item);
		}
	
	private void carregaLista(){
		PousadaDAO dao = new PousadaDAO(this);
		diarias = dao.getList();
		dao.close();
		
		ArrayAdapter<Hospedagem> adapter = new ArrayAdapter<Hospedagem>(this, android.R.layout.simple_list_item_1, diarias);
		listaHospedes.setAdapter(adapter);
	}
	

}
