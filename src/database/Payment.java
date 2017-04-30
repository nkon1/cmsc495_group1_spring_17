package database;
import java.util.Date;

import customer.Customer;
import customer.Address;


public class Payment {
    
    private static String accountNumber;
    private static Date expirationDate;
    private static String securityNum;
    
    private Customer cardHolder;
    private Address billing;
    
    public enum cardType{
        VISA, MASTERCARD, AMEX
    }
    
    private static cardType cardBrand;

    public Payment(String cardNumber, Date expireDate, Customer cardHolder, Address billing, String securityNum){
        this.accountNumber = cardNumber;
        this.expirationDate = expireDate;
        this.cardHolder = cardHolder;
        this.billing = billing;
        this.securityNum = securityNum;
    }

    public String getAccount(){
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
