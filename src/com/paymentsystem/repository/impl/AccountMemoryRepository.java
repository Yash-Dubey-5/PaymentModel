package com.paymentsystem.repository.impl;

import com.paymentsystem.model.Account;
import com.paymentsystem.repository.IAccountRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AccountMemoryRepository  implements IAccountRepository{
    private final Map<String,Account> accountDatabase = new HashMap<>();

    @Override
    public Account save(Account account){
        accountDatabase.put(account.getAccountNumber(),account);
        return account;
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber){
        return Optional.ofNullable(accountDatabase.get(accountNumber));
    }

    @Override
    public List<Account> findByOwnerUserId(String userId){
        List<Account> userAccounts =new ArrayList<>();

        for(Account account : accountDatabase.values()){
            if(account.getOwner().getUserId().equals((userId))){
                userAccounts.add(account);
            }
        }
        return userAccounts;
    }
}
