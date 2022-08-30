package menu;

public class Menu {

	
	public static void  menu1() {
		System.out.println("===============================");
		System.out.println("Bem vindo ao sisteme de aluguel de veículos");
		System.out.println("Digite a opção correspondente: ");
		System.out.println("1 - Cliente");
		System.out.println("2 - Vendedor");
		System.out.println("3 - Administrador");
		System.out.println("0 - Para sair do sistema");
	}
	
	
	public static void menuCliente1() {
		System.out.println("Você já tem cadastro? Digite seu e-mail:");
		System.out.println("Digite 2 se você não tem cadastro");
	}
	public static void menuCliente2() {
		System.out.println("Digite a opção desejada: ");
		System.out.println("1 - Alugar um veiculo");
		System.out.println("2 - Devolver um veiculo");
	}
	
	
	public static void menuVendedor1() {
		System.out.println("Digite a opção desejada: ");
		System.out.println("1 - Ver os carros que você alugou");
		System.out.println("2 - Ver seu salário");
		System.out.println("3 - Ver seu salário com a comissão atual");
	}
	
	public static void menuAdministrador() {
		System.out.println("Digite a opção desejada: ");
		System.out.println("1 - Cadastrar um veículo");
		System.out.println("2 - Remover um veículo");
		System.out.println("3 - Cadastrar um vendedor");
		System.out.println("4 - Remover um vendedor");
	}
}
