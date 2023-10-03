package br.com.luanasoares.javachallenge.service;

import br.com.luanasoares.javachallenge.dto.MovieDto;
import br.com.luanasoares.javachallenge.dto.UserAddFavoriteResponsetDto;
import br.com.luanasoares.javachallenge.dto.UserFindAllResponseDto;
import br.com.luanasoares.javachallenge.dto.UserRemoveFavoriteResponsetDto;
import br.com.luanasoares.javachallenge.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface UserService {

    User findByUsername(String username);

    User save(String username, String password);

    List<UserFindAllResponseDto> findAll();

    User update(Long id, Set<String> roles);

    UserAddFavoriteResponsetDto addFavorite(Long id, Long movieId);

    UserRemoveFavoriteResponsetDto removeFavorite(Long id, Long movieId);

    List<MovieDto> findFavoritesById(Long id);

}
