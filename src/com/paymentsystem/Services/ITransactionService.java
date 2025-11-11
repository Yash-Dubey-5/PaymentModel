package com.paymentsystem.Services;

import com.paymentsystem.exception.AccountNotFoundException;
import com.paymentsystem.exception.InsufficientFundsException;
import com.paymentsystem.model.Transaction;
import java.util.List;

public interface ITransactionService {

    /**
     * Transfers money from one account to another.
     * @param fromAccountNumber The sender's account number
     * @param toAccountNumber The receiver's account number
     * @param amount The amount to transfer
     * @return A Transaction object (the receipt)
     * @throws AccountNotFoundException if either account doesn't exist
     * @throws InsufficientFundsException if the sender has not enough money
     */
    Transaction makePayment(String fromAccountNumber, String toAccountNumber, double amount)
            throws AccountNotFoundException, InsufficientFundsException;

    /**
     * Gets the full transaction history for a single account.
     * @param accountNumber The account number
     * @return A List of transactions
     * @throws AccountNotFoundException if the account doesn't exist
     */
    List<Transaction> getTransactionHistory(String accountNumber)
            throws AccountNotFoundException;
}