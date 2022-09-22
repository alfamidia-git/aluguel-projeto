package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import model.Veiculo;
import model.Veiculo.Status;
import model.Veiculo.Tipo;

public class ClienteRepository {

	private Connection con;

	public ClienteRepository() {
		try {
			this.con = new ConexaoBD().getConnection();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public Cliente buscarPorID(int id) {
		Cliente cliente = null;

		try {
			PreparedStatement ps = this.con.prepareStatement("select * from clientes where id = " + id);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				Integer idR = result.getInt("id");
				String nome = result.getString("nome");
				String emailR = result.getString("email");
				String senhaR = result.getString("senha");
				cliente = new Cliente(idR, nome, emailR, senhaR);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return cliente;
	}
	
	public List<Cliente> buscar(){
		List<Cliente> clientes = new ArrayList<>();
		try {
			PreparedStatement ps = this.con.prepareStatement("select * from clientes");
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				Integer id = result.getInt("id");
				String nome = result.getString("nome");
				String emailR = result.getString("email");
				String senha = result.getString("senha");

				clientes.add(new Cliente(id, nome, emailR, senha));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return clientes;
	}
	
	public void salvar(Cliente cliente) {		
		String sql = "INSERT INTO clientes (nome, email, senha) values (?, ?, ?)";
		
		try {
			PreparedStatement ps = this.con.prepareStatement(sql);
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getEmail());
			ps.setString(3, cliente.getSenha());
			
			ps.executeUpdate();
			
		}catch(SQLException e) {
			System.out.println("Erro ao salvar o cliente " + e.getMessage() );
		}
		
	}
}
