package vlb.developer.login.controller;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;
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
        LoginResponseDto dto = loginService.login(form);
        NewCookie refreshCookie = new NewCookie.Builder("rocketfinauth.refresh.token")
                .value(dto.getRefreshToken())
                .maxAge(60 * 60 * 24)  // 24 horas
                .httpOnly(true)         // JS não consegue ler
                .secure(true)           // só HTTPS
                .sameSite(NewCookie.SameSite.STRICT)
                .path("/")
                .build();

        // retorna o dto sem o refreshToken + cookie no header
        return Response.ok(dto.semRefreshToken()).cookie(refreshCookie).build();

//        return loginService.login(form);
    }

    @POST
    @Path("/refresh")
    public LoginResponseDto refresh(@Valid RefreshTokenForm form) {
        return loginService.refresh(form);
    }
}
