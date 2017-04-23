package database;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import customer.Customer;

public interface DataAccessObject {
	/** TODO: add insertPaymentInfo method
	 * 		  add getCustomerFromDatabase()
	 * 		  add ReservationToDatabase(Reservation reservation)
	 *        add getReservationFromDatabase()
	 */
	
	/**
	 * Access to the database to add a customer to the database.
	 * @param customer The customer object
	 * @param payment The payment object
	 * @return success If the operation was successful.
	 */
	public boolean addCustomerToDatabase(Customer customer, Payment payment);
	
	/**
	 * 
	 * @return Salt
	 * @throws NoSuchAlgorithmException
	 */
	public byte[] createSalt() throws NoSuchAlgorithmException;
	
    /**
     * 
     * @param password
     * @param salt
     * @return encrypted password
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
	public byte[] getEncryptedPassword(String password, byte[] salt) 
			throws NoSuchAlgorithmException, InvalidKeySpecException;


}
