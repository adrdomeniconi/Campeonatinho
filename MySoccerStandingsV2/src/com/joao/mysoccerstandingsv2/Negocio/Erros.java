package com.joao.mysoccerstandingsv2.Negocio;

public enum Erros {
	
	ERRO_BANCO (-1, "N�o foi poss�vel conectar com o banco de dados."),
	ERRO_NOME_DUPLICADO (-10, "J� existe cadastro com esse nome. Favor inserir um nome diferente ou excluir o nome cadastrado.");

	private int codigo;
	private String msg;

	Erros(int codigo, String msg) {
		this.codigo = codigo;
		this.msg = msg;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getMsg() {
		return msg;
	}
}
