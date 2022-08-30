package repository;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import model.Veiculo;

public class VeiculoRepository {
	
	Map<Integer, Veiculo> veiculosBD;
	
	
	public VeiculoRepository() {
		this.veiculosBD = new TreeMap();
	}
	
	
	public void salvar(Veiculo veiculo) {
		this.veiculosBD.put(veiculo.getId(), veiculo);
	}
	
	public List<Veiculo> buscarTodos(){
		return this.veiculosBD.values().stream().collect(Collectors.toList());
	}
	
	public Veiculo buscarPorId(Integer id) {
		return this.veiculosBD.get(id);
	}
	
	public void removerPorId(Integer id) {
		this.veiculosBD.remove(id);
	}
}
