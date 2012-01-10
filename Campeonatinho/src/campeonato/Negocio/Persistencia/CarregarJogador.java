package campeonato.Negocio.Persistencia;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import campeonato.Negocio.Jogador;
import campeonato.Negocio.Utils;
import campeonato.Persistencia.DbAdapter;
import campeonato.Persistencia.Entidades.TbJogador;

public class CarregarJogador extends DbAdapter {
	
	public CarregarJogador(Context contexto) {
		super(contexto);
	}

	public List<Jogador> CarregarJogadoresSalvos() {
		
		Cursor cursor;
		List<Jogador> jogadores = new LinkedList<Jogador>();
		
		long id;
		String nome;
		
		String query = "select * "
					 + "from " + TbJogador.NOME_TABELA;
		
		open();
		cursor = db.rawQuery(query, null);		
		cursor.moveToFirst();
		close();
		
		int indexIdJogador = cursor.getColumnIndexOrThrow(TbJogador.IdJogador);
		int indexNomeJogador = cursor.getColumnIndexOrThrow(TbJogador.NomeJogador);
		
		while(!cursor.isAfterLast()) {
			
			id = cursor.getLong(indexIdJogador);
			nome = cursor.getString(indexNomeJogador);
			
			jogadores.add(new Jogador(id, (byte)0, nome));
			
			cursor.moveToNext();			
		}
		
		return jogadores;		
	}
	
public List<Jogador> CarregarJogadoresPorIds(long[] idsJogadores) {
		
		Cursor cursor;
		List<Jogador> jogadores = new LinkedList<Jogador>();
		
		long id;
		String nome;
		
		String query = "select * "
					 + "from " + TbJogador.NOME_TABELA
					 + " where " + TbJogador.IdJogador + Utils.ListaParaConsultaSql(idsJogadores);
		
		open();
		cursor = db.rawQuery(query, null);		
		cursor.moveToFirst();
		close();
		
		int indexIdJogador = cursor.getColumnIndexOrThrow(TbJogador.IdJogador);
		int indexNomeJogador = cursor.getColumnIndexOrThrow(TbJogador.NomeJogador);
		
		while(!cursor.isAfterLast()) {
			
			id = cursor.getLong(indexIdJogador);
			nome = cursor.getString(indexNomeJogador);
			
			jogadores.add(new Jogador(id, (byte)0, nome));
			
			cursor.moveToNext();			
		}
		
		return jogadores;		
	}
	
}
