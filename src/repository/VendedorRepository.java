package repository;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import model.Vendedor;

public class VendedorRepository {

	Map<Integer, Vendedor> vendedoresBD;
	
	
	public VendedorRepository() {
		this.vendedoresBD = new TreeMap();
	}
	
	
	public void salvar(Vendedor vendedor) {
		this.vendedoresBD.put(vendedor.getId(), vendedor);
	}
	
	public List<Vendedor> buscarTodos(){
		return this.vendedoresBD.values().stream().collect(Collectors.toList());
	}
	
	public Vendedor buscarPorId(Integer id) {
		return this.vendedoresBD.get(id);
	}
	
	public void removerPorId(Integer id) {
		this.vendedoresBD.remove(id);
	}
}
