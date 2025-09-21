package br.com.sprint03.investidorapi.service;

import br.com.sprint03.investidorapi.dto.CreateUserDto;
import br.com.sprint03.investidorapi.dto.UpdateUserDto;
import br.com.sprint03.investidorapi.dto.UserDto;
import br.com.sprint03.investidorapi.model.User;
import br.com.sprint03.investidorapi.repository.UserRepository;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.UUID;
import java.util.stream.Collectors;
import java.util.List;

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
    public UserDto findById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found")); // Provisório
        return new UserDto(user);
    }

        public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserDto update(UUID id, UpdateUserDto updateUserDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(updateUserDto.name());
        user.setEmail(updateUserDto.email());
        user.setBirthDate(updateUserDto.birthDate());
        user.setProfile(updateUserDto.profile());
        
        User updatedUser = userRepository.save(user); 

        return new UserDto(updatedUser);
    }
}