package model;

public abstract class Pessoa {
	private String nome;
	private String email;
	private String cidade;
	private String senha;
	
	public Pessoa(String nome, String email, String cidade, String senha) {
		super();
		this.nome = nome;
		this.email = email;
		this.cidade = cidade;
		this.senha = senha;
	}
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
}
