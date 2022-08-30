package repository;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import model.Administrador;

public class AdministradorRepository {

	Map<Integer, Administrador> administradoresBD;
	
	
	public AdministradorRepository() {
		this.administradoresBD = new TreeMap();
	}
	
	
	public void salvar(Administrador administrador) {
		this.administradoresBD.put(administrador.getId(), administrador);
	}
	
	public List<Administrador> buscarTodos(){
		return this.administradoresBD.values().stream().collect(Collectors.toList());
	}
	
	public Administrador buscarPorId(Integer id) {
		return this.administradoresBD.get(id);
	}
	
	public void removerPorId(Integer id) {
		this.administradoresBD.remove(id);
	}
}
