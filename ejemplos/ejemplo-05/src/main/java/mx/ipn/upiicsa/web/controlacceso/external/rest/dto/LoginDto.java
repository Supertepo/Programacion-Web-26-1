package mx.ipn.upiicsa.web.controlacceso.external.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Schema(name = "LoginDto", description = "Entidad quye abstrae los parámetros para la realización de la autenticacion")
public class LoginDto {
    @JsonProperty
    @Schema(description = "Nombre de usuario", required = true)
    @NotBlank(message = "Favor de proporcionar el nombre de usuario")
    @Email(message = "El nombre de usuario es incorrecto, favor de proporcionar un correo electrónico")
    private String username;
    @JsonProperty
    @Schema(description = "Contraseña del usuario codificada en SHA512 -> Base64", required = true)
    @NotBlank(message = "Favor de proporcionar la contraseña")
    private String password;
}
