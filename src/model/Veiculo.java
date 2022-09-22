package model;

import java.util.Objects;

import util.Contador;

public class Veiculo implements Banco{

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
	
	public Veiculo(int id, String modelo, String marca, String cor, String placa, Tipo tipo, Status status, double valorLocacao) {
		this.id = id;
		this.modelo = modelo;
		this.marca = marca;
		this.cor = cor;
		this.placa = placa;
		this.tipo = tipo;
		this.valorLocacao = valorLocacao;
		this.status = status;
	}

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public double getValorLocacao() {
		return valorLocacao;
	}

	public void setValorLocacao(double valorLocacao) {
		this.valorLocacao = valorLocacao;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return this.id + " - " + this.modelo + " " + this.marca + " " + this.cor + " " + this.valorLocacao;
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

	@Override
	public int hashCode() {
		return Objects.hash(cor, id, marca, modelo, placa, status, tipo, valorLocacao);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Veiculo other = (Veiculo) obj;
		return Objects.equals(cor, other.cor) && Objects.equals(id, other.id) && Objects.equals(marca, other.marca)
				&& Objects.equals(modelo, other.modelo) && Objects.equals(placa, other.placa) && status == other.status
				&& tipo == other.tipo
				&& Double.doubleToLongBits(valorLocacao) == Double.doubleToLongBits(other.valorLocacao);
	}
}
