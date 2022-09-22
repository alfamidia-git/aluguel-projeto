package model;

import java.util.ArrayList;
import java.util.List;

public class Vendedor extends Pessoa{

	public static final double COMISSAO  = 0.1;
	private double salario;
	List<Veiculo> veiculosAlugado;

	public Vendedor(String nome, String email, String cidade, String senha, double salario) {
		super(nome, email, cidade, senha);
		this.salario = salario;
		this.veiculosAlugado = new ArrayList<>();
	}
	
	public Vendedor(int id, String nome, String email, String senha, double salario) {
		super(id, nome, email, senha);
		this.salario = salario;
		this.veiculosAlugado = new ArrayList<>();
	}

	public double getSalario() {
		return this.salario;
	}

	public List<Veiculo> getVeiculosAlugado() {
		return veiculosAlugado;
	}

	public void setVeiculosAlugado(List<Veiculo> veiculosAlugado) {
		this.veiculosAlugado = veiculosAlugado;
	}


}
