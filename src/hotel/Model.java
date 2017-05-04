package hotel;

import customer.Address;
import customer.Customer;
import database.Payment;

public class Model {

    private Customer customer;
    private Address address;
    private Payment payment;
    
    public Model(Customer customer, Address address, Payment payment){
        this.customer = customer;
        this.address = address;
        this.payment = payment;
    }
    
    public void setCustomer(Customer customer){
        this.customer = customer;
    }
    
    public void setAddress(Address address){
        this.address = address;
    }
    
    public void setPayment(Payment payment){
        this.payment = payment;
    }
    
    public Customer getCustomer(){
        return customer;
    }
    
    public Address getAddress(){
        return address;
    }
    
    public Payment getPayment(){
        return payment;
    }
}
