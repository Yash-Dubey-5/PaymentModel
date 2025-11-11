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

    // 1. This service needs two repositories
    private final IAccountRepository accountRepository;
    private final ITransactionRepository transactionRepository;

    // 2. Inject them via the constructor
    public TransactionServiceImpl(IAccountRepository accountRepository,
                                  ITransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction makePayment(String fromAccountNumber, String toAccountNumber, double amount)
            throws AccountNotFoundException, InsufficientFundsException {

        // 3. --- Business Logic: Get Accounts ---
        Account fromAccount = accountRepository.findByAccountNumber(fromAccountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Sender account not found: " + fromAccountNumber));

        Account toAccount = accountRepository.findByAccountNumber(toAccountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Receiver account not found: " + toAccountNumber));

        // 4. --- Business Logic: Perform Transfer ---
        // We call the 'withdraw' method on the Account model.
        // That method (which we wrote in Step 1.D) already contains
        // the logic to check for sufficient funds and returns 'true' or 'false'.
        boolean success = fromAccount.withdraw(amount);

        if (success) {
            // 5. Withdrawal was successful, now deposit
            toAccount.deposit(amount);

            // 6. Create the transaction "receipt"
            Transaction transaction = new Transaction(
                    IdGenerator.generateTransactionId(),
                    fromAccountNumber,
                    toAccountNumber,
                    amount,
                    LocalDateTime.now(), // Use the current time
                    "SUCCESS"
            );

            // 7. Save the receipt and return it
            return transactionRepository.save(transaction);

        } else {
            // 8. The 'withdraw' method failed, so throw our exception
            throw new InsufficientFundsException("Insufficient funds for payment. " +
                    "Current balance: $" + fromAccount.getBalance());
        }
    }

    @Override
    public List<Transaction> getTransactionHistory(String accountNumber)
            throws AccountNotFoundException {

        // 9. First, check if the account even exists
        accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found: " + accountNumber));

        // 10. If it exists, get its history
        return transactionRepository.findByAccountNumber(accountNumber);
    }
}