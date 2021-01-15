package br.gov.fatec.sprintbootapp.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import br.gov.fatec.sprintbootapp.entity.Autorizacao;
import br.gov.fatec.sprintbootapp.entity.Loja;
import br.gov.fatec.sprintbootapp.entity.Moto;
import br.gov.fatec.sprintbootapp.entity.Usuario;

public interface ServiceD extends UserDetailsService{
	
	public Moto criarMoto(String placa, String modelo, String loja);
	
	public List<Moto> buscarTodasMotos();
	
	public Moto buscarMotoPorId(Long id);
	
	public Moto buscarMotoPorPlaca(String placa);
	
	public Loja buscarLojaPorNome(String nome);
	
	public Moto salvar(Moto moto);
	
	public Moto update(Moto moto);
	
    public void delete(Long id);
    
    public Usuario criarUsuario(String nome, String senha, String Autorizacao);
	
	
	public Usuario buscarUsuarioPorId(Long id);
	
	public Usuario buscarUsuarioPorNome(String nome);
	
	public Autorizacao buscarAutorizacaoPorNome(String nome);
	
	public Usuario salvar(Usuario usuario);
	
	public Usuario update(Usuario usuario);
	
    public List<Usuario> buscarTodosUsuarios();

    public void deleteUsuario(Long id);



	}