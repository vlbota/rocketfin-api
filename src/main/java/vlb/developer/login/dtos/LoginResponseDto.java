package vlb.developer.login.dtos;

import lombok.Getter;

import java.util.UUID;

@Getter
public class LoginResponseDto {

    private String token;
    private String refreshToken;
    private String name;
    private UUID id;

    public LoginResponseDto(String token, String refreshToken, String name, UUID id) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.name = name;
        this.id = id;
    }
}
