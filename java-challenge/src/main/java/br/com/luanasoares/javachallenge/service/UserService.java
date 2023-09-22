package br.com.luanasoares.javachallenge.service;

import br.com.luanasoares.javachallenge.dto.UserFindAllResponseDto;
import br.com.luanasoares.javachallenge.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    User findByUsername(String username);

    User save(String username, String password);

    List<UserFindAllResponseDto> findAll();

    User update(Long id, Set<String> roles);

    User addFavorite(Long id, Long movieId);

}
