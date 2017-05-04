package hotel;

import java.io.IOException;

import customer.Customer;
import database.DataAccessObjectImpl;

public class Model {
   
   private DataAccessObjectImpl dao;
    
    public Model(DataAccessObjectImpl dao) {
       this.dao = dao;
    }
    
    public boolean addCustomer(Customer customer) {
       return dao.addCustomerToDatabase(customer);
    }
    
    public Customer getCustomer(String email) throws IOException {
       return dao.getCustomerFromDatabase(email);
    }
}
