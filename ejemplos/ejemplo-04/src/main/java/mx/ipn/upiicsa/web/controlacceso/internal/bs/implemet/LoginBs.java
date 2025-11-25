package mx.ipn.upiicsa.web.controlacceso.internal.bs.implemet;

import lombok.extern.slf4j.Slf4j;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.dao.LoginDao;
import mx.ipn.upiicsa.web.controlacceso.external.mvc.dto.LoginDto;
import mx.ipn.upiicsa.web.controlacceso.internal.input.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoginBs implements LoginService {
    @Autowired
    private LoginDao loginDao;

    public void login(LoginDto login) {
        var resultado = loginDao.findByLoginAndPassword(login.getUsername(), login.getPassword());
        if(resultado.isPresent()) {
            log.info("El usuario {} se autenticó exitosamente", login.getUsername());
        } else {
            log.info("Error en la autenticación del usuario");
        }
        
    }
}
