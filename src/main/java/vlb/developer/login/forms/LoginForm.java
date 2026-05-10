package vlb.developer.login.forms;

import jakarta.validation.constraints.NotBlank;

public class LoginForm {

    @NotBlank
    public String username;

    @NotBlank
    public String password;
}
