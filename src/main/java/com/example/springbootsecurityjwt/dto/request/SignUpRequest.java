package com.example.springbootsecurityjwt.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Registration request")
public class SignUpRequest {

    @Schema(description = "Username", example = "Jon")
    @Size(min = 5, max = 50, message = "Username must contain from 5 to 50 characters")
    @NotBlank(message = "Username cannot be empty")
    private String username;

    @Schema(description = "E-mail address", example = "jondoe@gmail.com")
    @Size(min = 5, max = 255, message = "The email address must contain between 5 and 255 characters")
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email must be in format user@example.com")
    private String email;

    @Schema(description = "Password", example = "my_1secret1_password")
    @Size(min = 8, max = 255, message = "Password length must be from 8 to 255 characters")
    @NotBlank(message = "Password cannot be empty")
    private String password;

}
