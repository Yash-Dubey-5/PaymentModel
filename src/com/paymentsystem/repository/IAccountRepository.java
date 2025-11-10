package com.paymentsystem.repository;

import com.paymentsystem.model.Account;
import java.util.List;
import java.util.Optional;

public interface IAccountRepository {

    Account save(Account account);

    Optional<Account> findByAccountNumber(String accountNumber);

    // for showing account
    List<Account> findByOwnerUserId(String userId);
}
