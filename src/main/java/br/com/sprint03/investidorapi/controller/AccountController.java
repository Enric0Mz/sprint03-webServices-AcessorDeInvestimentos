package br.com.sprint03.investidorapi.controller;

import br.com.sprint03.investidorapi.dto.AccountDto;
import br.com.sprint03.investidorapi.dto.CreateAccountDto;
import br.com.sprint03.investidorapi.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountDto> create(
            @RequestBody @Valid CreateAccountDto createAccountDto,
            UriComponentsBuilder uriBuilder
    ) {
        AccountDto accountDto = accountService.create(createAccountDto);
        URI uri = uriBuilder.path("/accounts/{id}").buildAndExpand(accountDto.id()).toUri();
        return ResponseEntity.created(uri).body(accountDto);
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getByUserId(@RequestParam("userId") UUID userId) {
        List<AccountDto> accounts = accountService.findByUserId(userId);
        return ResponseEntity.ok(accounts);
    }
}