package br.com.anhanguera.atps.diariasmarau.activities;

import java.util.List;

import br.com.anhanguera.atps.diariasmarau.R;
import br.com.anhanguera.atps.diariasmarau.dao.PousadaDAO;
import br.com.anhanguera.atps.diariasmarau.model.Hospedagem;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ListaHospedagensActivity extends Activity {
private ListView listaHospedagens;
private List<Hospedagem> hospedagens;
private Hospedagem hospedagemSelecionada;
	
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
		
		listaHospedagens.setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> adapter, View view, int posicao, long id){
				hospedagemSelecionada = (Hospedagem) adapter.getItemAtPosition(posicao);
				registerForContextMenu(listaHospedagens);
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
					new AlertDialog.Builder(ListaHospedagensActivity.this)
					.setIcon(android.R.drawable.ic_dialog_alert)
					.setTitle("Excluir")
					.setMessage("Deseja mesmo excluir?")
					.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							PousadaDAO dao = new PousadaDAO(ListaHospedagensActivity.this);
							dao.excluirHospedagem(hospedagemSelecionada);
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
		getMenuInflater().inflate(R.menu.lista_hospededagens, menu);
		return true;
	}
	
	@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			switch (item.getItemId()){
				case R.id.tela_quartos:
					Intent intentTelaQuarto = new Intent(this, ListaQuartoActivity.class);
					startActivity(intentTelaQuarto);
					break;
				
				case R.id.tela_hospedes:
					Intent intentTelaHospede = new Intent(this, ListaHospedeActivity.class);
					startActivity(intentTelaHospede);
					break;
				
				case R.id.nova_hospedagem:
					Intent intentAddHospedagem = new Intent(this, FormularioHospedagemActivity.class);
					startActivity(intentAddHospedagem);
					break;
					
				default:
					break;
			}
			return super.onOptionsItemSelected(item);
		}
	
	private void carregaLista(){
		PousadaDAO dao = new PousadaDAO(this);
		hospedagens = dao.getListaHospedagens();
		dao.close();
		
		ArrayAdapter<Hospedagem> adapter = new ArrayAdapter<Hospedagem>(this, android.R.layout.simple_list_item_1, hospedagens);
		listaHospedagens.setAdapter(adapter);
	}
	

}
