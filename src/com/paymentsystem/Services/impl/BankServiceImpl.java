package com.paymentsystem.Services.impl;

import com.paymentsystem.exception.AccountNotFoundException;
import com.paymentsystem.exception.BankNotFoundException;
import com.paymentsystem.exception.UserNotFoundException;
import com.paymentsystem.model.Account;
import com.paymentsystem.model.Bank;
import com.paymentsystem.model.User;
import com.paymentsystem.repository.IAccountRepository;
import com.paymentsystem.repository.IBankRepository;
import com.paymentsystem.repository.IUserRepository;
import com.paymentsystem.Services.IBankService;
import com.paymentsystem.util.IdGenerator;


import java.util.List;
// import java.util.Optional;

public class BankServiceImpl implements IBankService {
    
    private final IBankRepository bankRepository;
    private final IAccountRepository accountRepository;
    private final IUserRepository userRepository;

    public BankServiceImpl(IBankRepository bankRepository,IAccountRepository accountRepository,IUserRepository userRepository){
        this.bankRepository = bankRepository;
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Bank createBank(String bankName,String ifscCode){
        Bank newBank = new Bank(bankName , ifscCode);
        return bankRepository.save(newBank);
    }

    @Override
    public Account createAccount(String userId,String ifscCode,double initialDeposit)throws UserNotFoundException,BankNotFoundException{
        // finding user
        User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User not found " + userId));

        // finding bank
        Bank bank = bankRepository.findByIfscCode(ifscCode).orElseThrow(() -> new BankNotFoundException("Bank not found: "+ ifscCode));

        // creste new account
        String newAccountNumber = IdGenerator.generateAccountNumber();
        Account newAccount = new Account(newAccountNumber,user,bank,initialDeposit);

        // linking
        user.addAccount(newAccount);
        bank.addAccount(newAccount);

        return accountRepository.save(newAccount);
    }

    @Override
    public double  getBalance(String accountNumber)throws AccountNotFoundException{
        // find account or throw error
        Account account = accountRepository.findByAccountNumber((accountNumber)).orElseThrow(() -> new AccountNotFoundException("Account not found:" + accountNumber));
    
        return account.getBalance();
    }

    @Override
    public List<Account> getUserAccounts(String userId)throws UserNotFoundException{
        // user exists
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found : "+ userId));

        // get accounts
        return accountRepository.findByOwnerUserId(userId);
    }
}
