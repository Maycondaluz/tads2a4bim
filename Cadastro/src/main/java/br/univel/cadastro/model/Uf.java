package br.univel.cadastro.model;

public enum Uf {

	PR("Paran�"), SP("Sao paulo"), SC("Santa Catarina");

	private String nome;

	public String getNome() {
		return nome;
	}

	private Uf(String nome) {
		this.nome = nome;
	}
}
