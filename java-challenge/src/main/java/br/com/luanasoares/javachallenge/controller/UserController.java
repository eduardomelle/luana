package br.com.luanasoares.javachallenge.controller;

import br.com.luanasoares.javachallenge.dto.*;
import br.com.luanasoares.javachallenge.model.User;
import br.com.luanasoares.javachallenge.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<Object> save(@RequestBody @Valid UserSaveRequestDto userSaveRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("UserController -> save(): {}", bindingResult.getAllErrors());
            StringBuilder stringBuilder = new StringBuilder();
            bindingResult.getAllErrors().forEach(error -> stringBuilder.append(error.getDefaultMessage()).append(" "));
            return ResponseEntity.badRequest().body(stringBuilder.toString());
        }

        User user = this.userService.save(userSaveRequestDto.getUsername(), userSaveRequestDto.getPassword());
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<UserFindAllResponseDto>> findAll() {
        return ResponseEntity.ok(this.userService.findAll());
    }

    @PutMapping
    public ResponseEntity<User> update(@RequestBody @Valid UserUpdateRequestDto userUpdateRequestDto) {
        return ResponseEntity.ok(this.userService.update(userUpdateRequestDto.getId(), userUpdateRequestDto.getRoles()));
    }

    @PostMapping("/add-favorite")
    public ResponseEntity<UserAddFavoriteResponsetDto> addFavorite(@RequestBody @Valid UserAddFavoriteRequestDto userAddFavoriteRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.addFavorite(userAddFavoriteRequestDto.getUserId(), userAddFavoriteRequestDto.getMovieId()));
    }

    @PostMapping("/remove-favorite")
    public ResponseEntity<UserRemoveFavoriteResponsetDto> addFavorite(@RequestBody @Valid UserRemoveFavoriteRequestDto userRemoveFavoriteRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.removeFavorite(userRemoveFavoriteRequestDto.getUserId(), userRemoveFavoriteRequestDto.getMovieId()));
    }

    @GetMapping("/{id}/favorites")
    public ResponseEntity<List<MovieDto>> findFavoritesById(@PathVariable Long id) {
        return ResponseEntity.ok(this.userService.findFavoritesById(id));
    }

}
