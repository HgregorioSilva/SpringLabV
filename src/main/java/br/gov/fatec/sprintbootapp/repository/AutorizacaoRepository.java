package br.gov.fatec.sprintbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.fatec.sprintbootapp.entity.Autorizacao;

public interface AutorizacaoRepository extends JpaRepository<Autorizacao, Long> {

    public Autorizacao findByNome(String autorizacao);
    
}