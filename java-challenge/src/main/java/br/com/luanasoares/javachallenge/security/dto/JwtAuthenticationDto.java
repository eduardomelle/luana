package br.com.luanasoares.javachallenge.security.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;

@Data
public class JwtAuthenticationDto implements Serializable {

    @NotEmpty(message = "Username can not be empty.")
    private String username;

    @NotEmpty(message = "Password can not be empty.")
    private String password;

}
