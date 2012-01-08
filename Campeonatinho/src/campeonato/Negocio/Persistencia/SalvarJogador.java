package campeonato.Negocio.Persistencia;

import campeonato.Negocio.Jogador;
import campeonato.Persistencia.DbAdapter;
import campeonato.Persistencia.Entidades.TbJogador;
import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;

public class SalvarJogador extends DbAdapter {
	
	public SalvarJogador(Context contexto) {
		super(contexto);
	}

	public long salvarNovoJogador(Jogador jogador) throws SQLException {
		
		long idJogador;
		
		ContentValues c = new ContentValues();		
		c.put(TbJogador.NomeJogador, jogador.Nome());
		
		open();
		//Salva e seta o id do jogador.
		idJogador =  db.insert(TbJogador.NOME_TABELA, null, c);
		close();
		
		return idJogador;
	}
	
	public long editarJogador(String novoNome) {
		return 0;
	}
	
}
