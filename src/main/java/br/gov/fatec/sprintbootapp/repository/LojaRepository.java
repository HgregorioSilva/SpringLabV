package br.gov.fatec.sprintbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.fatec.sprintbootapp.entity.Loja;

public interface LojaRepository extends JpaRepository<Loja, Long>{
    
    public Loja findByNome(String loja);
}