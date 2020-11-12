package dream.team.cetriolo.sprintbootapp.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dream.team.cetriolo.sprintbootapp.entity.Loja;
import dream.team.cetriolo.sprintbootapp.entity.Moto;
import dream.team.cetriolo.sprintbootapp.exception.RegistroNaoEncontradoExecption;
import dream.team.cetriolo.sprintbootapp.repository.LojaRepository;
import dream.team.cetriolo.sprintbootapp.repository.MotoRepository;

@Service("Service") // trocar para Service
public class ServiceImpl implements ServiceD{
	
	@Autowired
	private LojaRepository lojaRepo;
	
	@Autowired
	private MotoRepository motoRepo;

	@Override
	@Transactional//Por que usa transação? Porque como usamos duas operações de banco no caso .save() podemos salvar uma loja no banco e não uma moto ficando assim sem vínculo. 
	public Moto criarMoto(String placa, String modelo, String loja) {
		Loja loj = lojaRepo.findByNome(loja);
		if(loj != null) {
			loj = new Loja();
			loj.setNome(loja);
			lojaRepo.save(loj);
		}
		Moto moto = new Moto();
		moto.setPlaca(placa);
		moto.setModelo(modelo);
		moto.setLojas(new HashSet<Loja>());
		moto.getLojas().add(loj);
		motoRepo.save(moto);
		return moto;
	}

	@Override
	public List<Moto> buscarTodasMotos(){//Não precisava ter feito porque
		return motoRepo.findAll();
	}
	
	@Override
	public Moto buscarMotoPorId(Long id){
		Optional<Moto> motoOp = motoRepo.findById(id);
		if(motoOp.isPresent()) {
			return motoOp.get();
		}
		throw new RegistroNaoEncontradoExecption("Moto não encontrada!");
	}
	
	@Override
	public Moto buscarMotoPorPlaca(String placa) {
		Moto moto = motoRepo.findByPlaca(placa);
		if(placa != null) {
			return moto;
		}
		throw new RegistroNaoEncontradoExecption("Moto não encontrada!");
	}
	
	@Override
	public Loja buscarLojaPorNome(String nome) {
		Loja loja = lojaRepo.findByNome(nome);
		if(loja != null) {
			return loja;
		}
		throw new RegistroNaoEncontradoExecption("Loja não encotrada!");//Se retornar null, que usar recebe nullpointer e dá erro em outro lugar
	}
	
	public Moto salvar(Moto moto){
		if(!moto.getLojas().isEmpty()) {
			for(Loja loja: moto.getLojas()) {
				if(loja.getId() == null && lojaRepo.findByNome(loja.getNome())==null) {
					lojaRepo.save(loja);
				}
			}
		}
		return motoRepo.save(moto);
	}
	
	public Moto update(Moto moto) {
		return motoRepo.save(moto);
	}
	
	public void delete(Long id) {
		motoRepo.deleteById(id);
	}
	
	
}