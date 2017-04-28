package database;
import java.util.Date;

import customer.Customer;
import customer.Address;


public class Payment {
    
    private static long accountNumber;
    private static Date expirationDate;
    
    private Customer cardHolder;
    private Address billing;
    
    public enum cardType{
        VISA, MASTERCARD, AMEX
    }
    
    private static cardType cardBrand;

    public Payment(long cardNumber, Date expireDate, Customer cardHolder, Address billing){
        Payment.accountNumber = cardNumber;
        this.expirationDate = expireDate;
        this.cardHolder = cardHolder;
        this.billing = billing;
    }

    public long getAccount(){
        return accountNumber;
    }

    public static Date getExp(){
        return expirationDate;
    }
    
    public cardType getCardType(){
        return cardBrand;
    }
    
    public Address getBillingAddress() {
    	return billing;
    }
    
    public Customer getCardHolder() {
    	return cardHolder;
    }
    
}
