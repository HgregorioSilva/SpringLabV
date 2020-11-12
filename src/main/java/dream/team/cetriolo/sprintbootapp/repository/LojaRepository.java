package dream.team.cetriolo.sprintbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dream.team.cetriolo.sprintbootapp.entity.Loja;

public interface LojaRepository extends JpaRepository<Loja, Long>{
    
    public Loja findByNome(String loja);
}