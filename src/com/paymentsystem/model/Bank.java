package com.paymentsystem.model;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    private String bankName;
    private String ifscCode;
    private List<Account>accounts;

    public Bank(){
        this.accounts=new ArrayList<>();
    }

    public Bank(String bankName,String ifscCode){
        this.bankName=bankName;
        this.ifscCode=ifscCode;
        this.accounts=new ArrayList<>();
    }

    // Getter and Setter

    // 1- bank name
    public String getBankName(){
        return this.bankName;
    }
    public void setBankName(String bankName){
        this.bankName=bankName;
    }

    // 2-ifsc Code
    public String getIfscCode(){
        return this.ifscCode;
    }
    public void setIfscCode(String ifscCode){
        this.ifscCode=ifscCode;
    }

    // 3-Account
    public List<Account> getAccount(){
        return this.accounts;
    }

    public void addAccount(Account account){
        this.accounts.add(account);
    }

    public Account getAccountByNumber(String accountNumber){
        for(Account acc : this.accounts){
            if(acc.getAccountNumber().equals(accountNumber)){
                return acc;
            }
        }
        return null;        
    }

    @Override
    public String toString(){
        return "Bank{" +
        "bankName= '"+bankName + '\''+
        ", ifscCode='" +ifscCode + '\''+
        ", accountCount=" + accounts.size()+
        '}';
    }
}
