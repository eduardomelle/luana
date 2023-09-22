package br.com.luanasoares.javachallenge.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserAddFavoriteRequestDto implements Serializable {

    @NotNull
    private Long userId;

    @NotNull
    private Long movieId;

}
