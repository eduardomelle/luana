package br.com.luanasoares.javachallenge.service.impl;

import br.com.luanasoares.javachallenge.dto.UserFindAllResponseDto;
import br.com.luanasoares.javachallenge.model.Role;
import br.com.luanasoares.javachallenge.model.User;
import br.com.luanasoares.javachallenge.repository.RoleRepository;
import br.com.luanasoares.javachallenge.repository.UserRepository;
import br.com.luanasoares.javachallenge.service.UserService;
import br.com.luanasoares.javachallenge.util.PasswordUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public User save(String username, String password) {
        Set<Role> roles = new HashSet<>();
        roles.add(this.roleRepository.findByName("ROLE_USER"));

        User user = new User();
        user.setUsername(username);
        user.setPassword(PasswordUtils.generatebCrypt(password));
        user.setRoles(roles);
        return this.userRepository.save(user);
    }

    @Override
    public List<UserFindAllResponseDto> findAll() {
        List<UserFindAllResponseDto> userFindAllResponseDtos = new ArrayList<>();
        this.userRepository.findAll().forEach(user -> {
            UserFindAllResponseDto userFindAllResponseDto = new UserFindAllResponseDto();
            BeanUtils.copyProperties(user, userFindAllResponseDto);
            userFindAllResponseDtos.add(userFindAllResponseDto);
        });
        return userFindAllResponseDtos;
    }

    @Override
    public User update(Long id, Set<String> roles) {
        Optional<User> optionalUser = this.userRepository.findById(id);
        if (optionalUser.isPresent()) {
            Set<Role> rs = new HashSet<>();
            roles.forEach(role -> {
                Role r = this.roleRepository.findByName(role);
                if (r != null) {
                    rs.add(r);
                }
            });

            if (!rs.isEmpty()) {
                User user = optionalUser.get();
                user.setRoles(rs);
                return this.userRepository.save(user);
            }
        }

        return null;
    }

}
