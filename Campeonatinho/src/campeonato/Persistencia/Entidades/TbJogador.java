package campeonato.Persistencia.Entidades;

import android.database.sqlite.SQLiteDatabase;

public class TbJogador {
	
	//Nome da tabela
	public static final String NOME_TABELA = "Jogador";

	//Colunas da tabela
	public static final String IdJogador = "IdJogador";
	public static final String NomeJogador = "NomeJogador";

	//Criação da tabela
	private static final String CREATE_TABLE = "create table " + NOME_TABELA + " ("
			+ IdJogador + " integer primary key autoincrement, "
			+ NomeJogador + " text not null"
			+ ");";

	public static void onCreate(SQLiteDatabase database) {
		database.execSQL(CREATE_TABLE);
	}

	public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		database.execSQL("DROP TABLE IF EXISTS " + NOME_TABELA);
		onCreate(database);
	}
}
