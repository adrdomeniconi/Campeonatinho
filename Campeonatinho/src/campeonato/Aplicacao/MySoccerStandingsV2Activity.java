package campeonato.Aplicacao;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.Toast;

public class MySoccerStandingsV2Activity extends TabActivity {

	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    TabHost tabHost = getTabHost();
	    LayoutInflater.from(getApplicationContext()).inflate(R.layout.main, tabHost.getTabContentView());
	    
	    TabHost.TabSpec tabStandings = tabHost.newTabSpec("Standings");
	    tabStandings.setContent(R.id.tabStandings);
	    tabStandings.setIndicator("Standings");
	    tabHost.addTab(tabStandings);
	    
	    TabHost.TabSpec tabResults = tabHost.newTabSpec("Results");
	    tabResults.setContent(R.id.tabResults);
	    tabResults.setIndicator("Results");
	    tabHost.addTab(tabResults);
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
    	// TODO Auto-generated method stub
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.main_menu, menu);
      	return true;
    }
	
	public void openSelectPlayersLayout(){
    	Intent playersintent = new Intent(MySoccerStandingsV2Activity.this,SelectPlayers.class);
    	startActivity(playersintent);
    }
    
	
	   public boolean onOptionsItemSelected(MenuItem item) {
		   String mensagem = null;
	    	switch (item.getItemId()) {
			case R.id.newTournament:
				openSelectPlayersLayout();
				break;
			case R.id.loadTournament:
				openSelectPlayersLayout();
				break;
			case R.id.about:
				mensagem = "My Soccer Standings - Version 0.2b";
		    	Toast.makeText(MySoccerStandingsV2Activity.this, mensagem, Toast.LENGTH_LONG).show();
				break;
			default:
				break;
			}
	    
	    	return true;
	    }

}