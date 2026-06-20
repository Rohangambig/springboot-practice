package com.entire.demo.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    @NotBlank(message="email Id cannot be null")
    @Email(message = "Email id should be proper")
    private String email;

    @NotBlank(message="password should be mandatory")
    private String password;
}
