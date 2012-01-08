package com.joao.mysoccerstandingsv2.Negocio;

import android.content.Context;

import com.joao.mysoccerstandingsv2.Negocio.Persistencia.SalvarJogo;
import com.joao.mysoccerstandingsv2.Negocio.Utils.Resultado;

public class Jogo {
	
	private long idJogo;
	
	private Jogador mandante;
	private Jogador visitante;
	
	private byte golsMandante;
	private byte golsVisitante;
	
	private Boolean realizado;
	
	//Construtor
	public Jogo(Jogador mandante, Jogador visitante) {
		
		mandante.ZerarTempoAusente();
		visitante.ZerarTempoAusente();
		
		this.mandante = mandante;
		this.visitante = visitante;
	}
	
	public long Id() {
		return this.idJogo;
	}
	
	public void Id(long id) {
		this.idJogo = id;
	}
	
	public Jogador Mandante() {
		return this.mandante;
	}
	
	public Jogador Visitante() {
		return this.visitante;
	}
	
	public byte GolsMandante() {
		return this.golsMandante;
	}
	
	public byte GolsVisitante() {
		return this.golsVisitante;
	}
	
	public Boolean Realizado() {
		return this.realizado;
	}
	
	public void AdicionarResultado(byte golsMandante, byte golsVisitante) {
		
		this.realizado = true;
		this.golsMandante = golsMandante;
		this.golsVisitante = golsVisitante;
		
		mandante.AtualizarDados(golsMandante > golsVisitante ? Resultado.VITORIA : 
							   (golsMandante == golsVisitante ? Resultado.EMPATE : 
								Resultado.DERROTA), golsMandante, golsVisitante);
		
		visitante.AtualizarDados(golsVisitante > golsMandante ? Resultado.VITORIA : 
			   					(golsVisitante == golsMandante ? Resultado.EMPATE : 
			   					 Resultado.DERROTA), golsVisitante, golsMandante);
		
	}
	
	//Método utilizado apenas na CRIAÇÃO do campeonato.
	public void SalvarNovo(long idCampeonato, Context contexto) {		
		new SalvarJogo(contexto).salvarNovoJogo(this, idCampeonato);		
	}
	
	public void AtualizarJogo(Context contexto) {		
		new SalvarJogo(contexto).atualizarJogo(this);		
	}
	
	public String toString(){
		return this.mandante.Nome() + " x " + this.visitante.Nome();
	}
}
