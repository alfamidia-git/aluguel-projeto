import java.util.Scanner;

import menu.Menu;
import model.Cliente;
import model.Veiculo;
import repository.AdministradorRepository;
import repository.ClienteRepository;
import repository.VeiculoRepository;
import repository.VendedorRepository;
import service.AdminService;
import service.ClienteService;
import service.VeiculoService;

public class Principal {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		ClienteService clienteService = new ClienteService(sc);
		VeiculoService veiculoService = new VeiculoService(sc);
		AdminService adminService = new AdminService(sc, veiculoService);
		
		boolean continua = true;
		do {
			Menu.menu1();
			int opcao1 = sc.nextInt();
			sc.nextLine();
			
			switch(opcao1) {
			case 1:
				Menu.menuCliente1();
				String email = sc.nextLine();
				Cliente cliente = clienteService.confereEmail(email);
				boolean senhaCorreta = false;
				for(int i = 0; i < 3; i++) {
					System.out.println("Agora digite a sua senha: ");
					String senha = sc.nextLine();
					senhaCorreta = clienteService.conferirSenha(cliente, senha);
					if(!senhaCorreta) {
						System.out.println("Senha incorreta, tente novamente!!");
					}else {
						break;
					}
				}
				
				if(!senhaCorreta) {
					break;
				}
				
				Menu.menuCliente2();
				int opcao2 = sc.nextInt();
				
				if(opcao2 == 1) {
					System.out.println("Digite o número referente ao carro desejado: ");
					veiculoService.buscarTodosVeiculosLivres();
					int opcaoCarro = sc.nextInt();
					Veiculo veiculo = veiculoService.alugarVeiculoPorID(opcaoCarro);
					clienteService.alugarVeiculo(cliente, veiculo);
				}
				
				break;
			case 2:
				Menu.menuVendedor1();
				opcao2 = sc.nextInt();
				break;
			case 3: 
				Menu.menuAdministrador();
				opcao2 = sc.nextInt();
				adminService.confereEntrada(opcao2);
				
				break;
			case 0:
				continua = false;
				break;
			default: 
				System.out.println("Alternativa inválida. Tente novamente!!!");
				break;
			}
		} while (continua);

	}

}
