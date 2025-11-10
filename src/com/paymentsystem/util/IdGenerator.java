package com.paymentsystem.util;

import java.util.UUID;
public class IdGenerator {
    public static String generateUserId(){
        return UUID.randomUUID().toString().substring(0,8);
    }

    public static String generateTransactionId(){
        return "TXN-"+UUID.randomUUID().toString().substring(0,12);
    }
}
