package campeonato.Persistencia.Entidades;

import android.database.sqlite.SQLiteDatabase;

public class TbCampeonatoJogador {
	
	//Nome da tabela
	public static final String NOME_TABELA = "CampeonatoJogador";

	//Colunas da tabela
	public static final String IdCampeonato = TbCampeonato.IdCampeonato;
	public static final String IdJogador = TbJogador.IdJogador;
	public static final String Vitorias = "Vitorias";
	public static final String Derrotas = "Derrotas";
	public static final String Empates = "Empates";
	public static final String GolsPro = "GolsPro";
	public static final String GolsContra = "GolsContra";
	public static final String Pontos = "Pontos";

	//Criação da tabela
	private static final String CREATE_TABLE = "create table " + NOME_TABELA + " ("			
			+ IdCampeonato + " integer not null, "
			+ IdJogador + " integer not null, "
			+ Vitorias + " integer null, "
			+ Derrotas + " integer null, "
			+ Empates + " integer null, "
			+ GolsPro + " integer null, "
			+ GolsContra + " integer null, "
			+ Pontos + " integer null, "
			
			+ "FOREIGN KEY(" + IdCampeonato + ") REFERENCES " + TbCampeonato.NOME_TABELA + "(" + TbCampeonato.IdCampeonato + "), "
			+ "FOREIGN KEY(" + IdJogador + ") REFERENCES " + TbJogador.NOME_TABELA + "(" + TbJogador.IdJogador + ")"
			
			+ ");";

	public static void onCreate(SQLiteDatabase database) {
		database.execSQL(CREATE_TABLE);
	}

	public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		database.execSQL("DROP TABLE IF EXISTS " + NOME_TABELA);
		onCreate(database);
	}	
}
