package br.com.luanasoares.javachallenge.service;

import br.com.luanasoares.javachallenge.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    User findByUsername(String username);

    User save(String username, String password);

    List<User> findAll();

    User update(Long id, Set<String> roles);

}
