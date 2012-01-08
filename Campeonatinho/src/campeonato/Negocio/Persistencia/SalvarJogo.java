package campeonato.Negocio.Persistencia;

import campeonato.Negocio.Jogo;
import campeonato.Persistencia.DbAdapter;
import campeonato.Persistencia.Entidades.TbJogo;
import android.content.ContentValues;
import android.content.Context;

public class SalvarJogo extends DbAdapter {

	public SalvarJogo(Context contexto) {
		super(contexto);
	}

	public void salvarNovoJogo(Jogo jogo, long idCampeonato) {
		
		ContentValues c = new ContentValues();		
		c.put(TbJogo.IdMandante, jogo.Mandante().Id());
		c.put(TbJogo.IdVisitante, jogo.Visitante().Id());
		c.put(TbJogo.IdCampeonato, idCampeonato);
		c.put(TbJogo.Realizado, jogo.Realizado());
		
		open();
		jogo.Id(db.insert(TbJogo.NOME_TABELA, null, c));
		close();
	}
	
	public void atualizarJogo(Jogo jogo) {
		
		ContentValues c = new ContentValues();
		
		c.put(TbJogo.IdJogo, jogo.Id());
		c.put(TbJogo.GolsMandante, jogo.GolsMandante());
		c.put(TbJogo.GolsVisitante, jogo.GolsMandante());
		c.put(TbJogo.Realizado, jogo.Realizado());
		
		open();
		db.update(TbJogo.NOME_TABELA, c, TbJogo.IdJogo + "=" + jogo.Id(), null);
		close();		
	}		
}
