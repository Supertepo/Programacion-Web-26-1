package mx.ipn.upiicsa.web.controlacceso.internal.output;

import mx.ipn.upiicsa.web.controlacceso.internal.bs.entity.Persona;

import java.util.Optional;

public interface LoginRepository {
    Optional<Persona> findByLoginAndPassword(String login, String password);
}
