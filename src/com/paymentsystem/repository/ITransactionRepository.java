package com.paymentsystem.repository;

import com.paymentsystem.model.Transaction;
import java.util.List;

public interface ITransactionRepository {

    Transaction save(Transaction transaction);

    List<Transaction> findByAccountNumber(String accountNumber);
}