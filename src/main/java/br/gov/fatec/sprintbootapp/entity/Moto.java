package br.gov.fatec.sprintbootapp.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.fatec.sprintbootapp.controller.View;

@Entity
@Table(name = "mto_moto")
public class Moto {
	
	@JsonView(View.MotoCompleta.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mto_id")
	private Long id;
	
	@JsonView({View.MotoResumo.class, View.LojaResumo.class})
	@Column(name = "mto_placa")
	private String placa;
	
	@JsonView(View.MotoCompleta.class)
	@Column(name = "mto_modelo")
	private String modelo;
	

	@JsonView({View.MotoResumo.class})
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "moj_moto_loja", 
		joinColumns = { @JoinColumn(name = "mto_id") },
		inverseJoinColumns = {@JoinColumn(name = "loj_id")})
	private Set<Loja>lojas;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public Set<Loja> getLojas() {
		return lojas;
	}

	public void setLojas(Set<Loja> lojas) {
		this.lojas = lojas;
	}

}

