package campeonato.Aplicacao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import campeonato.Negocio.Jogador;
import campeonato.Negocio.Persistencia.CarregarJogador;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class SelectPlayers extends Activity {
	private ListView addPlayer;
	private ListView lstPlayerList;
	private List<Jogador> jogadores;
	private Button btnNewPlayer;
	private Button btnCreateTournament;



	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selectplayers);
		lstPlayerList = (ListView)findViewById(R.id.lstPlayerList);
		btnNewPlayer = (Button)findViewById(R.id.btnNewPlayer);
		btnCreateTournament = (Button)findViewById(R.id.btnCreateTournament);

		carregarJogadores();    

		registerForContextMenu(lstPlayerList);

		//Listeners

		btnNewPlayer.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent playerformintent = new Intent(SelectPlayers.this,PlayerForm.class);
				startActivityForResult(playerformintent,1);
			}
		});

		btnCreateTournament.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {				
				if (obterJogadoresSelecinados().size() >= 2)
					openTournamentForm();
				else
					Toast.makeText(SelectPlayers.this, "Please select at least two players!", 
							Toast.LENGTH_SHORT).show();
			}
		});
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == 1){
			carregarJogadores();
		}
	}


	private void openTournamentForm(){
		Intent tournamentForm = new Intent(SelectPlayers.this,TournamentForm.class);
		
		List<Long> listaIdsSelecionados = obterJogadoresSelecinados();
		
		//Converte de Long pra long (pois o intent não reconheceu o Long).
		long[] idsSelecionados = new long[listaIdsSelecionados.size()];
		Iterator<Long> iter = listaIdsSelecionados.iterator();
		int i = 0;
		while(iter.hasNext()) {
			idsSelecionados[i] = Long.parseLong(iter.next().toString());
			i++;
		}		
		
		tournamentForm.putExtra("campeonato.Aplicacao.JogadoresSelecionados", idsSelecionados);
		startActivity(tournamentForm);
	}

	private void carregarJogadores(){
		CarregarJogador cj = new CarregarJogador(this);
		jogadores = cj.CarregarJogadoresSalvos();
		createPlayerList();
	}


	public void createPlayerList(){
		lstPlayerList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		ArrayAdapter<Jogador> adapter = new ArrayAdapter<Jogador>(SelectPlayers.this, android.R.layout.simple_list_item_multiple_choice, jogadores);
		lstPlayerList.setAdapter(adapter);
	}

	private List<Long> obterJogadoresSelecinados(){
		
		List<Long> idsJogadores = new LinkedList<Long>();		
		long[] posicoes = lstPlayerList.getCheckItemIds();
		String texto = new String();
		
		//Obtém os objetos dos jogadores selecionados.
		for(int i = 0 ; i < posicoes.length ; i++)
			idsJogadores.add(((Jogador)(lstPlayerList.getItemAtPosition((int)posicoes[i]))).Id());		
		
		texto= texto + "Foram selecionados " + idsJogadores.size();
		Log.d("SelectPlayers", texto);

		return idsJogadores;
	}

	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {  
		super.onCreateContextMenu(menu, v, menuInfo);
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
		Adapter adapter = lstPlayerList.getAdapter();
		String selectedplayer = adapter.getItem(info.position).toString();	    

		menu.setHeaderTitle(selectedplayer);  
		menu.add(0, v.getId(), 0, "Edit");  
		menu.add(0, v.getId(), 0, "Delete");  
	}  

	public boolean onContextItemSelected(MenuItem item) {  
		if(item.getTitle()=="Edit"){editPlayer(item.getItemId());}  
		else if(item.getTitle()=="Delete"){deletePlayer(item.getItemId());}  
		else {return false;}  
		return true;  
	}  

	public void editPlayer(int id){  
		openPlayerForm();
	}
	
	public void deletePlayer(int id){
		final AlertDialog.Builder b = new AlertDialog.Builder(this);
		b.setIcon(android.R.drawable.ic_dialog_alert);
		b.setTitle("Delete Player");
		b.setMessage("Are you sure?");
		b.setPositiveButton("Yes", null);
		b.setNegativeButton("No", null);
		b.show();
	}  


	public void openPlayerForm(){
		Intent playerformintent = new Intent(SelectPlayers.this,PlayerForm.class);
		startActivity(playerformintent);
	}

}
