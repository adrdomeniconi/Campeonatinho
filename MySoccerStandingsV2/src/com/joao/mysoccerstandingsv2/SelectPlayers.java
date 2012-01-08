package com.joao.mysoccerstandingsv2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.joao.mysoccerstandingsv2.Negocio.Jogador;
import com.joao.mysoccerstandingsv2.Negocio.Persistencia.CarregarJogador;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SelectPlayers extends ListActivity {
	private ListView addPlayer;
	private ListView lstPlayerList;
	private List<Jogador> jogadores;
	

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.selectplayers);
	    addPlayer = (ListView)findViewById(R.id.addPlayer);
	    lstPlayerList = (ListView)findViewById(R.id.lstPlayerList);
	    createAddPlayer();
	    createPlayerList();
	    registerForContextMenu(lstPlayerList);
	    CarregarJogador cj = new CarregarJogador(this);
	    jogadores = cj.CarregarJogadoresSalvos();
	    
	    
	    
	}

	


	public void createAddPlayer(){
		List<String> addplayerlabel = new ArrayList<String>();
		addplayerlabel.add("Add new player...");
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(SelectPlayers.this, android.R.layout.simple_list_item_1, addplayerlabel);
		addPlayer.setAdapter(adapter);
		
	}
	
	public void createPlayerList(){
		lstPlayerList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		ArrayAdapter<Jogador> adapter = new ArrayAdapter<Jogador>(SelectPlayers.this, android.R.layout.simple_list_item_multiple_choice, jogadores);
		lstPlayerList.setAdapter(adapter);
		
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