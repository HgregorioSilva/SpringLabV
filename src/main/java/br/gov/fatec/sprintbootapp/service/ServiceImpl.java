package br.gov.fatec.sprintbootapp.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.fatec.sprintbootapp.entity.Autorizacao;
import br.gov.fatec.sprintbootapp.entity.Loja;
import br.gov.fatec.sprintbootapp.entity.Moto;
import br.gov.fatec.sprintbootapp.entity.Usuario;
import br.gov.fatec.sprintbootapp.exception.RegistroNaoEncontradoExecption;
import br.gov.fatec.sprintbootapp.repository.AutorizacaoRepository;
import br.gov.fatec.sprintbootapp.repository.LojaRepository;
import br.gov.fatec.sprintbootapp.repository.MotoRepository;
import br.gov.fatec.sprintbootapp.repository.UsuarioRepository;

@Service("Service") // trocar para Service
public class ServiceImpl implements ServiceD {

    @Autowired
    private LojaRepository lojaRepo;

    @Autowired
    private MotoRepository motoRepo;

    @Autowired
    private AutorizacaoRepository autorizacaoRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private PasswordEncoder passEncoder;

    @Override
    @Transactional // Por que usa transação? Porque como usamos duas operações de banco no caso
                   // .save() podemos salvar uma loja no banco e não uma moto ficando assim sem
                   // vínculo.
    public Moto criarMoto(String placa, String modelo, String loja) {
        Loja loj = lojaRepo.findByNome(loja);
        if (loj != null) {
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
    @PreAuthorize("isAuthenticated()")
    public List<Moto> buscarTodasMotos() {// Não precisava ter feito porque
        return motoRepo.findAll();
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'USUARIO')")
    public Moto buscarMotoPorId(Long id) {
        Optional<Moto> motoOp = motoRepo.findById(id);
        if (motoOp.isPresent()) {
            return motoOp.get();
        }
        throw new RegistroNaoEncontradoExecption("Moto não encontrada!");
    }

    @Override
    public Moto buscarMotoPorPlaca(String placa) {
        Moto moto = motoRepo.findByPlaca(placa);
        if (placa != null) {
            return moto;
        }
        throw new RegistroNaoEncontradoExecption("Moto não encontrada!");
    }

    @Override
    public Loja buscarLojaPorNome(String nome) {
        Loja loja = lojaRepo.findByNome(nome);
        if (loja != null) {
            return loja;
        }
        throw new RegistroNaoEncontradoExecption("Loja não encotrada!");// Se retornar null, que usar recebe nullpointer
                                                                        // e dá erro em outro lugar
    }

    public Moto salvar(Moto moto) {
        if (!moto.getLojas().isEmpty()) {
            for (Loja loja : moto.getLojas()) {
                if (loja.getId() == null && lojaRepo.findByNome(loja.getNome()) == null) {
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

    @Override
    @Transactional
    public Usuario criarUsuario(String nome, String senha, String autorizacao) {
        Autorizacao aut = autorizacaoRepo.findByNome(autorizacao);
        if (aut != null) {
            aut = new Autorizacao();
            aut.setNome(autorizacao);
            autorizacaoRepo.save(aut);
        }
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setSenha(passEncoder.encode(senha));
        usuario.setAutorizacoes(new HashSet<Autorizacao>());
        usuario.getAutorizacoes().add(aut);
        usuarioRepo.save(usuario);
        return usuario;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<Usuario> buscarTodosUsuarios() {
        return usuarioRepo.findAll();
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'USUARIO')")
    public Usuario buscarUsuarioPorId(Long id) {
        Optional<Usuario> usuarioOp = usuarioRepo.findById(id);
        if (usuarioOp.isPresent()) {
            return usuarioOp.get();
        }
        throw new RegistroNaoEncontradoExecption("Usuario não encontrado!");
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public Usuario buscarUsuarioPorNome(String nome) {
        Usuario usuario = usuarioRepo.findByNome(nome);
        if (nome != null) {
            return usuario;
        }
        throw new RegistroNaoEncontradoExecption("Usuario não encontrado!");
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public Autorizacao buscarAutorizacaoPorNome(String nome) {
        Autorizacao autorizacao = autorizacaoRepo.findByNome(nome);
        if (autorizacao != null) {
            return autorizacao;
        }
        throw new RegistroNaoEncontradoExecption("Autorização não encotrada!");
    }

    public Usuario salvar(Usuario usuario) {
        if (!usuario.getAutorizacoes().isEmpty()) {
            for (Autorizacao autorizacao : usuario.getAutorizacoes()) {
                if (autorizacao.getId() == null && autorizacaoRepo.findByNome(autorizacao.getNome()) == null) {
                    autorizacaoRepo.save(autorizacao);
                }
            }
        }
        return usuarioRepo.save(usuario);
    }

    public Usuario update(Usuario usuario) {
        return usuarioRepo.save(usuario);
    }

    public void deleteUsuario(Long id) {
        usuarioRepo.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepo.findByNome(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário " + username + "não encontrado! ");
        }
        return User.builder().username(username).password(usuario.getSenha())
                .authorities(usuario.getAutorizacoes().stream().map(Autorizacao::getNome).collect(Collectors.toList())
                        .toArray(new String[usuario.getAutorizacoes().size()]))
                .build();
    }

}