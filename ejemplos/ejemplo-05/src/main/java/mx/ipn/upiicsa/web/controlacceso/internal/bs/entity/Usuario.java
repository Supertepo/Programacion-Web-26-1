package mx.ipn.upiicsa.web.controlacceso.internal.bs.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class Usuario {
    private Integer id;
    private Integer idRol;
    private String login;
    private String password;
    private Boolean activo;
}
