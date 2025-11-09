// package
package com.paymentsystem.model;

// imports
import java.util.ArrayList;
import java.util.List;


public class User {
    private String userId;
    private String name;
    private String email;
    private String password;

    private List<Account> accounts;

    private List<Transaction> transactionsHistory;

    public User(){
        this.accounts= new ArrayList<>();
        this.transactionsHistory=new ArrayList<>();
    }

    public User(String userId,String name,String email,String password){
        this.userId=userId;
        this.email=email;
        this.name=name;
        this.password=password;

        this.accounts=new ArrayList<>();
        this.transactionsHistory= new ArrayList<>();
    }

    // getter and setter
    
    // 1- userId
    public String getUserId(){
        return this.userId;
    }
    public void setUserId(String userId){
        this.userId=userId;
    }

    // 2- name
    public String getName(){
        return this.name;
    }
    public void getName(String name){
        this.name=name;
    }

    // 3-email
    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email=email;
    }

    // 4- password 
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password=password;
    }

    // 5-Account
    public void addAccount(Account account){
        this.accounts.add(account);
    }
    public List<Account> getAccount(){
        return this.accounts;
    }

    // 6-Transaction
    public void addTransaction(Transaction transaction){
        this.transactionsHistory.add(transaction);
    }
    public List<Transaction> getTransactionHistory(){
        return this.transactionsHistory;
    }
    
    @Override
    public String toString(){
        return "User{"+
                "userId='" + userId + '\''+
                ", name='" + name + '\''+
                ", email='" + email + '\''+
                ", password='" + password + '\''+
                ", accountsCount=" + accounts.size() + 
                ", transactionCount="+ transactionsHistory.size() +
                '}';
    }
}
