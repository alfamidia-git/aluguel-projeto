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
import repository.Repository;
import repository.VeiculoRepository;

public class VeiculoService {

	Scanner sc;
	Repository<Veiculo> repository = new Repository<>();
	
	VeiculoRepository veiculoRepository = new VeiculoRepository();

	public VeiculoService(Scanner sc) {
		this.sc = sc;
	}

	public void cadastrarVeiculo() {
		System.out.println("Digite o modelo do veiculo: ");
		String modelo = sc.nextLine();

		System.out.println("Digite a marca do veiculo: ");
		String marca = sc.nextLine();

		System.out.println("Digite a cor do veiculo: ");
		String cor = sc.nextLine();

		System.out.println("Digite a placa do veiculo: ");
		String placa = sc.nextLine();

		System.out.println("Digite o segmento do veiculo: ");
		String tipo = sc.nextLine();

		System.out.println("Digite o valor de locação do veiculo: ");
		double valor = sc.nextDouble();

		Veiculo veiculo = new Veiculo(modelo, marca, cor, placa, tipo, valor);

		this.repository.salvar(veiculo);

		System.out.println("Você cadastrou o veiculo!!");
	}

	public void buscarTodosVeiculosLivres(){		
		
		List<Veiculo> todosVeiculos = this.veiculoRepository.buscarOnde("STATUS = 'LIVRE' ");

		todosVeiculos.forEach(v -> System.out.println(v));

	}

	public Veiculo alugarVeiculoPorID(int id) throws SistemaException {

		Veiculo veiculo = this.veiculoRepository.buscarPorID(id);

		if(veiculo == null) {
			throw new SistemaException("Veículo não encontrado!");
		}

		veiculo.setStatus(Status.ALUGADO);

		try {
			PreparedStatement ps = this.repository.prepararSQL("update veiculos set status = 'ALUGADO' where id = ?");
			ps.setInt(1, id);
			
			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar veículo: " + e.getMessage());
		}

		return veiculo;
	}

	public Veiculo devolverVeiculo(Cliente cliente, int id) throws SistemaException {
		
		Veiculo veiculo = this.veiculoRepository.buscarPorID(id);
		
		if(veiculo == null) {
			throw new SistemaException("Veículo não encontrado!");
		}
		
		if(!cliente.getVeiculos().contains(veiculo)) {
			throw new SistemaException("Você não possui este veículo!");
		}

		veiculo.setStatus(Status.LIVRE);
		this.veiculoRepository.atualizar(veiculo);
		
		return veiculo;
	}
}
