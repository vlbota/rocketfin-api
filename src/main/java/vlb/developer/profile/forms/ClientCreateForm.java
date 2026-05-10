package vlb.developer.profile.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ClientCreateForm {

    @NotBlank
    public String name;

    @NotBlank
    public String nickname;

    @NotBlank
    public String username;

    @NotBlank
    public String password;

    @NotBlank
    @Email
    public String email;

    public String countryCode;

    public String phoneNumber;
}
