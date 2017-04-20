/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author BATZ
 */
public class Controller {
    
    private Customer User;
    private NewCustomerView newUserView;
    
    public Controller (Customer User, NewCustomerView newUserView){
        this.User=User;
        this.newUserView=newUserView;
    }
    
    public void setCustomerFirstName(String first){
        User.setFirstName(first);
    }
    
    public void setCustomerLastName(String last){
        User.setLastName(last);
    }
    
    public void setCustomerAddress(Address home){
        User.setAddress(home);
    }
    
    public void setCustomerEmail(String email){
        User.setEmail(email);
    }
    
    public void setNewUser(boolean newUser){
        User.setNewCustomer(newUser);
    }
    
    public String getCustomerFirstName(){
        return User.getFirstName();
    }
    
    public String getCustomerLastName(){
        return User.getLastName();
    }
    
    public Address getCustomerAddress(){
        return User.getAddresss();
    }
    
    public String getCustomerEmail(){
        return User.getEmail();
    }
    
    public boolean getNewUser(){
        return User.getNewCustomer();
    }
    
}
