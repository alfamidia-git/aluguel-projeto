package service;

import java.util.List;
import java.util.Scanner;

import exception.SistemaException;
import model.Administrador;
import model.Veiculo;
import model.Vendedor;
import repository.AdministradorRepository;
import repository.VeiculoRepository;
import util.Normaliza;

public class AdminService {
	
	Scanner sc;
	VeiculoService veiculoService;
	AdministradorRepository repository = new AdministradorRepository();
	private VendedorService vendedorService;
	
	public AdminService(Scanner sc, VeiculoService veiculoService, VendedorService vendedorService) {
		this.sc = sc;
		this.repository.salvar(new Administrador("admin", "admin@admin.com", "poa", "1111"));
		this.veiculoService = veiculoService;
		this.vendedorService = vendedorService;
	}
	
	public void confereEntrada(int entrada) throws SistemaException {
		sc.nextLine();
		
		if(entrada == 1) {
			veiculoService.cadastrarVeiculo();
		}else if(entrada == 2) {
			this.removerVeiculo();
		}else if(entrada == 3) {
			vendedorService.cadastrarVendedor();
		}else if(entrada == 4) {
			this.removerVendedor();
		}
	}
	
	public void removerVeiculo() throws SistemaException {
		System.out.println("Todos veículos cadastrados e livres no sistema: ");
		veiculoService.buscarTodosVeiculosLivres();
		int opcaoVeiculo = sc.nextInt();
		
		Veiculo veiculo = veiculoService.repository.buscarPorId(opcaoVeiculo);
		
		if(veiculo == null) {
			throw new SistemaException("Veiculo não encontrado");
		}
		veiculoService.repository.removerPorId(opcaoVeiculo);
		
		System.out.println("Veiculo removido com sucesso!!");
	}
	
	public void removerVendedor() throws SistemaException {
		System.out.println("Todos vendedores cadastrados no sistema: ");
		vendedorService.mostrarTodosVendedores();
		int opcaoVendedor = sc.nextInt();
		
		Vendedor vendedor = vendedorService.repository.buscarPorId(opcaoVendedor);
		if(vendedor == null) {
			throw new SistemaException("Vendedor não encontrado!");
		}
		
		vendedorService.repository.removerPorId(opcaoVendedor);
		
		System.out.println("Vendedor removido com sucesso!!");
	}

	public Administrador confereEmail(String email) {
		List<Administrador> adminCadastrados = repository.buscarTodos();
		
		for(Administrador admin : adminCadastrados) {
			if(admin.getEmail().equals(Normaliza.normalizaEmail(email))) {
				return admin;
			}
		}
		
		return null;
	}

	public boolean conferirSenha(Administrador adminParam, String senha) {
		Administrador admin = repository.buscarPorId(adminParam.getId());
		
		return admin.getSenha().equals(senha);
	}
}
