package br.com.sprint03.investidorapi.service;

import br.com.sprint03.investidorapi.dto.AccountDto;
import br.com.sprint03.investidorapi.dto.CreateAccountDto;
import br.com.sprint03.investidorapi.exception.ResourceNotFoundException;
import br.com.sprint03.investidorapi.model.Account;
import br.com.sprint03.investidorapi.model.User;
import br.com.sprint03.investidorapi.repository.AccountRepository;
import br.com.sprint03.investidorapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public AccountDto create(CreateAccountDto createAccountDto) {
        User user = userRepository.findById(createAccountDto.userId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + createAccountDto.userId()));

        Account newAccount = new Account();
        newAccount.setAccountNumber(createAccountDto.accountNumber());
        newAccount.setUser(user);

        Account savedAccount = accountRepository.save(newAccount);
        return new AccountDto(savedAccount);
    }
    
    public List<AccountDto> findByUserId(UUID userId) {
        return accountRepository.findByUser_Id(userId)
                .stream()
                .map(AccountDto::new)
                .collect(Collectors.toList());
    }
}