package model;

import util.Contador;

public class Veiculo {
	
	private Integer id;
	
	private String modelo;	
	private String marca;
	private String cor;
	private String placa;
	private Tipo tipo;
	private double valorLocacao;
	private Status status;
	
	
	public Veiculo(String modelo, String marca, String cor, String placa, String tipo, double valorLocacao) {
		this.id = Contador.proximoId();
		
		this.modelo = modelo;
		this.marca = marca;
		this.cor = cor;
		this.placa = placa;
		this.tipo = Tipo.valueOf(tipo.toUpperCase());
		this.valorLocacao = valorLocacao;
		this.status = Status.LIVRE;		
	}
	
	
	
	
	
	
	
	public static enum Tipo{
		CARRO,
		CAMINHAO,
		MOTO
	}
	
	public static enum Status{
		LIVRE,
		ALUGADO
	}
}
