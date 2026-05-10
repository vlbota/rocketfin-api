package vlb.developer.login.controller;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import vlb.developer.login.dtos.LoginResponseDto;
import vlb.developer.login.forms.LoginForm;
import vlb.developer.login.forms.RefreshTokenForm;
import vlb.developer.login.service.ClientLoginService;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientLoginController {

    @Inject
    ClientLoginService loginService;

    @POST
    @Path("/login")
    public LoginResponseDto login(@Valid LoginForm form) {
        return loginService.login(form);
    }

    @POST
    @Path("/refresh")
    public LoginResponseDto refresh(@Valid RefreshTokenForm form) {
        return loginService.refresh(form);
    }
}
