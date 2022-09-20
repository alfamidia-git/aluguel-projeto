package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import model.Banco;

public class Repository<T extends Banco> {

	Map<Integer, T> bancoDeDados;


	public void salvar(T t) {
		this.bancoDeDados.put(t.getId(), t);
	}

	public List<T> buscarTodos(){
		return this.bancoDeDados.values().stream().collect(Collectors.toList());
	}

	public T buscarPorId(Integer id) {
		return this.bancoDeDados.get(id);
	}

	public void removerPorId(Integer id) {
		this.bancoDeDados.remove(id);
	}
	
	
	Connection con;
	
	public Repository() {

		this.bancoDeDados = new TreeMap<>();
		this.con = new ConexaoBD().getConnection();
	}
	
	public PreparedStatement prepararSQL(String sql) throws SQLException {
		PreparedStatement ps = this.con.prepareStatement(sql);		
		return ps;
	}
	
	public ResultSet select(String sql) throws SQLException {
		PreparedStatement ps = this.con.prepareStatement(sql);
		ResultSet result = ps.executeQuery();
		
		return result;
	}
}
