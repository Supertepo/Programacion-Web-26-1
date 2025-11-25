package mx.ipn.upiicsa.web.controlacceso.internal.input;

import mx.ipn.upiicsa.web.controlacceso.external.mvc.dto.LoginDto;

public interface LoginService {
    void login(LoginDto login);
}
