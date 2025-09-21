package br.com.sprint03.investidorapi.controller;

import br.com.sprint03.investidorapi.dto.CreateUserDto;
import br.com.sprint03.investidorapi.model.User;
import br.com.sprint03.investidorapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

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
}