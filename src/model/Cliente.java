package model;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa{

	List<Veiculo> veiculos;

	public Cliente(String nome, String email, String cidade, String senha) {
		super(nome, email, cidade, senha);
		this.veiculos = new ArrayList();
	}
	
	public Cliente(int id, String nome, String email, String senha) {
		super(id, nome, email, senha);
		this.veiculos = new ArrayList();
	}

	public List<Veiculo> getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}

}
