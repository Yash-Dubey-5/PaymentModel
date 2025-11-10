package com.paymentsystem.repository;

import com.paymentsystem.model.Bank;
import java.util.Optional;

public interface IBankRepository {
    Bank save(Bank bank);

    Optional<Bank> findByIfscCode(String ifscCode);
}
