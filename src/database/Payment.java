package database;
import customer.Customer;
import customer.Address;


public class Payment {
    
    private long accountNumber;
    private int expirationDate;
    
    private Customer cardHolder;
    private Address billing;
    
    private enum cardType{
        VISA, MASTERCARD, AMEX
    }
    
    private cardType cardBrand;

    public Payment(long accountNumber, int expirationDate, Customer cardHolder, Address billing){
        this.accountNumber = accountNumber;
        this.expirationDate = expirationDate;
        this.cardHolder = cardHolder;
        this.billing = billing;
    }

    public long getAccount(){
        return accountNumber;
    }

    public int getExp(){
        return expirationDate;
    }
    
    public cardType getCardType(){
        return cardBrand;
    }
}
