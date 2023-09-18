package br.com.luanasoares.javachallenge.controller;

import br.com.luanasoares.javachallenge.dto.UserSaveRequestDto;
import br.com.luanasoares.javachallenge.dto.UserUpdateRequestDto;
import br.com.luanasoares.javachallenge.model.User;
import br.com.luanasoares.javachallenge.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<User> save(@RequestBody @Valid UserSaveRequestDto userSaveRequestDto) {
        User user = this.userService.save(userSaveRequestDto.getUsername(), userSaveRequestDto.getPassword());
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(this.userService.findAll());
    }

    @PutMapping
    public ResponseEntity<User> update(@RequestBody @Valid UserUpdateRequestDto userUpdateRequestDto) {
        return ResponseEntity.ok(this.userService.update(userUpdateRequestDto.getId(), userUpdateRequestDto.getRoles()));
    }

}
