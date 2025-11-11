package com.paymentsystem.repository.impl;

import com.paymentsystem.model.Transaction;
import com.paymentsystem.repository.ITransactionRepository;

import java.util.ArrayList;
import java.util.List;
// import java.util.stream.Collectors;

public class TransactionMemoryRepository implements ITransactionRepository {

    // A simple list is fine for our in-memory history
    private final List<Transaction> transactionDatabase = new ArrayList<>();

    @Override
    public Transaction save(Transaction transaction) {
        transactionDatabase.add(transaction);
        return transaction;
    }

    @Override
    public List<Transaction> findByAccountNumber(String accountNumber) {
        List<Transaction> accountTransactions = new ArrayList<>();

        // Loop through all transactions
        for (Transaction t : transactionDatabase) {
            // Check if this account was the sender OR the receiver
            if (t.getFromAccountNumber().equals(accountNumber) || t.getToMerchantAccount().equals(accountNumber)) {
                accountTransactions.add(t);
            }
        }
        return accountTransactions;
    }
}