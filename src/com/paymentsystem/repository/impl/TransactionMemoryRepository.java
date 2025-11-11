package com.paymentsystem.repository.impl;

import com.paymentsystem.model.Transaction;
import com.paymentsystem.repository.ITransactionRepository;

import java.util.ArrayList;
import java.util.List;

public class TransactionMemoryRepository implements ITransactionRepository {

    private final List<Transaction> transactionDatabase = new ArrayList<>();

    @Override
    public Transaction save(Transaction transaction) {
        transactionDatabase.add(transaction);
        return transaction;
    }

    @Override
    public List<Transaction> findByAccountNumber(String accountNumber) {
        List<Transaction> accountTransactions = new ArrayList<>();

        for (Transaction t : transactionDatabase) {
            if (t.getFromAccountNumber().equals(accountNumber) || t.getToMerchantAccount().equals(accountNumber)) {
                accountTransactions.add(t);
            }
        }
        return accountTransactions;
    }
}