package br.com.luanasoares.javachallenge.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserAddFavoriteResponsetDto implements Serializable {

    private String username;

    private List<MovieDto> favorites;

}
