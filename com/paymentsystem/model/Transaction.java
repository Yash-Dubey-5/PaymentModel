//package
package com.paymentsystem.model;

//for time
import java.time.LocalDateTime;

// class
public class Transaction {
    private String fromAccountNumber;
    private String toMerchantAccount;
    private String transactionId;
    private double amount;
    private LocalDateTime timestamp;
    private String status;

    //default
    public Transaction(){}

    // constructor needed
    public Transaction(String fromAccountNumber,String toMerchantAccount,String transactionId,
                        double amount,LocalDateTime timestamp, String status){  
        this.fromAccountNumber=fromAccountNumber;
        this.toMerchantAccount=toMerchantAccount;
        this.transactionId=transactionId;
        this.amount=amount;
        this.timestamp=timestamp;
        this.status=status;
    }

    // getter and setter
    
    // 1-fromAccountNumber
    public String getFromAccountNumber(){
        return this.fromAccountNumber;
    }
    public void setFromAccountNumber(String fromAccountNumber){
        this.fromAccountNumber=fromAccountNumber;
    }

    // 2- toMerchantOrAccount
    public String getToMerchantAccount(){
        return this.toMerchantAccount;
    }
    public void setToMerchantAccount(String toMerchantAccount){
        this.toMerchantAccount=toMerchantAccount;
    }

    // 3- transactionId
    public String getTransactionId(){
        return this.transactionId;
    }
    public void setTransactionId(String transactionId){
        this.transactionId=transactionId;
    }

    // 4- Amount
    public double getAmount(){
        return this.amount;
    }
    public void setAmount(double amount){
        this.amount=amount;
    }

    // 5-timestamp
    public LocalDateTime gettimestamp(){
        return this.timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp){
        this.timestamp=timestamp;
    }

    // 6- Status
    public String getStatus(){
        return this.status;
    }
    public void setStatus(String status){
        this.status=status;
    }

    // overridding
    @Override
    public String toString(){
        return "Transaction {"+
                ", from= '"+ fromAccountNumber +'\''+
                ", to= '"+ toMerchantAccount +'\''+
                ", transactionId= '" + transactionId +'\''+
                ", amount= " + amount +
                ", timestamp= " + timestamp +
                ", status = '" + status + '\''+
                '}';
    }
}
