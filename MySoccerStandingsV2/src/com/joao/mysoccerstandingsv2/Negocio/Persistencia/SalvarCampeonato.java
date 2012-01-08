package com.joao.mysoccerstandingsv2.Negocio.Persistencia;

import java.util.Date;
import android.content.ContentValues;
import android.content.Context;
import com.joao.mysoccerstandingsv2.Negocio.Campeonato;
import com.joao.mysoccerstandingsv2.Persistencia.*;
import com.joao.mysoccerstandingsv2.Persistencia.Entidades.*;

public class SalvarCampeonato extends DbAdapter {
	
	public SalvarCampeonato(Context contexto) {
		super(contexto);
	}

	public long salvarNovoCampeonato(String nomeCampeonato, int idaVolta) {
		
		ContentValues c = new ContentValues();
		
		c.put(TbCampeonato.DataHoraCriacao, new Date().toString());
		c.put(TbCampeonato.NomeCampeonato, nomeCampeonato);
		c.put(TbCampeonato.IdaVolta, idaVolta);
		c.put(TbCampeonato.Concluido, 0);		
		
		long chave;
		
		open();
		chave = db.insert(TbCampeonato.NOME_TABELA, null, c);
		close();
		
		return chave;	
	}
	
	public void SalvarNovoCampeonato(Campeonato campeonato) {
		
		long id;
		ContentValues c = new ContentValues();
		
		c.put(TbCampeonato.DataHoraCriacao, new Date().toString());
		c.put(TbCampeonato.NomeCampeonato, campeonato.Nome());
		c.put(TbCampeonato.IdaVolta, campeonato.IdaVolta());
		c.put(TbCampeonato.Concluido, 0);
		
		open();
		
		id = db.insert(TbCampeonato.NOME_TABELA, null, c);
		
		if(id > 0)
			campeonato.Id(id);
		
		close();
	}
	
	
	public int atualizarCampeonato(int idCampeonato, int concluido) {
		
		int linhasAtualizadas;
		ContentValues c = new ContentValues();
		
		c.put(TbCampeonato.IdCampeonato, idCampeonato);
		c.put(TbCampeonato.DataHoraUltimoSave, new Date().toString());
		c.put(TbCampeonato.Concluido, concluido);		
		
		open();
		linhasAtualizadas = 
				db.update(TbCampeonato.NOME_TABELA, c, TbCampeonato.IdCampeonato + "=" + idCampeonato, null);
		close();
		
		return linhasAtualizadas;
	}	
}
