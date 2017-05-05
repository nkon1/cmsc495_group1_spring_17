package hotel;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

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
    public byte[] getDatabasePassword(String email) throws IOException {
    	return dao.getCustomerFromDatabase(email).getEPassword();
    }
    public byte[] getDatabaseSalt(String email) throws IOException {
    	return dao.getCustomerFromDatabase(email).getSalt();
    }
    public byte[] getEncryptedPassword(char[] password, byte[] salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        // PBKDF2 with SHA-1 as the hashing algorithm. 
        String algorithm = "PBKDF2WithHmacSHA1";
        // SHA-1 generates 160 bit hashes
        int derivedKeyLength = 160;
        int iterations = 20000;
        KeySpec spec = new PBEKeySpec(password, salt, iterations, derivedKeyLength);
        SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);
        return f.generateSecret(spec).getEncoded();
    }
    
    public boolean addReservationToDatabase(Reservation reservation) {
       return dao.addReservationToDatabase(reservation);
    }
}
