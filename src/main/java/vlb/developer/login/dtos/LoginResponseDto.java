package vlb.developer.login.dtos;

import java.util.UUID;

public class LoginResponseDto {

    public String token;
    public String refreshToken;
    public String name;
    public UUID id;

    public LoginResponseDto(String token, String refreshToken, String name, UUID id) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.name = name;
        this.id = id;
    }
}
