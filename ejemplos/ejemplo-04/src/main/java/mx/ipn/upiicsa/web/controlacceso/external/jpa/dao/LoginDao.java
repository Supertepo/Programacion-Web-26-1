package mx.ipn.upiicsa.web.controlacceso.external.jpa.dao;

import mx.ipn.upiicsa.web.controlacceso.internal.bs.entity.Persona;
import mx.ipn.upiicsa.web.controlacceso.internal.bs.entity.Usuario;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.repository.UsuarioJpaRepository;
import mx.ipn.upiicsa.web.controlacceso.internal.output.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class LoginDao implements LoginRepository {
    @Autowired
    private UsuarioJpaRepository usuarioJpaRepository;
    
    public Optional<Persona> findByLoginAndPassword(String login, String password) {
        var resultado = usuarioJpaRepository.findByLoginAndPassword(login, password);
        if(resultado.isPresent()) {
            var usuarioJpa = resultado.get();
            var persona = usuarioJpa.getPersona().toEntity();
            persona.setUsuario(usuarioJpa.toEntity());
            return Optional.of(persona);
        } else {
            return Optional.empty();
        }
    }
}
