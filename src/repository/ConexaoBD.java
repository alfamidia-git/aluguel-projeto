package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {

	private String hostname;
	
	private String username;
	
	private String passoword;
	
	private String url;
	
	private Connection conn;
	
	
	public ConexaoBD() {
		this.hostname = "localhost:3306";
		this.username = "root";
		this.passoword = "";
		
		this.url = "jdbc:mysql://" + hostname + "/mais_pra_ti";
		
		
	}
	
	public Connection getConnection() throws SQLException {
		this.conn = null;
		try {
			this.conn = DriverManager.getConnection(this.url, this.username, this.passoword);
		}catch(SQLException e){
			System.out.println("Erro ao fazer conexão: " + e.getMessage());
		}
		
		if(this.conn == null) {
			throw new SQLException("Erro ao conectar com banco de dados");
		}
		return conn;
	}
	
	public void closeConnection() {
		if(conn != null) {
			try {
				conn.close();
			}catch(SQLException e) {
				System.out.println("Erro ao fechar conexão: " + e.getMessage());
			}
		}
	}
}
