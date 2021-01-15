package br.gov.fatec.sprintbootapp.controller;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.fatec.sprintbootapp.entity.Loja;
import br.gov.fatec.sprintbootapp.entity.Moto;
import br.gov.fatec.sprintbootapp.service.ServiceD;

@RestController
@RequestMapping(value = "/moto")
@CrossOrigin
public class MotoController {//Poderia usar direto o repositorio fazer delete e um put
	
	@Autowired
	private ServiceD service;	

	@JsonView(View.MotoResumo.class)
    @GetMapping()
   // checa se a condição não for aceita não entra no metodo
   //@PostAuthorize só checa no retorno
	public List<Moto> buscarTodas(){
		return service.buscarTodasMotos();
	}
	
	@JsonView(View.MotoCompleta.class)
	@GetMapping(value="/{id}")
	public Moto buscarPorId(@PathVariable("id") Long id){
		return service.buscarMotoPorId(id);
	}
	
	@JsonView(View.MotoResumo.class)
	@GetMapping(value="/placa")
	public Moto buscarMotoPorPlaca(@RequestParam(value="placa")String placa) {
		return service.buscarMotoPorPlaca(placa);
	}
	
	//@JsonView({View.MotoResumo.class, View.LojaResumo.class})
	//@PostMapping
	//public ResponseEntity<Moto> cadastrarNovaMoto(@RequestBody Moto moto, UriComponentsBuilder uriComponentsBuilder) {
		//moto = service.criarMoto(moto.getPlaca(), moto.getModelo(), "BMW");
	//	HttpHeaders responseHeaders = new HttpHeaders();
		//responseHeaders.setLocation(
			//	uriComponentsBuilder.path(
				//		"/moto/" + moto.getId()).build().toUri());
		//return new ResponseEntity<Moto>(moto, responseHeaders, HttpStatus.CREATED);
//	}
	
    @JsonView( View.MotoResumo.class)
    @PostMapping
	public Moto cadastraNovaMoto(@RequestBody Moto moto) {
		return service.criarMoto(moto.getPlaca(), moto.getModelo(), "BMW");
	}
	
	@JsonView( View.LojaResumo.class)
	@GetMapping(value = "/loja/{loja}")
	public Loja buscarLojaPorNome(@PathVariable("loja") String nome) {
		return service.buscarLojaPorNome(nome);
		
	}
	
	@PutMapping
	public ResponseEntity atualizar(@RequestBody Moto moto) {
		return ResponseEntity.accepted().body(service.update(moto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity deletar(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
