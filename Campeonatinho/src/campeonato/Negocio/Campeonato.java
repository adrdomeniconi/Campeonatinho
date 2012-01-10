package campeonato.Negocio;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;

import campeonato.Negocio.Persistencia.SalvarCampeonato;
import campeonato.Negocio.Persistencia.SalvarCampeonatoJogador;

public class Campeonato {
	
	private long id;
	
	private List<Jogador> jogadores;
	private List<Jogo> jogos;
	
	private String nome;
	private Boolean idaVolta;
	
	private short[][] tabela;
	
	private Context contexto;
	
	//Construtor
	public Campeonato(String nome, Boolean idaVolta, List<Jogador> jogadores, Context contexto) {
		jogos = new LinkedList<Jogo>();
		
		this.nome = nome;
		this.idaVolta = idaVolta;
		this.contexto = contexto;
		
		//Salva o campeonato em banco de dados.
		this.SalvarCampeonato();
		
		//Salva os jogadores no banco de dados e carrega uma lista em memória com objetos representando as entidades.
		this.AdicionarJogadores(jogadores);
	}
	
	private void SalvarCampeonato() {
		
		//O Id do campeonato deve ser setado automaticamente.
		new SalvarCampeonato(contexto).SalvarNovoCampeonato(this);		
	}
	
	//Método chamado apenas na CRIAÇÃO do campeonato.
	private void AdicionarJogadores(List<Jogador> jogadores) {
		
		//TODO: Há algum problema nesse método.
		
		SalvarCampeonatoJogador salvar = new SalvarCampeonatoJogador(contexto);
		long sucesso;
		Jogador jogadorAtual;
		
		Iterator<Jogador> iter = jogadores.iterator();
		while(iter.hasNext()) {
			
			jogadorAtual = (Jogador)iter.next();
			
			sucesso = salvar.SalvarNovoCampeonatoJogador(jogadorAtual, this.id);
			
			if(sucesso > 0)
				jogadores.add(new Jogador(jogadorAtual.Id(), (byte)(jogadores.size()+1), jogadorAtual.Nome()));
		}
	}
	
	public long Id() {
		return this.id;
	}
	
	public void Id(long id) {
		this.id = id;
	}
	
	public Boolean IdaVolta() {
		return this.idaVolta;
	}
	
	public String Nome() {
		return this.nome;
	}
	
	public List<Jogo> ObterJogos() {
		return jogos;
	}
	
	public Jogo ObterJogo(long idJogo) {
		
		boolean encontrou = false;
		Jogo jogoAtual = null;
		Iterator<Jogo> iter = jogos.iterator();
		
		while(!encontrou && iter.hasNext()) {
			
			jogoAtual = (Jogo)iter.next();
			
			if(jogoAtual.Id() == idJogo)
				encontrou = true;
		}
		
		return jogoAtual;
	}
	
	public List<Jogador> ObterJogadores() {
		return jogadores;
	}
	
	//Preenche a lista de jogos
	public void GerarTabela() {		
		
		//Gera uma matriz para controle dos jogos gerados
		tabela = new short[(byte)jogadores.size()][(byte)jogadores.size()];
		
		CriarJogosTurno();
		
		if(idaVolta)
			CriarJogosReturno();
				
		SalvarJogos();
	}
	
	private void CriarJogosTurno() {

		byte numMandante;
		byte numVisitante;
		
		Jogador mandante;
		Jogador visitante;
		
		boolean sucesso;
		
		//Verifica se já existem todos os jogos
		while(jogos.size() < NroJogosTurno()){
		
			//Ordena a lista de jogadores de acordo com o maior tempo sem jogar e menor numero de jogos.
			Collections.sort(jogadores);			
			
			numMandante = 0;
			numVisitante = 1;
			
			mandante = jogadores.get(numMandante);
			visitante = jogadores.get(numVisitante);
			
			sucesso = false;
			
			while(!sucesso && jogos.size() < NroJogosTurno()) {
				
				//System.out.println("Tentando adicionar: " + mandante.Nome() + " e " + visitante.Nome());
				
				sucesso = CriarJogo(mandante, visitante);
				
				if(!sucesso){
					
					//System.out.println("Não adicionou!");
					
					//Tenta uma nova combinação
					if(numVisitante < jogadores.size())
						numVisitante++;
					else {
						numVisitante = 1;
						numMandante++;
					}
					
					mandante = jogadores.get(numMandante);
					visitante = jogadores.get(numVisitante);
				}
			}
		}
	}
	
	private void CriarJogosReturno() {
		
		List<Jogo> temp = new LinkedList<Jogo>();
		Jogo jogo;
		
		Iterator<Jogo> iter = jogos.iterator();
		while(iter.hasNext()) {
			jogo = (Jogo)iter.next();
			temp.add(new Jogo(jogo.Visitante(), jogo.Mandante()));
		}
		
		jogos.addAll(temp);		
	}
	
	private Boolean CriarJogo(Jogador mandante, Jogador visitante){
		
		Boolean sucesso = false;		
					
		//Verifica se o jogo já ocorreu
		if(tabela[mandante.Matricula()-1][visitante.Matricula()-1] == 0 && tabela[visitante.Matricula()-1][mandante.Matricula()-1] == 0){
			
			//Se o jogo não ocorreu atualiza a tabela
			tabela[mandante.Matricula()-1][visitante.Matricula()-1] = (short)(jogos.size()+1);
			
			//Cria o jogo
			AtualizarTemposEspera();
			jogos.add(new Jogo(mandante, visitante));
			
			sucesso = true;	
		}
		
		return sucesso;
	}
	
	private short NroJogosTurno() {
		return (short)((jogadores.size() * (jogadores.size() - 1))/2);
	}
	
	private void AtualizarTemposEspera(){
		
		Iterator<Jogador> iter = jogadores.iterator();
		
		while(iter.hasNext())
			iter.next().AdicionarTempoAusente();		
	}	
	
	//Salva os jogos no momento da CRIAÇÃO do campeonato.
	private void SalvarJogos() {
		
		Jogo jogoAtual;
		
		Iterator<Jogo> iter = jogos.iterator();
		while(iter.hasNext()) {
			
			jogoAtual = (Jogo)iter.next();
			
			jogoAtual.SalvarNovo(this.id, contexto);
		}
		
	}
	
	public List<Jogador> ObterClassificacao() {
		
		Collections.sort(jogadores, new Comparator<Jogador>() 
		{ 
			public int compare(Jogador j1, Jogador j2) {
				
				int retorno = 0;
				
				if(j1.Pontos() > j2.Pontos())
					retorno = -1;
				else if(j1.Pontos() < j2.Pontos())
					retorno = 1;
				else if(j1.Vitorias() > j2.Vitorias())
					retorno = -1;
				else if(j1.Vitorias() < j2.Vitorias())
					retorno = 1;
				else if(j1.Saldo() > j2.Saldo())
					retorno = -1;
				else if(j1.Saldo() < j2.Saldo())
					retorno = 1;
				else if(j1.GolsPro() > j2.GolsPro())
					retorno = -1;
				else if(j1.GolsPro() < j2.GolsPro())
					retorno = 1;
				else if(j1.Jogos() > j2.Jogos())
					retorno = 1;
				else if(j1.Jogos() < j2.Jogos())
					retorno = -1;
				
				return retorno;
			}
		});
		
		
		return this.jogadores;
		
	}
}
