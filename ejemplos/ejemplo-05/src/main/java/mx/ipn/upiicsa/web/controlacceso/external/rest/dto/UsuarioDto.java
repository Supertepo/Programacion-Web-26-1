package mx.ipn.upiicsa.web.controlacceso.external.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import mx.ipn.upiicsa.web.controlacceso.internal.bs.entity.Usuario;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Schema(name = "UsuarioDto", description = "Información de un usuario")
public class UsuarioDto {
    @JsonProperty
    @Schema(description = "Identificador del usuario", readOnly = true)
    private Integer id;
    @JsonProperty
    @Schema(description = "Identificador del rol", readOnly = true)
    private Integer idRol;
    @JsonProperty
    @Schema(description = "Login del usuario", readOnly = true)
    private String login;
    @JsonProperty
    @Schema(description = "Contraseña del usuario en SHA512+Base64", readOnly = true)
    private String password;
    @JsonProperty
    @Schema(description = "Estado de bloqueo del usuario", readOnly = true)
    private Boolean activo;

    public static UsuarioDto fromEntity(Usuario entity) {
        return UsuarioDto.builder()
                .id(entity.getId())
                .idRol(entity.getIdRol())
                .login(entity.getLogin())
                .password(entity.getPassword())
                .activo(entity.getActivo())
                .build();
    }
}
