package br.com.luanasoares.javachallenge.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserSaveRequestDto implements Serializable {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
