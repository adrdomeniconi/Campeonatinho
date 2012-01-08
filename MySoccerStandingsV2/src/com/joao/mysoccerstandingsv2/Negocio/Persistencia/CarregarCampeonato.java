package com.joao.mysoccerstandingsv2.Negocio.Persistencia;

import android.content.Context;
import com.joao.mysoccerstandingsv2.Negocio.Campeonato;
import com.joao.mysoccerstandingsv2.Persistencia.DbAdapter;

public class CarregarCampeonato extends DbAdapter {
	
	protected CarregarCampeonato(Context contexto) {
		super(contexto);
	}

	public Campeonato CarregarCampeonato(long idCampeonato) {
		return new Campeonato("", false);
	}
	
}
