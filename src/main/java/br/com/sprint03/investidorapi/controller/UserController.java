package br.com.sprint03.investidorapi.controller;

import br.com.sprint03.investidorapi.dto.CreateUserDto;
import br.com.sprint03.investidorapi.dto.UpdateUserDto;
import br.com.sprint03.investidorapi.dto.UserDto;
import br.com.sprint03.investidorapi.model.User;
import br.com.sprint03.investidorapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> create(
            @RequestBody @Valid CreateUserDto createUserDto,
            UriComponentsBuilder uriBuilder
    ) {
        User user = userService.create(createUserDto);
        URI uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        List<UserDto> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable UUID id) {
        UserDto user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateUserDto updateUserDto
    ) {
        UserDto updatedUser = userService.update(id, updateUserDto);
        return ResponseEntity.ok(updatedUser);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}