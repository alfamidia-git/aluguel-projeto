package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Veiculo;
import model.Veiculo.Status;
import model.Veiculo.Tipo;

public class VeiculoRepository {

	private Connection con;

	public VeiculoRepository() {
		try {
			this.con = new ConexaoBD().getConnection();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public Veiculo buscarPorID(int id) {
		Veiculo veiculo = null;

		try {
			PreparedStatement ps = this.con.prepareStatement("select * from veiculos where id = " + id);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				String modelo = result.getString("modelo");
				String marca = result.getString("marca");
				String placa = result.getString("placa");
				String cor = result.getString("cor");
				int idR = result.getInt("id");
				Status status = Status.valueOf(result.getString("status"));
				Tipo tipo = Tipo.valueOf(result.getString("tipo"));
				double valorLocacao = result.getDouble("valorLocacao");

				veiculo = new Veiculo(id, modelo, marca, cor, placa, tipo, status, valorLocacao);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return veiculo;
	}
	
	public List<Veiculo> buscar(){
		List<Veiculo> veiculos = new ArrayList<>();
		try {
			PreparedStatement ps = this.con.prepareStatement("select * from veiculos");
			ResultSet result = ps.executeQuery();
			while (result.next()) {
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
			System.out.println(e.getMessage());
		}
		
		return veiculos;
	}
	
	public List<Veiculo> buscarOnde(String onde){
		List<Veiculo> veiculos = new ArrayList<>();
		try {
			PreparedStatement ps = this.con.prepareStatement("select * from veiculos where " + onde);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
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
			System.out.println(e.getMessage());
		}
		
		return veiculos;
	}
	
	
	public void atualizar(Veiculo veiculo) {
		String sql = "update veiculos set modelo = ?, marca = ?, placa = ?, cor = ?, status = ?, tipo = ?"
				+ ", valorLocacao = ? where id = ?";
		
		
		
		PreparedStatement ps;
		try {
			
			ps = this.con.prepareStatement(sql);
			
			ps.setString(1, veiculo.getModelo());
			ps.setString(2, veiculo.getMarca());
			ps.setString(3, veiculo.getPlaca());
			ps.setString(4, veiculo.getCor());
			ps.setString(5, veiculo.getStatus().toString());
			ps.setString(6, veiculo.getTipo().toString());
			ps.setDouble(7, veiculo.getValorLocacao());
			ps.setInt(8, veiculo.getId());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar veículo: " + e.getMessage());
		}
	}
}
