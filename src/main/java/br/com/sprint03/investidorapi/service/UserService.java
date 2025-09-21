package br.com.sprint03.investidorapi.service;

import br.com.sprint03.investidorapi.dto.CreateUserDto;
import br.com.sprint03.investidorapi.model.User;
import br.com.sprint03.investidorapi.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(CreateUserDto createUserDto) {
        User newUser = new User();
        newUser.setName(createUserDto.name());
        newUser.setEmail(createUserDto.email());
        newUser.setBirthDate(createUserDto.birthDate());
        newUser.setProfile(createUserDto.profile());

        return userRepository.save(newUser);
    }
}