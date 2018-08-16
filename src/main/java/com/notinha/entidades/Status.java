package com.notinha.entidades;

public enum Status {
	ABERTA("Aberta"), PAGA("Paga");

	private String descricao;

	Status(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
