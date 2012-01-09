package campeonato.Aplicacao;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class TournamentForm extends Activity {
	private Button btnSaveTournament;
	private Button btnCancelTournament;
	private EditText edtTournamentName;
	private Spinner spnTournamentType;
	private Spinner spnTournamentLength;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.tournamentform);
	    btnSaveTournament=(Button)findViewById(R.id.btnSaveTournament);
	    btnCancelTournament=(Button)findViewById(R.id.btnCancelTournament);
	    edtTournamentName=(EditText)findViewById(R.id.edtTournamentName);
	    spnTournamentType=(Spinner)findViewById(R.id.spnTournamentType);
	    spnTournamentLength=(Spinner)findViewById(R.id.spnTournamentlength);
	    
	    
	    
	}

}
