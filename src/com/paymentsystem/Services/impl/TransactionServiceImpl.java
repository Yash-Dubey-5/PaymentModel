package com.paymentsystem.Services.impl;

import com.paymentsystem.exception.AccountNotFoundException;
import com.paymentsystem.exception.InsufficientFundsException;
import com.paymentsystem.model.Account;
import com.paymentsystem.model.Transaction;
import com.paymentsystem.repository.IAccountRepository;
import com.paymentsystem.repository.ITransactionRepository;
import com.paymentsystem.Services.ITransactionService;
import com.paymentsystem.util.IdGenerator;

import java.time.LocalDateTime;
import java.util.List;

public class TransactionServiceImpl implements ITransactionService {

    private final IAccountRepository accountRepository;
    private final ITransactionRepository transactionRepository;

    public TransactionServiceImpl(IAccountRepository accountRepository,ITransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction makePayment(String fromAccountNumber, String toAccountNumber, double amount)
            throws AccountNotFoundException, InsufficientFundsException {

        Account fromAccount = accountRepository.findByAccountNumber(fromAccountNumber).orElseThrow(() -> new AccountNotFoundException("Sender account not found: " + fromAccountNumber));

        Account toAccount = accountRepository.findByAccountNumber(toAccountNumber).orElseThrow(() -> new AccountNotFoundException("Receiver account not found: " + toAccountNumber));

        boolean success = fromAccount.withdraw(amount);

        if (success) {
            toAccount.deposit(amount);
            Transaction transaction = new Transaction(
                    IdGenerator.generateTransactionId(),
                    fromAccountNumber,
                    toAccountNumber,
                    amount,
                    LocalDateTime.now(),
                    "SUCCESS"
            );

            return transactionRepository.save(transaction);

        } else {
            throw new InsufficientFundsException("Insufficient funds for payment. " +
                    "Current balance: $" + fromAccount.getBalance());
        }
    }

    @Override
    public List<Transaction> getTransactionHistory(String accountNumber)
            throws AccountNotFoundException {

        accountRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new AccountNotFoundException("Account not found: " + accountNumber));

        return transactionRepository.findByAccountNumber(accountNumber);
    }
}