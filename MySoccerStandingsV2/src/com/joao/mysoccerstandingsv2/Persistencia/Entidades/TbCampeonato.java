package com.joao.mysoccerstandingsv2.Persistencia.Entidades;

import android.database.sqlite.SQLiteDatabase;

public class TbCampeonato {

	//Nome da tabela
	public static final String NOME_TABELA = "Campeonato";
	
	//Colunas da tabela
	public static final String IdCampeonato = "IdCampeonato";
	public static final String NomeCampeonato = "NomeCampeonato";
	public static final String DataHoraCriacao = "DataHoraCriacao";
	public static final String DataHoraUltimoSave = "DataHoraUltimoSave";
	public static final String IdaVolta = "IdaVolta";
	public static final String Concluido = "Concluido";
	
	//Criação da tabela
	private static final String CREATE_TABLE = "create table " + NOME_TABELA + " ("
			+ IdCampeonato + " integer primary key autoincrement, "
			+ NomeCampeonato + " text not null, "
			+ DataHoraCriacao + " text not null, "
			+ DataHoraUltimoSave + " text null, "
			+ IdaVolta + " integer not null, "
			+ Concluido + " integer not null"
			+ ");";

	public static void onCreate(SQLiteDatabase database) {
		database.execSQL(CREATE_TABLE);
	}

	public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		database.execSQL("DROP TABLE IF EXISTS " + NOME_TABELA);
		onCreate(database);
	}
}
