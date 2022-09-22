package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import model.Vendedor;

public class VendedorRepository {

	private Connection con;

	public VendedorRepository() {
		try {
			this.con = new ConexaoBD().getConnection();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public List<Vendedor> buscar(){
		List<Vendedor> vendedores = new ArrayList<>();
		try {
			PreparedStatement ps = this.con.prepareStatement("select * from vendedores");
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				Integer id = result.getInt("id");
				String nome = result.getString("nome");
				String emailR = result.getString("email");
				String senha = result.getString("senha");
				Double salario = result.getDouble("salario");

				vendedores.add(new Vendedor(id, nome, emailR, senha, salario));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return vendedores;
	}
	
	public Vendedor buscarPorID(int id) {
		Vendedor vendedor = null;

		try {
			PreparedStatement ps = this.con.prepareStatement("select * from vendedores where id = " + id);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				Integer idR = result.getInt("id");
				String nome = result.getString("nome");
				String emailR = result.getString("email");
				String senhaR = result.getString("senha");
				Double salario = result.getDouble("salario");
				vendedor = new Vendedor(idR, nome, emailR, senhaR, salario);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return vendedor;
	}

	public void atualizar(Vendedor vendedor) {
		String sql = "update vendedores set nome = ?, email = ?, senha = ?, salario = ? where id = ?";	
		
		PreparedStatement ps;
		try {
			
			ps = this.con.prepareStatement(sql);
			
			ps.setString(1, vendedor.getNome());
			ps.setString(2, vendedor.getEmail());
			ps.setString(3, vendedor.getSenha());
			ps.setDouble(4, vendedor.getSalario());
			ps.setInt(5, vendedor.getId());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar vendedores: " + e.getMessage());
		}
		
	}
}
