package dream.team.cetriolo.sprintbootapp.controller;

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

import dream.team.cetriolo.sprintbootapp.entity.Autorizacao;
import dream.team.cetriolo.sprintbootapp.entity.Usuario;
import dream.team.cetriolo.sprintbootapp.service.ServiceD;


@RestController
@RequestMapping(value = "/usuario")
@CrossOrigin
public class UsuarioController {

    @Autowired
	private ServiceD service;	

	@JsonView(View.UsuarioResumo.class)
    @GetMapping()
    @PreAuthorize("isAuthenticated")// checa se a condição não for aceita não entra no metodo
   //@PostAuthorize só checa no retorno
	public List<Usuario> buscarTodas(){
        return service.buscarTodosUsuarios();
	}
	
	@JsonView(View.UsuarioCompleta.class)
	@GetMapping(value="/{id}")
	public Usuario buscarPorId(@PathVariable("id") Long id){
		return service.buscarUsuarioPorId(id);
	}
	
	@JsonView(View.UsuarioResumo.class)
	@GetMapping(value="/placa")
	public Usuario buscarUsuarioPorNome(@RequestParam(value="usuario")String nome) {
		return service.buscarUsuarioPorNome(nome);
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
	
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
	public Usuario cadastraNovaUsuario(@RequestBody Usuario usuario) {
        return service.criarUsuario(usuario.getNome(), usuario.getSenha(), "ROLE_USUARIO");
	}
	
	@JsonView( View.AutorizacaoResumo.class)
	@GetMapping(value = "/autorizacao/{autorizacao}")
	public Autorizacao buscarAutorizacaoPorNome(@PathVariable("autorizacao") String nome) {
		return service.buscarAutorizacaoPorNome(nome);
		
	}
	
	@PutMapping
	public ResponseEntity atualizar(@RequestBody Usuario usuario) {
		return ResponseEntity.accepted().body(service.update(usuario));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity deletar(@PathVariable Long id) {
       service.deleteUsuario(id);
		return ResponseEntity.noContent().build();
	}
    
}