package service;

import java.util.Scanner;

import model.Administrador;
import model.Veiculo;
import repository.AdministradorRepository;
import repository.VeiculoRepository;

public class AdminService {
	
	Scanner sc;
	VeiculoService veiculoService;
	AdministradorRepository repository = new AdministradorRepository();
	
	public AdminService(Scanner sc, VeiculoService veiculoService) {
		this.sc = sc;
		this.repository.salvar(new Administrador("admin", "admin@admin.com", "poa", "1111"));
		this.veiculoService = veiculoService;
	}
	
	public void confereEntrada(int entrada) {
		sc.nextLine();
		
		if(entrada == 1) {
			veiculoService.cadastrarVeiculo();
		}
	}
}
