import java.util.Scanner;

import menu.Menu;
import repository.AdministradorRepository;
import repository.ClienteRepository;
import repository.VeiculoRepository;
import repository.VendedorRepository;

public class Principal {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		AdministradorRepository admRepository = new AdministradorRepository();
		ClienteRepository clienteRepository = new ClienteRepository();
		VendedorRepository vendedorRepository = new VendedorRepository();
		VeiculoRepository veiculoRepository = new VeiculoRepository();
		
		boolean continua = true;
		do {
			Menu.menu1();
			int opcao1 = sc.nextInt();
			
			switch(opcao1) {
			case 1:
				Menu.menuCliente1();
				int opcao2 = sc.nextInt();
				break;
			case 2:
				Menu.menuVendedor1();
				opcao2 = sc.nextInt();
				break;
			case 3: 
				Menu.menuAdministrador();
				opcao2 = sc.nextInt();
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
