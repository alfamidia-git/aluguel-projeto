package service;

import java.util.List;
import java.util.Scanner;

import exception.SistemaException;
import model.Cliente;
import model.Veiculo;
import repository.Repository;
import util.Normaliza;

public class ClienteService {

	Scanner sc;
	Repository<Cliente> repository = new Repository<>();

	public ClienteService(Scanner sc) {
		this.sc = sc;
		this.repository.salvar(new Cliente("marlon", "marlon@alfamidia.com.br", "Porto Alegre", "123"));
	}


	public Cliente confereEmail(String email) {

		List<Cliente> clientesCadastrados = repository.buscarTodos();
		
		Cliente cliente = clientesCadastrados.stream()
		.filter(c -> c.getEmail().equals(Normaliza.normalizaEmail(email)))
		.findFirst().orElse(null);
		

		if(cliente != null) {
			return cliente;
		}
		
		return this.cadastrarCliente(email);
	}

	private Cliente cadastrarCliente(String email) {

		System.out.println("Digite seu nome: ");
		String nome = sc.nextLine();
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

		veiculosAlugados.forEach( v -> System.out.println(v));
		
//		for(Veiculo veiculo : veiculosAlugados) {
//			System.out.println(veiculo);
//		}
	}

	public void removerVeiculo(Cliente clienteParam, Veiculo veiculoParam) throws SistemaException {
		Cliente cliente = this.repository.buscarPorId(clienteParam.getId());

		if(cliente == null) {
			throw new SistemaException("Cliente não encontrado!");
		}
		cliente.getVeiculos().remove(veiculoParam);

		this.repository.salvar(cliente);
	}

}
