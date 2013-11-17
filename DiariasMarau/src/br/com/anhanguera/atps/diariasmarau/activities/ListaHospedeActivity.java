package br.com.anhanguera.atps.diariasmarau.activities;

import java.util.List;

import br.com.anhanguera.atps.diariasmarau.R;
import br.com.anhanguera.atps.diariasmarau.dao.PousadaDAO;
import br.com.anhanguera.atps.diariasmarau.model.Hospede;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListaHospedeActivity extends Activity {
	private ListView listaHospedes;
	private List<Hospede> hospedes;
	private Hospede hospedeSelecionado;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_hospede);
		
		listaHospedes = (ListView) findViewById(R.id.listaHospedes);
		carregaLista();
		
		listaHospedes.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view, int posicao, long id){
				hospedeSelecionado = (Hospede) adapter.getItemAtPosition(posicao);
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
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		
		MenuItem excluir = menu.add(0,0,0, "Excluir");
		excluir.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				new AlertDialog.Builder(ListaHospedeActivity.this)
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setTitle("Excluir")
				.setMessage("Deseja mesmo excluir?")
				.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						PousadaDAO dao = new PousadaDAO(ListaHospedeActivity.this);
						dao.excluirHospede(hospedeSelecionado);
						dao.close();
						carregaLista();
					}
				}).setNegativeButton("Não", null).show();
				
				return false;
			}
		});
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
