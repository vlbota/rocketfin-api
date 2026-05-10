package vlb.developer.login.forms;

import jakarta.validation.constraints.NotBlank;

public class RefreshTokenForm {

    @NotBlank
    public String refreshToken;
}
