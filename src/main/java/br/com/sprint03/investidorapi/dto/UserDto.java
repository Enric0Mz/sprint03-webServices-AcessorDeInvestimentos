package br.com.sprint03.investidorapi.dto;

import br.com.sprint03.investidorapi.model.Profile;
import br.com.sprint03.investidorapi.model.User;

import java.time.LocalDate;
import java.util.UUID;

public record UserDto(
    UUID id,
    String name,
    String email,
    LocalDate birthDate,
    Profile profile
) {
    public UserDto(User user) {
        this(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getBirthDate(),
            user.getProfile()
        );
    }
}