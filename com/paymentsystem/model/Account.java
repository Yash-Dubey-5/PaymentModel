package com.paymentsystem.model;

public class Account {
    private String accountNumber;
    private User owner;
    private double balance;
    private Bank bank;

    public Account(){}

    public Account(String accountNumber, User owner,double initialBalance, Bank bank){
        this.accountNumber=accountNumber;
        this.owner=owner;
        this.bank=bank;
        this.balance=initialBalance;
    }

    // Getter and Setter
    
    // 1- accountNumber
    public String getAccountNumber(){
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber){
        this.accountNumber=accountNumber;
    }
    
    // 2-Owner
    public User getOwner(){
        return this.owner;
    }
    public void setOwner(User owner){
        this.owner=owner;
    }

    // 3-Bank
    public Bank getBank(){
        return this.bank;
    }
    public void setBank(Bank bank){
        this.bank=bank;
    }

    // 4- Balance
    public double getBalance(){
        return this.balance;
    }

    public void deposit(double amount){
        if(amount>0){
            this.balance+=amount;
            System.out.println("Deposite successfully.\nNew Balance = "+this.balance);            
        }else{
            System.out.println("Invalid deposite amount.");
        }
    }

    public boolean withdraw(double amount){
        if(amount<=0){
            System.out.println("Invalid withdrawl amount.");
            return false;
        }
        if(this.balance>=amount){
            this.balance-=amount;
            System.out.println("Withdraw successfully.\nNew balance = "+this.balance);
            return true;
        }else{
            System.out.println("Insufficient funds. Withdrawal failed.");
            return false;
        }
    }

    @Override
    public String toString(){
        return  "Account{"+
                "accountNumber='" + accountNumber+'\''+
                ", ownerName='" + (owner !=null ? owner.getName() : "N/A") + '\''+
                ", balance=" + balance +
                ", bankName='" + (bank!=null ? bank.getBankName() : "N/A") + '\''+
                '}';
    }
}
