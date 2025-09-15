package br.com.mrb.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank(message = "{register.name.not-blank}")
    @Size(max = 20, message = "{register.name.size}")
    private String name;

    @NotBlank(message = "{register.email.not-blank}")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "{register.email.pattern}")
    private String email;

    @NotBlank(message = "{register.password.not-blank}")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "{register.password.pattern}")
    private String password;
}
