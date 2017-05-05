package customer;
import java.util.Date;

/**
 * Edited 04/30/2016 by Tiff
 * 		-added setter methods
 * 		-added blank constructor
 * 		-added constructor without security number 
 */

public class Payment {
    
	 private static String email;
	 private static String cardHolderName;
	 private static String cardType;
    private static String accountNumber;
    private static Date expirationDate;
    private static String securityNum;
    
    private Customer cardHolder;
    private Address billing;
    
    public enum cardType{
        VISA, MASTERCARD, AMEX
    }
    
    private static cardType cardBrand;
    
    public Payment() {
    	
    }

    public Payment(String cardNumber, Date expireDate, Customer cardHolder, Address billing, String securityNum){
        this.accountNumber = cardNumber;
        this.expirationDate = expireDate;
        this.cardHolder = cardHolder;
        this.billing = billing;
        this.securityNum = securityNum;
    }
    
    public Payment(String email, String cardHolderName, String cardType, String accountNumber, 
        Date expireDate, Address billing){
        this.email = email;
        this.cardHolderName = cardHolderName;
        this.cardType = cardType;
        this.accountNumber = accountNumber;
        this.expirationDate = expireDate;        
        this.billing = billing;
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
    
    public void setEmail(String email) {
    	this.email = email; 
	}
    public void setCardHolderName(String cardHolderName) {
    	this.cardHolderName = cardHolderName; 
	}
    public void setCardType(String cardType) {
    	this.cardType = cardType;
	}
    public void setAccountNumber(String accountNumber) {
    	this.accountNumber = accountNumber;
	}
    public void setExpireDate(Date exiprationDate) {
    	this.expirationDate = expirationDate;
    }
    public void setAddress(Address billing) {
    	this.billing = billing;
    }
    
}
