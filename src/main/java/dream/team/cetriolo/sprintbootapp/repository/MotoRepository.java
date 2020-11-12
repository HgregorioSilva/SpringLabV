package dream.team.cetriolo.sprintbootapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dream.team.cetriolo.sprintbootapp.entity.Moto;

public interface MotoRepository extends JpaRepository<Moto,Long> {

    public List<Moto> findByPlacaContainsIgnoreCase(String placa);
	
	public Moto findByPlaca(String placa);
	
	@Query("select u from Moto u where u.placa = ?1")
	public Moto buscaMotoPorPlaca(String placa);
	
	public Moto findByPlacaAndModelo(String placa, String modelo);
	
	@Query("select u from Moto u where u.placa = ?1 and u.modelo = ?2")
	public Moto buscaMotoPorPlacaEModelo(String placa, String modelo);
	
	@Query("select u from Moto u inner join u.lojas a where a.nome = ?1")
	public List<Moto> buscaPorNomeloja(String loja);

	public List<Moto> findByLojasNome(String string);
    
}