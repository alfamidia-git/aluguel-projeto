package service;

import java.util.List;
import java.util.Scanner;

import model.Cliente;
import model.Veiculo;
import repository.ClienteRepository;

public class ClienteService {

	Scanner sc;
	ClienteRepository repository = new ClienteRepository();
	
	public ClienteService(Scanner sc) {
		this.sc = sc;
		this.repository.salvar(new Cliente("marlon", "marlon@alfamidia.com.br", "Porto Alegre", "123"));
	}
	
	
	public Cliente confereEmail(String email) {
		
		List<Cliente> clientesCadastrados = repository.buscarTodos();
		
		for(Cliente cliente : clientesCadastrados) {
			if(cliente.getEmail().equals(email)) {
				return cliente;
			}
		}
		
		
		return this.cadastrarCliente();
	}
	
	private Cliente cadastrarCliente() {
		
		System.out.println("Digite seu nome: ");
		String nome = sc.nextLine();
		System.out.println("Digite seu email: ");
		String email = sc.nextLine();
		System.out.println("Digite sua cidade: ");
		String cidade = sc.nextLine();
		System.out.println("Digite uma senha: ");
		String senha = sc.nextLine();
		
		
		Cliente cliente = new Cliente(nome, email, cidade, senha);
		
		this.repository.salvar(cliente);
		
		return cliente;
	}
	
	public boolean conferirSenha(Cliente clienteParam, String senha) {
		Cliente cliente = repository.buscarPorId(clienteParam.getId());
		
		return cliente.getSenha().equals(senha);
	}
	
	public void alugarVeiculo(Cliente cliente, Veiculo veiculo) {
		cliente.getVeiculos().add(veiculo);
		this.repository.salvar(cliente);
	}
	
	public void buscarCarrosAlugados(Cliente cliente) {
		List<Veiculo> veiculosAlugados = cliente.getVeiculos();
		
		for(Veiculo veiculo : veiculosAlugados) {
			System.out.println(veiculo);
		}
	}
	
	public void removerVeiculo(Cliente clienteParam, Veiculo veiculoParam) {
		Cliente cliente = this.repository.buscarPorId(clienteParam.getId());
		
		cliente.getVeiculos().remove(veiculoParam);
		
		this.repository.salvar(cliente);
	}
	
}
