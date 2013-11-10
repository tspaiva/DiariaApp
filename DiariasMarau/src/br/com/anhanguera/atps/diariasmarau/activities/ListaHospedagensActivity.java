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
		getMenuInflater().inflate(R.menu.lista_hospededagens, menu);
		return true;
	}
	
	@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			switch (item.getItemId()){
				case R.id.nova_hospedagem:
					Intent intentAddHospedagem = new Intent(this, FormularioHospedagemActivity.class);
					startActivity(intentAddHospedagem);
					break;
					
				case R.id.tela_hospedes:
					Intent intentTelaHospede = new Intent(this, ListaHospedeActivity.class);
					startActivity(intentTelaHospede);
					break;
				
				case R.id.tela_quartos:
					Intent intentTelaQuarto = new Intent(this, ListaQuartoActivity.class);
					startActivity(intentTelaQuarto);
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
