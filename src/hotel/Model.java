package hotel;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.bind.DatatypeConverter;

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
    public byte[] getEncryptedPassword(String password)
            throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
        // SHA-1 as the hashing algorithm. 
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		digest.reset();
		byte[] ePassword = digest.digest(password.getBytes("UTF-8"));
		byte[] tempPassword = DatatypeConverter.printHexBinary(ePassword).getBytes("UTF-8");
		byte[] finalEPassword = new byte[20];
		System.arraycopy(tempPassword, 0, finalEPassword, 0, finalEPassword.length);
		return finalEPassword;
    }
    public boolean addReservationToDatabase(Reservation reservation) {
       return dao.addReservationToDatabase(reservation);
    }
}
