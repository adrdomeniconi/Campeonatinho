package com.joao.mysoccerstandingsv2.Negocio.Persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import com.joao.mysoccerstandingsv2.Negocio.Jogador;
import com.joao.mysoccerstandingsv2.Persistencia.DbAdapter;
import com.joao.mysoccerstandingsv2.Persistencia.Entidades.TbCampeonato;
import com.joao.mysoccerstandingsv2.Persistencia.Entidades.TbCampeonatoJogador;
import com.joao.mysoccerstandingsv2.Persistencia.Entidades.TbJogador;

public class SalvarCampeonatoJogador extends DbAdapter {

	public SalvarCampeonatoJogador(Context contexto) {
		super(contexto);
	}
	
	public long SalvarNovoCampeonatoJogador(Jogador jogador, long idCampeonato) throws SQLException {
		
		long result;
		
		ContentValues cj = new ContentValues();
		cj.put(TbCampeonatoJogador.IdCampeonato, idCampeonato);
		cj.put(TbCampeonatoJogador.IdJogador, jogador.Id());
		
		open();
		result = db.insert(TbCampeonatoJogador.NOME_TABELA, null, cj);
		close();
		
		return result;
	}
	
	public void atualizarCampeonatoJogador(Jogador j, int idCampeonato) throws SQLException {
		
		ContentValues c = new ContentValues();
		c.put(TbCampeonatoJogador.Vitorias, j.Vitorias());
		c.put(TbCampeonatoJogador.Derrotas, j.Derrotas());
		c.put(TbCampeonatoJogador.Empates, j.Empates());
		c.put(TbCampeonatoJogador.GolsPro, j.GolsPro());
		c.put(TbCampeonatoJogador.GolsContra, j.GolsContra());
		c.put(TbCampeonatoJogador.Pontos, j.Pontos());
		
		open();
		db.update(TbCampeonatoJogador.NOME_TABELA, c, 
				TbCampeonato.IdCampeonato + "=" + idCampeonato 
				+ " and " +
				TbJogador.IdJogador + "=" + j.Id(), 
				null);
		close();
	}

}
