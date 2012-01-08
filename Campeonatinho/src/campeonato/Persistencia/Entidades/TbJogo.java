package campeonato.Persistencia.Entidades;

import android.database.sqlite.SQLiteDatabase;

public class TbJogo {

	//Nome da tabela
	public static final String NOME_TABELA = "Jogo";

	//Colunas da tabela
	public static final String IdJogo = "IdJogo";
	public static final String IdCampeonato = TbCampeonato.IdCampeonato;
	public static final String IdVisitante = "IdVisitante";
	public static final String IdMandante = "IdMandante";
	public static final String Realizado = "Realizado";
	public static final String GolsMandante = "GolsMandante";
	public static final String GolsVisitante = "GolsVisitante";

	//Criação da tabela
	private static final String CREATE_TABLE = "create table " + NOME_TABELA + " ("			
			+ IdJogo + " integer primary key autoincrement, "
			+ IdCampeonato + " integer not null, "
			+ IdVisitante + " integer not null, "
			+ IdMandante + " integer not null, "
			+ Realizado + " integer not null, "
			+ GolsMandante + " integer null, "
			+ GolsVisitante + " integer null, "

			+ "FOREIGN KEY(" + IdCampeonato + ") REFERENCES " + TbCampeonato.NOME_TABELA + "(" + TbCampeonato.IdCampeonato + "), "
			+ "FOREIGN KEY(" + IdVisitante + ") REFERENCES " + TbJogador.NOME_TABELA + "(" + TbJogador.IdJogador + "), "
			+ "FOREIGN KEY(" + IdMandante + ") REFERENCES " + TbJogador.NOME_TABELA + "(" + TbJogador.IdJogador + ")"
			
			+ ");";

	public static void onCreate(SQLiteDatabase database) {
		database.execSQL(CREATE_TABLE);
	}

	public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		database.execSQL("DROP TABLE IF EXISTS " + NOME_TABELA);
		onCreate(database);
	}	
}
