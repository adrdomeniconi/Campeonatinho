package com.joao.mysoccerstandingsv2.Negocio;

import java.util.Iterator;
import java.util.List;

import android.content.Context;
import com.joao.mysoccerstandingsv2.Negocio.Utils.Resultado;
import com.joao.mysoccerstandingsv2.Negocio.Persistencia.CarregarJogador;
import com.joao.mysoccerstandingsv2.Negocio.Persistencia.SalvarJogador;

public class Jogador implements Comparable<Jogador>  {
	
	private long idJogador;
	private String nomeJogador;
	
	//Identificador do jogador no campeonato atual. Essa informação não é aramzenada em banco pois é utilizada 
	//apenas no momento de gerar a tabela.
	private byte matricula;
	
	//Dados no campeonato
	private short jogos;
	private short vitorias;
	private short derrotas;
	private short empates;
	private short golsPro;
	private short golsContra;
	private short pontos;
	
	//Tempo sem jogar
	private byte tempoAusente;
	
	//Construtor
	public Jogador(long id, byte matricula, String nome) {
		this.idJogador = id;
		this.matricula = matricula;
		this.nomeJogador = nome;
	}
	
	public String Nome(){
		return this.nomeJogador;
	}
	
	public long Id() {
		return this.idJogador;
	}
	
	public void Id(long id) {
		this.idJogador = id;
	}
	
	public byte Matricula() {
		return this.matricula;
	}
	
	public short Derrotas() {
		return this.derrotas;
	}
	
	public short Empates() {
		return this.empates;
	}
	
	public short GolsContra() {
		return this.golsContra;
	}
	
	public short GolsPro() {
		return this.golsPro;
	}
	
	public byte TempoAusente() {
		return tempoAusente;
	}
	
	public short Pontos() {
		return this.pontos;
	}
	
	public short Vitorias() {
		return this.vitorias;
	}
	
	public short Jogos() {
		return this.jogos;
	}
	
	public void AdicionarTempoAusente() {
		tempoAusente++;
	}
	
	public void ZerarTempoAusente() {
		this.tempoAusente = 0;
	}

	@Override
	//Compara a lista de jogadores de acordo com o maior tempo sem jogar e menor numero de jogos.
	public int compareTo(Jogador j) {
		int retorno = 0;
		
		if(tempoAusente > j.tempoAusente )
			retorno = -1;
		else if(tempoAusente < j.tempoAusente)
			retorno = 1;
		else if(jogos > j.jogos)
			retorno = -1;
		else if(jogos < j.jogos)
			retorno = 1;
		
		return retorno;
	}

	public void AtualizarDados(Resultado resultado, short golsPro, short golsContra) {
		this.golsPro += golsPro;
		this.golsContra += golsContra;
		this.jogos += 1;
		
		this.pontos += resultado.Pontos();		
		switch(resultado) {
			case DERROTA:
				this.derrotas++;
				break;
			case EMPATE:
				this.empates++;
				break;
			case VITORIA:
				this.vitorias++;
		}
	}
	
	public short Saldo() {
		return (short)(this.golsPro - this.golsContra);
	}
	
	public long Salvar(Context contexto) {
		
		List<Jogador> jogadoresSalvos;
		boolean existe = false;
		long result;
		Jogador jogadorAtual;

		//É preciso verificar se o nome inserido é duplicado.
		CarregarJogador carregar = new CarregarJogador(contexto);
		jogadoresSalvos = carregar.CarregarJogadoresSalvos();

		Iterator<Jogador> iter = jogadoresSalvos.iterator();
		while(!existe && iter.hasNext()) {
			jogadorAtual = iter.next();
			if(jogadorAtual.Nome().equals(this.nomeJogador))
				existe = true;
		}

		if(!existe) {		
			SalvarJogador sj = new SalvarJogador(contexto);		
			result = sj.salvarNovoJogador(this);

			if(result > 0)
				this.idJogador = result;
		}
		else
			result = Erros.ERRO_NOME_DUPLICADO.getCodigo();
		
		return result;
	}
	
	public String toString() {
		return this.nomeJogador;
	}
	
}
