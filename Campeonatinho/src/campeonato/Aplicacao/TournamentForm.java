package campeonato.Aplicacao;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import campeonato.Negocio.*;
import campeonato.Negocio.Persistencia.CarregarJogador;

public class TournamentForm extends Activity {
	private Button btnBeginTournament;
	private Button btnCancelTournament;
	private EditText edtTournamentName;
	private Spinner spnTournamentType;
	private Spinner spnTournamentLength;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.tournamentform);
	    btnBeginTournament=(Button)findViewById(R.id.btnBeginTournament);
	    btnCancelTournament=(Button)findViewById(R.id.btnCancelTournament);
	    edtTournamentName=(EditText)findViewById(R.id.edtTournamentName);
	    spnTournamentType=(Spinner)findViewById(R.id.spnTournamentType);
	    spnTournamentLength=(Spinner)findViewById(R.id.spnTournamentlength);
	    
	    btnBeginTournament.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {				
				
				//TODO: Fazer verificação de campos obrigatórios.
				
				long idCampeonato = CriarCampeonato();
				
				//TODO: Fazer a chamada para a tela com a tabela passando via intent o id do
				//campeonato.
			}
		});
	    
	}
	
	private long CriarCampeonato() {
		
		String nome = edtTournamentName.getText().toString();
		boolean idaVolta = false; //TODO: Usar os valores da combo, ou criar um botão de rádio.
		List<Jogador> jogadores = ObterJogadoresSelecionados(
				getIntent().getLongArrayExtra("campeonato.Aplicacao.JogadoresSelecionados"));		
		
		//Cria o campeonato e o Salva no banco de Dados.
		Campeonato campeonato = new Campeonato(nome, idaVolta, jogadores, TournamentForm.this);
		
		return campeonato.Id();		
	}
	
	private List<Jogador> ObterJogadoresSelecionados(long[] idsJogadores) {		
		return new CarregarJogador(TournamentForm.this).CarregarJogadoresPorIds(idsJogadores);		
	}

}
