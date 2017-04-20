package database;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author BATZ
 */
public class Payment {
    
    private long accountNumber;
    private int expirationDate;

    private Customer cardHolder;
    private Address billing;

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
    
}
