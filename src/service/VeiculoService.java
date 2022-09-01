package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Veiculo;
import model.Veiculo.Status;
import repository.VeiculoRepository;

public class VeiculoService {

	Scanner sc;
	VeiculoRepository repository = new VeiculoRepository();
	
	public VeiculoService(Scanner sc) {
		this.sc = sc;
		repository.salvar(new Veiculo("I30", "Hyndai", "Preto", "IXI9076", "carro", 135));
		repository.salvar(new Veiculo("HB20", "Hyndai", "Branco", "IXI8076", "carro", 145));
		repository.salvar(new Veiculo("CG 150", "Honda", "Preto", "IXI7076", "moto", 60));
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
		List<Veiculo> todosVeiculos = this.repository.buscarTodos();
		
		for(Veiculo veiculo : todosVeiculos) {
			if(veiculo.getStatus() == Status.LIVRE) {
				System.out.println(veiculo);
		}
		}
	
	}
	
	public Veiculo alugarVeiculoPorID(int id) {
		
		Veiculo veiculo = this.repository.buscarPorId(id);
		
		veiculo.setStatus(Status.ALUGADO);
		
		this.repository.salvar(veiculo);
		
		return veiculo;
	}
	
	public Veiculo devolverVeiculo(int id) {
		Veiculo veiculo = this.repository.buscarPorId(id);		
		veiculo.setStatus(Status.LIVRE);		
		this.repository.salvar(veiculo);
		
		return veiculo;
	}
}
