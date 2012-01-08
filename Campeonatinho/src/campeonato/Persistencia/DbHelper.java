package campeonato.Persistencia;

import campeonato.Persistencia.Entidades.*;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
	
	private final static String NOME_BANCO = "Campeonato";
	private static int VERSAO_BANCO = 2;
	
	public DbHelper(Context context) {
		//Chama o construtor da classe SQLiteOpenHelper
		super(context, NOME_BANCO, null, VERSAO_BANCO);
	}
	
	@Override
	//Esse método é chamado durante a criação do banco de dados. Nele as tabelas são criadas e populadas quando
	//for o caso.
	public void onCreate(SQLiteDatabase database) {
		TbCampeonato.onCreate(database);
		TbJogador.onCreate(database);
		TbCampeonatoJogador.onCreate(database);
		TbJogo.onCreate(database);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		TbCampeonato.onUpgrade(db, oldVersion, newVersion);
		TbJogador.onUpgrade(db, oldVersion, newVersion);
		TbCampeonatoJogador.onUpgrade(db, oldVersion, newVersion);
		TbJogo.onUpgrade(db, oldVersion, newVersion);	
	}
}

