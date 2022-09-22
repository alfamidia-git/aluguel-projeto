package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import exception.SistemaException;
import model.Cliente;
import model.Veiculo;
import model.Veiculo.Status;
import model.Veiculo.Tipo;
import repository.ClienteRepository;
import repository.Repository;
import util.Normaliza;

public class ClienteService {

	Scanner sc;
	Repository<Cliente> repository = new Repository<>();

	ClienteRepository clienteRepository = new ClienteRepository();

	public ClienteService(Scanner sc) {
		this.sc = sc;
	}

	public Cliente confereEmail(String email) {

		List<Cliente> clientesCadastrados = this.clienteRepository.buscar();

		Cliente cliente = clientesCadastrados.stream().filter(c -> c.getEmail().equals(Normaliza.normalizaEmail(email)))
				.findFirst().orElse(null);

		if (cliente != null) {
			try {
				String sql = "SELECT veiculos.* FROM `clientes_veiculos` "
						+ "	inner join veiculos on veiculos.id = veiculo_id WHERE cliente_id = ? and estaAtivo = true";

				PreparedStatement ps = this.repository.prepararSQL(sql);
				ps.setInt(1, cliente.getId());

				ResultSet result = ps.executeQuery();

				while (result.next()) {
					String modelo = result.getString("modelo");
					String marca = result.getString("marca");
					String placa = result.getString("placa");
					String cor = result.getString("cor");
					int idR = result.getInt("id");
					Status status = Status.valueOf(result.getString("status"));
					Tipo tipo = Tipo.valueOf(result.getString("tipo"));
					double valorLocacao = result.getDouble("valorLocacao");

					if (cliente.getVeiculos() == null) {
						cliente.setVeiculos(new ArrayList<>());
					}

					cliente.getVeiculos().add(new Veiculo(idR, modelo, marca, cor, placa, tipo, status, valorLocacao));
				}

			} catch (SQLException e) {
				System.out.println("Erro ao buscar veiculos: " + e.getMessage());
			}
		}

		if (cliente != null) {
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

		this.clienteRepository.salvar(cliente);
		
		try {
			Thread.sleep(2000l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return cliente;
	}

	public boolean conferirSenha(Cliente clienteParam, String senha) {
		Cliente cliente = this.clienteRepository.buscarPorID(clienteParam.getId());

		return cliente.getSenha().equals(senha);
	}

	public void alugarVeiculo(Cliente cliente, Veiculo veiculo) {
		cliente.getVeiculos().add(veiculo);

		try {
			PreparedStatement ps = this.repository
					.prepararSQL("INSERT INTO clientes_veiculos(cliente_id, veiculo_id, estaAtivo) values (?, ?, ?)");
			ps.setInt(1, cliente.getId());
			ps.setInt(2, veiculo.getId());
			ps.setBoolean(3, true);

			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao salvar aluguel do veiculo: " + e.getMessage());
		}
	}

	public void buscarCarrosAlugados(Cliente cliente) {
		List<Veiculo> veiculosAlugados = cliente.getVeiculos();

		veiculosAlugados.forEach(v -> System.out.println(v));

//		for(Veiculo veiculo : veiculosAlugados) {
//			System.out.println(veiculo);
//		}
	}

	public void removerVeiculo(Cliente clienteParam, Veiculo veiculoParam) throws SistemaException {
		Cliente cliente = this.clienteRepository.buscarPorID(clienteParam.getId());

		if (cliente == null) {
			throw new SistemaException("Cliente não encontrado!");
		}

		String sql = "update clientes_veiculos set estaAtivo = false where cliente_id = ? and veiculo_id = ?";

		try {
			PreparedStatement ps = this.repository.prepararSQL(sql);
			ps.setInt(1, clienteParam.getId());
			ps.setInt(2, veiculoParam.getId());

			ps.execute();

		} catch (SQLException e) {
			System.out.println("Erro ao desvincular veiculo do cliente " + e.getMessage());
		}

		this.repository.salvar(cliente);
	}

}
