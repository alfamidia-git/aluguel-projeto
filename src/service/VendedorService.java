package service;

import java.util.List;
import java.util.Scanner;

import exception.SistemaException;
import model.Veiculo;
import model.Vendedor;
import repository.Repository;
import util.Normaliza;

public class VendedorService {

	Scanner sc;
	Repository<Vendedor> repository = new Repository<>();


	public VendedorService(Scanner sc) {
		this.sc = sc;

		repository.salvar(new Vendedor("João", "joao@vendedor.com", "Porto Alegre", "123", 2500));
		repository.salvar(new Vendedor("Maria", "maria@vendedor.com", "Porto Alegre", "12345", 3000));
		repository.salvar(new Vendedor("José", "jose@vendedor.com", "Porto Alegre", "12", 2000));
	}

	public Vendedor confereEmail(String email) {

		List<Vendedor> vendedoresCadastrados = repository.buscarTodos();

		Vendedor vendedor = vendedoresCadastrados.stream()
							.filter(v -> v.getEmail().equals(Normaliza.normalizaEmail(email)))
							.findFirst().orElse(null);
		
//		for(Vendedor vendedor : vendedoresCadastrados) {
//			if(vendedor.getEmail().equals(Normaliza.normalizaEmail(email))) {
//				return vendedor;
//			}
//		}

		return vendedor;
	}

	public boolean conferirSenha(Vendedor vendedorParam, String senha) {
		Vendedor vendedor = repository.buscarPorId(vendedorParam.getId());

		return vendedor.getSenha().equals(senha);
	}

	public void verSalario(Vendedor vendedor) {
		System.out.println("Seu salário atual é: " + vendedor.getSalario());
	}

	public void mostrarTodosVendedores() {
		List<Vendedor> vendedores = repository.buscarTodos();

		vendedores.forEach(v -> System.out.println(v.getId() + " - " + v.getNome()));
		
//		for(Vendedor vendedor : vendedores) {
//			System.out.println(vendedor.getId() + " - " + vendedor.getNome());
//		}
	}

	public void salvarVeiculo(Veiculo veiculo, Integer idVendedor) throws SistemaException {

		Vendedor vendedor = repository.buscarPorId(idVendedor);

		if(vendedor == null) {
			throw new SistemaException("Vendedor não encontrado!");
		}

		vendedor.getVeiculosAlugado().add(veiculo);

		repository.salvar(vendedor);
	}

	public void mostrarAlugueisVeiculos(Vendedor vendedor) {

		List<Veiculo> veiculos = vendedor.getVeiculosAlugado();

		veiculos.forEach(v -> System.out.println(v));
//		for(Veiculo veiculo : veiculos) {
//			System.out.println(veiculo);
//		}
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
