package database;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.SQLException;
import customer.Customer;

/** Created 04/23/2016 by Tiff 
 *     	-addCustomerToDatabase()
 *     	-createSalt()
 *     	-getEncryptedPassword()
 *  Edited 04/28/2016 by Tiff
 *  	-getCustomerFromDatabase()
 *  	-addPaymentToDatabase()
 *  	-getConnection()
 */

public interface DataAccessObject {
	/** TODO: add ReservationToDatabase(Reservation reservation)
	 *        add getReservationFromDatabase()
	 */
	
	/**
	 * Access to the database to add a customer to the database.
	 * @param customer The customer object
	 * @param payment The payment object
	 * @return success If the customer insertion was successful.
	 */
	public boolean addCustomerToDatabase(Customer customer);
	
	/**
	 * @param payment The payment object
	 * @return success If payment insertion was successful
	 */
	public boolean addPaymentToDatabase(Payment payment);
	
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
	
	/**
	 * 
	 * @return connection to database
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		return null;
	}

	

}
