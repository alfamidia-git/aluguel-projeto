package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;

import exception.SistemaException;
import model.Veiculo;
import model.Vendedor;
import model.Veiculo.Status;
import model.Veiculo.Tipo;
import repository.Repository;
import repository.VendedorRepository;
import util.Normaliza;

public class VendedorService {

	Scanner sc;
	Repository<Vendedor> repository = new Repository<>();
	private VendedorRepository vendedorRepository = new VendedorRepository();


	public VendedorService(Scanner sc) {
		this.sc = sc;

		repository.salvar(new Vendedor("João", "joao@vendedor.com", "Porto Alegre", "123", 2500));
		repository.salvar(new Vendedor("Maria", "maria@vendedor.com", "Porto Alegre", "12345", 3000));
		repository.salvar(new Vendedor("José", "jose@vendedor.com", "Porto Alegre", "12", 2000));
	}

	public Vendedor confereEmail(String email) {

		List<Vendedor> vendedoresCadastrados = this.vendedorRepository.buscar();

		Vendedor vendedor = vendedoresCadastrados.stream()
							.filter(v -> v.getEmail().equals(Normaliza.normalizaEmail(email)))
							.findFirst().orElse(null);

		return vendedor;
	}

	public boolean conferirSenha(Vendedor vendedorParam, String senha) {
		Vendedor vendedor = this.vendedorRepository.buscarPorID(vendedorParam.getId());

		return vendedor.getSenha().equals(senha);
	}

	public void verSalario(Vendedor vendedor) {
		System.out.println("Seu salário atual é: " + vendedor.getSalario());
	}

	public void mostrarTodosVendedores() {
		List<Vendedor> vendedores = this.vendedorRepository.buscar();

		vendedores.forEach(v -> System.out.println(v.getId() + " - " + v.getNome()));

	}

	public void salvarVeiculo(Veiculo veiculo, Integer idVendedor) throws SistemaException {

		Vendedor vendedor = this.vendedorRepository.buscarPorID(idVendedor);

		if(vendedor == null) {
			throw new SistemaException("Vendedor não encontrado!");
		}

		vendedor.getVeiculosAlugado().add(veiculo);
		
		try {
			PreparedStatement ps = this.repository
					.prepararSQL("INSERT INTO vendedores_veiculos(vendedor_id, veiculo_id, data_locacao) values (?, ?, ?)");
			ps.setInt(1, idVendedor);
			ps.setInt(2, veiculo.getId());
			ps.setDate(3, new java.sql.Date(	new Date().getTime()	));

			ps.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao salvar aluguel do veiculo: " + e.getMessage());
		}

		this.vendedorRepository.atualizar(vendedor);
	}

	public void mostrarAlugueisVeiculos(Vendedor vendedor) {

		List<Veiculo> veiculos = new ArrayList<>();
		
		String sql = "SELECT v.* from vendedores_veiculos "
				+ 			"inner join veiculos as v "
				+ 			"on v.id = vendedores_veiculos.veiculo_id "
				+ "where vendedor_id = " + vendedor.getId();
		
		try {
			ResultSet result = this.repository.select(sql);
			
			while(result.next()) {
				String modelo = result.getString("modelo");
				String marca = result.getString("marca");
				String placa = result.getString("placa");
				String cor = result.getString("cor");
				int idR = result.getInt("id");
				Status status = Status.valueOf(result.getString("status"));
				Tipo tipo = Tipo.valueOf(result.getString("tipo"));
				double valorLocacao = result.getDouble("valorLocacao");

				veiculos.add(new Veiculo(idR, modelo, marca, cor, placa, tipo, status, valorLocacao));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar veiculos alugado pelo vendedor "+ e.getMessage());
		}

		veiculos.forEach(v -> System.out.println(v));
	}

	public void verSalarioComComissao(Vendedor vendedor) {

		List<Veiculo> veiculos = vendedor.getVeiculosAlugado();
		double totalVendas = veiculos.stream().mapToDouble(v -> v.getValorLocacao()).sum();

//		for(Veiculo veiculo : veiculos) {
//			totalVendas += veiculo.getValorLocacao();
//		}

		double comissao = totalVendas * Vendedor.COMISSAO;

		System.out.println("Seu salário atual é: " + vendedor.getSalario());
		System.out.println("Sua comissão é: " + comissao);
		System.out.println("Seu salário + comissão é: " + (vendedor.getSalario() + comissao));
	}

	public void cadastrarVendedor() {
		System.out.println("Digite seu nome: ");
		String nome = sc.nextLine();
		System.out.println("Digite seu email: ");
		String email = sc.nextLine();
		System.out.println("Digite sua cidade: ");
		String cidade = sc.nextLine();
		System.out.println("Digite uma senha: ");
		String senha = sc.nextLine();
		System.out.println("Digite o salário: ");
		double salario = sc.nextDouble();

		Vendedor vendedor = new Vendedor(nome, email, cidade, senha, salario);

		repository.salvar(vendedor);
	}
}
