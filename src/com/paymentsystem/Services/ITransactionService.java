package com.paymentsystem.Services;

import com.paymentsystem.exception.AccountNotFoundException;
import com.paymentsystem.exception.InsufficientFundsException;
import com.paymentsystem.model.Transaction;
import java.util.List;

public interface ITransactionService {

    Transaction makePayment(String fromAccountNumber, String toAccountNumber, double amount)throws AccountNotFoundException, InsufficientFundsException;

    List<Transaction> getTransactionHistory(String accountNumber)throws AccountNotFoundException;
}