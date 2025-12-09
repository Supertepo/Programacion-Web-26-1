package mx.ipn.upiicsa.web.controlacceso.external.jpa.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.model.PersonaJpa;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.model.UsuarioJpa;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.repository.PersonaJpaRepository;
import mx.ipn.upiicsa.web.controlacceso.internal.bs.entity.Persona;
import mx.ipn.upiicsa.web.controlacceso.internal.bs.entity.Usuario;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.repository.UsuarioJpaRepository;
import mx.ipn.upiicsa.web.controlacceso.internal.output.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LoginDao implements LoginRepository {
    @Autowired
    private UsuarioJpaRepository usuarioJpaRepository;
    @Autowired
    private PersonaJpaRepository personaJpaRepository;
    @PersistenceContext
    private EntityManager entityManager;

    private static final String QUERY_EXIST_LOGIN = "select count(*) > 0 as st_existe from tca02_usuario where tx_login = :login";
    
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

    @Override
    public Integer savePersona(Persona persona) {
        return personaJpaRepository.save(PersonaJpa.fromEntity(persona)).getId();
    }

    @Override
    public void saveUsuario(Usuario build) {
        usuarioJpaRepository.save(UsuarioJpa.fromEntity(build));
    }

    @Override
    public boolean existLogin(String login) {
        Query query = entityManager.createNativeQuery(QUERY_EXIST_LOGIN);
        query.setParameter("login", login);
        List<Object> resultados = query.getResultList();
        if(resultados.isEmpty()) {
            return true;
        }
        return (boolean)resultados.get(0);
    }
}
