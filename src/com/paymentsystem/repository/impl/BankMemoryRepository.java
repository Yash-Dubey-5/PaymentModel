package com.paymentsystem.repository.impl;

import com.paymentsystem.model.Bank;
import com.paymentsystem.repository.IBankRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BankMemoryRepository implements IBankRepository {

    private final Map<String,Bank> bankDatabase =new HashMap<>();

    @Override
    public Bank save(Bank bank){
        bankDatabase.put(bank.getIfscCode(),bank);
        return bank;
    }

    @Override
    public Optional<Bank> findByIfscCode(String ifscCode){
        return Optional.ofNullable(bankDatabase.get(ifscCode));
    }
}
