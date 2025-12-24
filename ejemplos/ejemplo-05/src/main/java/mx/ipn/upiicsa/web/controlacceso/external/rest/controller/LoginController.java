package mx.ipn.upiicsa.web.controlacceso.external.rest.controller;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import mx.ipn.upiicsa.web.controlacceso.external.rest.dto.PersonaDto;
import mx.ipn.upiicsa.web.controlacceso.external.rest.dto.SigninDto;
import mx.ipn.upiicsa.web.controlacceso.external.rest.dto.LoginDto;
import mx.ipn.upiicsa.web.controlacceso.external.rest.dto.UsuarioDto;
import mx.ipn.upiicsa.web.controlacceso.internal.input.LoginService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Slf4j
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/")
@Tag(name = "Control de Acceso")
public class LoginController {
    @Inject
    private LoginService loginService;

    @POST
    @Path("/login")
    @APIResponse(responseCode = "200", description = "Petición exitosa", content = @Content(schema = @Schema(implementation = PersonaDto.class)))
    @Operation(operationId = "login", description = "Autenticación basada en usuario y contraseña, la contraseña es el digesto en SHA-512 codificado en Base64")
    public Response login(@Valid LoginDto loginDto) {
        return loginService.login(loginDto)
                .map(persona -> {
                    var personaDto = PersonaDto.fromEntity(persona);
                    personaDto.setUsuario(UsuarioDto.fromEntity(persona.getUsuario()));
                    return Response.status(Response.Status.OK).entity(personaDto).build();
                })
                .getOrElseGet(resultadoError -> Response.status(Response.Status.NOT_FOUND).entity(resultadoError).build());
    }

    @POST
    @Path("/signin")
    @APIResponse(responseCode = "200", description = "Petición exitosa", content = @Content(schema = @Schema(implementation = Boolean.class)))
    @Operation(operationId = "signin", description = "Registra a un usuario con base en la información proporcionada, la contraseña es el digesto en SHA-512 codificado en Base64")
    public Response signin(@Valid SigninDto signinDto) {
        return loginService.signin(signinDto.toEntity())
                .map(resultado -> Response.status(Response.Status.OK).entity(resultado).build())
                .getOrElseGet(resultadoerror -> Response.status(Response.Status.BAD_REQUEST).entity(resultadoerror).build());
    }
}