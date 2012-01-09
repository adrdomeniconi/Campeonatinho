package campeonato.Negocio;

import java.util.Iterator;
import java.util.List;

public class Utils {
	
	static void ImprimeListaJogador(List<Jogador> jogadores)
	{
		System.out.println("\nLista de Jogadores: \n");
		
		Iterator<Jogador> iter = jogadores.iterator();
		
		while(iter.hasNext())
			System.out.println(iter.next().Nome());
	}
	
	static void ImprimeListaJogos(List<Jogo> jogo)
	{
		System.out.println("\nLista de Jogos: \n");
		
		Iterator<Jogo> iter = jogo.iterator();
		
		while(iter.hasNext())
			System.out.println(iter.next().toString());
	}
	
	public enum Resultado {
		DERROTA (0), 
		EMPATE (1), 
		VITORIA (3);
		
		private int pontos;
		Resultado(int pontos){
			this.pontos = pontos;
		}
		
		public int Pontos(){
			return pontos;
		}
	}
	
	public enum Tela {
		Jogador,
		Torneio
	}
	
	public static String ListaParaConsultaSql(long[] registros) {
		
		String ret = " IN (";
		
		for(int i = 0 ; i < registros.length ; i++) {
			ret += registros[i];
			if(i != registros.length-1)
				ret += ',';			
		}
		
		ret += ")";
		
		return ret;
	}
}
