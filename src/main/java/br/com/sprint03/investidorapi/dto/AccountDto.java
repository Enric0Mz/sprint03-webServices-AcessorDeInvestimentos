package br.com.sprint03.investidorapi.dto;

import br.com.sprint03.investidorapi.model.Account;

import java.util.UUID;

public record AccountDto(
    UUID id,
    String accountNumber,
    UUID userId
) {
    public AccountDto(Account account) {
        this(account.getId(), account.getAccountNumber(), account.getUser().getId());
    }
}