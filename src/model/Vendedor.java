package model;

public class Vendedor extends Pessoa{

	private final double COMISSAO  = 0.1;
	private double salario;
	
	public Vendedor(String nome, String email, String cidade, String senha) {
		super(nome, email, cidade, senha);
	}

}
