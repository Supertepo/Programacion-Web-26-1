package mx.ipn.upiicsa.web.controlacceso.external.jpa.dao;

import mx.ipn.upiicsa.web.controlacceso.internal.bs.entity.Usuario;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.repository.UsuarioJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class LoginDao {
    @Autowired
    private UsuarioJpaRepository usuarioJpaRepository;
    
    public Optional<Usuario> findByLoginAndPassword(String login, String password) {
        var resultado = usuarioJpaRepository.findByLoginAndPassword(login, password);
        if(resultado.isPresent()) {
            return Optional.of(resultado.get().toEntity());
        } else {
            return Optional.empty();
        }
    }
}
