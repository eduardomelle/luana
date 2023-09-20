package br.com.luanasoares.javachallenge.dto;

import br.com.luanasoares.javachallenge.model.Role;
import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserFindAllResponseDto implements Serializable {

    private Long id;

    private String username;

    private Set<Role> roles = new HashSet<>();

}
