package service;

import java.util.List;
import java.util.Scanner;

import model.Cliente;
import model.Vendedor;
import repository.VendedorRepository;

public class VendedorService {
	
	Scanner sc;
	VendedorRepository repository = new VendedorRepository();
	
	
	public VendedorService(Scanner sc) {
		this.sc = sc;
		
		repository.salvar(new Vendedor("João", "joao@vendedor.com", "Porto Alegre", "123", 2500));
		repository.salvar(new Vendedor("Maria", "maria@vendedor.com", "Porto Alegre", "12345", 3000));
		repository.salvar(new Vendedor("José", "jose@vendedor.com", "Porto Alegre", "12", 2000));
	}
	
	public Vendedor confereEmail(String email) {
		
		List<Vendedor> vendedoresCadastrados = repository.buscarTodos();
		
		for(Vendedor vendedor : vendedoresCadastrados) {
			if(vendedor.getEmail().equals(email)) {
				return vendedor;
			}
		}
		
		return null;
	}

	public boolean conferirSenha(Vendedor vendedorParam, String senha) {
		Vendedor vendedor = repository.buscarPorId(vendedorParam.getId());
		
		return vendedor.getSenha().equals(senha);
	}
	
	public void verSalario(Vendedor vendedor) {
		System.out.println("Seu salário atual é: " + vendedor.getSalario());
	}
}
