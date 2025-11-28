package mx.ipn.upiicsa.web.controlacceso.internal.bs.implemet;

import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import mx.ipn.upiicsa.web.controlacceso.external.mvc.dto.LoginDto;
import mx.ipn.upiicsa.web.controlacceso.internal.bs.entity.Persona;
import mx.ipn.upiicsa.web.controlacceso.internal.input.LoginService;
import mx.ipn.upiicsa.web.controlacceso.internal.output.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class LoginBs implements LoginService {
    @Autowired
    private LoginRepository loginRepository;

    public Either<Integer, Persona> login(LoginDto login) {
        var resultadoLogin = loginRepository.findByLoginAndPassword(login.getUsername(), login.getPassword());
        Either<Integer, Persona> resultado;
        if(resultadoLogin.isPresent()) {
            var persona = resultadoLogin.get();
            log.info("El usuario {} se autenticó exitosamente", login.getUsername());
            if(!persona.getUsuario().getActivo()) {
                resultado = Either.left(2);
            } else {
                resultado = Either.right(persona);
            }
        } else {
            resultado = Either.left(1);
            log.info("Error en la autenticación del usuario");
        }
        return resultado;
    }
}
