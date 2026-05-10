package vlb.developer.login.service;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotAuthorizedException;
import vlb.developer.entities.ClientEnty;
import vlb.developer.entities.ClientRefreshTokenEnty;
import vlb.developer.login.dtos.LoginResponseDto;
import vlb.developer.login.forms.LoginForm;
import vlb.developer.login.forms.RefreshTokenForm;
import vlb.developer.profile.repositories.ClientRefreshTokenRepository;
import vlb.developer.profile.repositories.ClientRepository;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.UUID;

@ApplicationScoped
public class ClientLoginService {

    @Inject
    ClientRepository clientRepository;

    @Inject
    ClientRefreshTokenRepository refreshTokenRepository;

    @Transactional
    public LoginResponseDto login(LoginForm form) {
        ClientEnty client = clientRepository.findByUsername(form.username)
                .orElseThrow(() -> new NotAuthorizedException("Invalid credentials"));

        if (!BcryptUtil.matches(form.password, client.getPassword())) {
            throw new NotAuthorizedException("Invalid credentials");
        }

        String token = buildJwt(client);
        String refreshToken = createRefreshToken(client);

        return new LoginResponseDto(token, refreshToken, client.getName(), client.getId());
    }

    @Transactional
    public LoginResponseDto refresh(RefreshTokenForm form) {
        ClientRefreshTokenEnty tokenEnty = refreshTokenRepository.findByToken(form.refreshToken)
                .orElseThrow(() -> new NotAuthorizedException("Invalid refresh token"));

        if (tokenEnty.getExpiresAt().isBefore(OffsetDateTime.now())) {
            refreshTokenRepository.delete(tokenEnty);
            throw new NotAuthorizedException("Refresh token expired");
        }

        ClientEnty client = tokenEnty.getClient();
        refreshTokenRepository.delete(tokenEnty);

        String token = buildJwt(client);
        String newRefreshToken = createRefreshToken(client);

        return new LoginResponseDto(token, newRefreshToken, client.getName(), client.getId());
    }

    private String buildJwt(ClientEnty client) {
        return Jwt.issuer("rocketfin")
                .upn(client.getUsername())
                .claim("id", client.getId().toString())
                .claim("name", client.getName())
                .expiresIn(Duration.ofHours(1))
                .sign();
    }

    private String createRefreshToken(ClientEnty client) {
        String token = UUID.randomUUID().toString();

        ClientRefreshTokenEnty entity = new ClientRefreshTokenEnty();
        entity.setRefreshToken(token);
        entity.setClient(client);
        entity.setExpiresAt(OffsetDateTime.now().plusDays(7));
        refreshTokenRepository.persist(entity);

        return token;
    }
}
