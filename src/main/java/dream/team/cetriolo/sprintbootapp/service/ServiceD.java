package dream.team.cetriolo.sprintbootapp.service;

import java.util.List;

import dream.team.cetriolo.sprintbootapp.entity.Loja;
import dream.team.cetriolo.sprintbootapp.entity.Moto;

public interface ServiceD {
	
	public Moto criarMoto(String placa, String modelo, String loja);
	
	public List<Moto> buscarTodasMotos();
	
	public Moto buscarMotoPorId(Long id);
	
	public Moto buscarMotoPorPlaca(String placa);
	
	public Loja buscarLojaPorNome(String nome);
	
	public Moto salvar(Moto moto);
	
	public Moto update(Moto moto);
	
	public void delete(Long id);


	}