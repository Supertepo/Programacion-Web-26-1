package mx.ipn.upiicsa.web.controlacceso.internal.output;

import mx.ipn.upiicsa.web.controlacceso.internal.bs.entity.Persona;
import mx.ipn.upiicsa.web.controlacceso.internal.bs.entity.Usuario;

import java.util.Optional;

public interface LoginRepository {
    Optional<Persona> findByLoginAndPassword(String login, String password);
    Integer savePersona(Persona build);
    void saveUsuario(Usuario build);
    boolean existLogin(String login);
}
