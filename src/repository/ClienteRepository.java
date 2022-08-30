package repository;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import model.Cliente;

public class ClienteRepository {

	Map<Integer, Cliente> clientesBD;
	
	
	public ClienteRepository() {
		this.clientesBD = new TreeMap();
	}
	
	
	public void salvar(Cliente cliente) {
		this.clientesBD.put(cliente.getId(), cliente);
	}
	
	public List<Cliente> buscarTodos(){
		return this.clientesBD.values().stream().collect(Collectors.toList());
	}
	
	public Cliente buscarPorId(Integer id) {
		return this.clientesBD.get(id);
	}
	
	public void removerPorId(Integer id) {
		this.clientesBD.remove(id);
	}
}
