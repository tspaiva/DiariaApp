package br.com.anhanguera.atps.diariasmarau.activities;

import java.util.List;

import br.com.anhanguera.atps.diariasmarau.R;
import br.com.anhanguera.atps.diariasmarau.dao.DiariaDAO;
import br.com.anhanguera.atps.diariasmarau.model.Diaria;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ListaHospedesActivity extends Activity {
private ListView listaHospedes;
private List<Diaria> diarias;
private Diaria diariaSelecionada;
	
@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_hospedes);
		
		listaHospedes = (ListView) findViewById(R.id.listaHospedes);
		carregaLista();
		
		listaHospedes.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int posicao,
					long id) {
				Intent intent = new Intent(ListaHospedesActivity.this, FechamentoActivity.class);
				startActivity(intent);
			}
		});
		
		
		listaHospedes.setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> adapter, View view, int posicao, long id){
				diariaSelecionada = (Diaria) adapter.getItemAtPosition(posicao);
				registerForContextMenu(listaHospedes);
				return false;
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
	
	@Override
		public void onCreateContextMenu(ContextMenu menu, View v,
				ContextMenuInfo menuInfo) {
			super.onCreateContextMenu(menu, v, menuInfo);
			
			MenuItem excluir = menu.add(0,0,0, "Excluir");
			excluir.setOnMenuItemClickListener(new OnMenuItemClickListener() {
				
				@Override
				public boolean onMenuItemClick(MenuItem item) {
					DiariaDAO dao = new DiariaDAO(ListaHospedesActivity.this);
					dao.delete(diariaSelecionada);
					dao.close();
					carregaLista();
					return false;
				}
			});
		}
	private void carregaLista(){
		DiariaDAO dao = new DiariaDAO(this);
		diarias = dao.getList();
		dao.close();
		
		ArrayAdapter<Diaria> adapter = new ArrayAdapter<Diaria>(this, android.R.layout.simple_list_item_1, diarias);
		listaHospedes.setAdapter(adapter);
	}
	

}
