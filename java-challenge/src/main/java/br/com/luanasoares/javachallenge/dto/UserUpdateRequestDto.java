package br.com.luanasoares.javachallenge.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class UserUpdateRequestDto implements Serializable {

    @NotNull
    private Long id;

    @NotEmpty
    private Set<String> roles;

}
