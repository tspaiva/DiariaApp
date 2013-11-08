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


public class ListaHospedagensActivity extends Activity {
private ListView listaHospedagens;
private List<Hospedagem> hospedagens;
	
@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_hospedagens);
		
		listaHospedagens = (ListView) findViewById(R.id.listaHospedagens);
		carregaLista();
		
		listaHospedagens.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int posicao,
					long id) {
				Intent intent = new Intent(ListaHospedagensActivity.this, FechamentoActivity.class);
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
		hospedagens = dao.getList();
		dao.close();
		
		ArrayAdapter<Diaria> adapter = new ArrayAdapter<Diaria>(this, android.R.layout.simple_list_item_1, hospedagens);
		listaHospedagens.setAdapter(adapter);
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
					Intent intent = new Intent(this, FormularioHospedagemActivity.class);
					startActivity(intent);
					break;

				default:
					break;
			}
			return super.onOptionsItemSelected(item);
		}
	
	private void carregaLista(){
		PousadaDAO dao = new PousadaDAO(this);
		hospedagens = dao.getList();
		dao.close();
		
		ArrayAdapter<Hospedagem> adapter = new ArrayAdapter<Hospedagem>(this, android.R.layout.simple_list_item_1, hospedagens);
		listaHospedagens.setAdapter(adapter);
	}
	

}
