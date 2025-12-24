package mx.ipn.upiicsa.web.controlacceso.external.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import mx.ipn.upiicsa.web.controlacceso.internal.bs.entity.Persona;
import mx.ipn.upiicsa.web.controlacceso.internal.bs.entity.Usuario;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDate;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Schema(name = "PersonaDto", description = "Información de una persona autenticada autenticado")
public class PersonaDto {
    @JsonProperty
    @Schema(description = "Identificador de la persona autenticada", readOnly = true)
    private Integer id;
    @JsonProperty
    @Schema(description = "Identificador del genero de la persona", readOnly = true)
    private Integer idGenero;
    @JsonProperty
    @Schema(description = "Nombre de la persona", readOnly = true)
    private String nombre;
    @JsonProperty
    @Schema(description = "Primer apellido de la persona", readOnly = true)
    private String primerApellido;
    @JsonProperty
    @Schema(description = "Segundo apellido de la persona", readOnly = true)
    private String segundoApellido;
    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Schema(description = "Fecha de nacimiento de la persona", readOnly = true, format = "string", implementation = String.class)
    private LocalDate fechaNacimiento;
    @JsonProperty
    @Schema(description = "Usuario asociado a la persona", readOnly = true)
    private UsuarioDto usuario;

    public static PersonaDto fromEntity(Persona entity) {
        return PersonaDto.builder()
                .id(entity.getId())
                .idGenero(entity.getIdGenero())
                .nombre(entity.getNombre())
                .primerApellido(entity.getPrimerApellido())
                .segundoApellido(entity.getSegundoApellido())
                .fechaNacimiento(entity.getFechaNacimiento())
                .build();
    }
}
