package dream.team.cetriolo.sprintbootapp.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import dream.team.cetriolo.sprintbootapp.controller.View;

@Entity
@Table(name = "loj_loja")
public class Loja {
	
	@JsonView(View.MotoCompleta.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "loj_id")
	private Long id;
	
	@JsonView({View.MotoResumo.class, View.LojaResumo.class})
	@Column(name = "loj_nome")
	private String nome;
	
	@JsonView(View.LojaResumo.class)
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "lojas")
	private Set<Moto> motos;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<Moto> getMotos() {
		return motos;
	}

	public void setMotos(Set<Moto> motos) {
		this.motos = motos;
	}
}