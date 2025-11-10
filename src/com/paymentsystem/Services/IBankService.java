package com.paymentsystem.Services;

import com.paymentsystem.exception.AccountNotFoundException;
import com.paymentsystem.exception.BankNotFoundException;
import com.paymentsystem.exception.UserNotFoundException;
import com.paymentsystem.model.Account;
import com.paymentsystem.model.Bank;

import java.util.List;

public interface IBankService {
    
    Bank createBank(String bankName,String ifscCode);

    Account createAccount(String userId,String ifscCode, double initialDeposit)
        throws UserNotFoundException,BankNotFoundException;

    double getBalance(String accountNumber)throws AccountNotFoundException;

    List<Account>getUserAccounts(String userId)throws UserNotFoundException;
}
