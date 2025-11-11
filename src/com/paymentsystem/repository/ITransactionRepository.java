package com.paymentsystem.repository;

import com.paymentsystem.model.Transaction;
import java.util.List;

public interface ITransactionRepository {

    /**
     * Saves a new transaction record.
     * @param transaction The Transaction to save.
     * @return The saved Transaction.
     */
    Transaction save(Transaction transaction);

    /**
     * Finds all transactions (sent or received) for a specific account number.
     * @param accountNumber The account number to search for.
     * @return A List of transactions.
     */
    List<Transaction> findByAccountNumber(String accountNumber);
}