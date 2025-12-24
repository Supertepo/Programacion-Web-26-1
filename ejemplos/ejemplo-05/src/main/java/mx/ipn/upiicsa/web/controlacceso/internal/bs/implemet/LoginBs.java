package mx.ipn.upiicsa.web.controlacceso.internal.bs.implemet;

import io.vavr.control.Either;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.dao.LoginDao;
import mx.ipn.upiicsa.web.controlacceso.external.rest.dto.LoginDto;
import mx.ipn.upiicsa.web.controlacceso.internal.bs.entity.Persona;
import mx.ipn.upiicsa.web.controlacceso.internal.bs.entity.Signin;
import mx.ipn.upiicsa.web.controlacceso.internal.bs.entity.Usuario;
import mx.ipn.upiicsa.web.controlacceso.internal.input.LoginService;
import mx.ipn.upiicsa.web.controlacceso.internal.output.LoginRepository;

import java.time.LocalDate;
import java.time.Period;

@Slf4j
@ApplicationScoped
public class LoginBs implements LoginService {
    @Inject
    private LoginRepository loginRepository;
    @Inject
    private LoginDao loginDao;

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

    @Override
    public Either<Integer, Boolean> signin(Signin signin) {
        Either<Integer, Boolean> resultado;
        // Hacer todas las validaciones de negocio
        /**
         * 1. Que la persona que se quiere registrar sea mayor de edad
         * 2. Que el login que desea usar la persona no esté registrado previamente
         */
        if(!validateRN003(signin.getFechaNacimiento())) {
            resultado = Either.left(3);
        } else if(validateRN004(signin.getLogin())){
            resultado = Either.left(4);
        } else {
            Integer idPersona = loginRepository.savePersona(Persona.builder()
                    .idGenero(signin.getIdGenero())
                    .nombre(signin.getNombre())
                    .primerApellido(signin.getPrimerApellido())
                    .segundoApellido(signin.getSegundoApellido())
                    .fechaNacimiento(signin.getFechaNacimiento())
                    .build());
            loginRepository.saveUsuario(Usuario.builder()
                    .id(idPersona)
                    .idRol(1)
                    .login(signin.getLogin())
                    .password(signin.getPassword())
                    .activo(true)
                    .build());
            return Either.right(true);
        }
        return resultado;
    }

    private boolean validateRN004(String login) {
        var existe = loginDao.existLogin(login);
        log.info("validateRN004: {} {}", login, existe);
        return existe;
    }

    private boolean validateRN003(LocalDate fechaNacimiento) {
        var now = LocalDate.now();
        Period period = Period.between(fechaNacimiento, now);
        log.info("validateRN003: {} {}",period.getYears(),period.getYears() > 18);
        return period.getYears() > 18;
    }
}
