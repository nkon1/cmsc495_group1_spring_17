package database;

import hotel.Reservation;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.SQLException;

import room.Room;
import customer.Customer;

/** Created 04/23/2016 by Tiff 
 *     	-addCustomerToDatabase()
 *     	-createSalt()
 *     	-getEncryptedPassword()
 *  Edited 04/28/2016 by Tiff
 *  	-getCustomerFromDatabase()
 *  	-addPaymentToDatabase()
 *  	-getConnection()
 *  Edited 04/30/2016 by Tiff
 *  	-addReservationToDatabase()
 *  	-getReservationFromDatabase()
 *  	-getRoomNumber()
 *  	-getRoom()
 *  	-getPaymentNumber()
 *  	-getPayment()
 *  Edited 05/03/2017 by Tiff
 *  	-changed methods to take password as char[]
 */

public interface DataAccessObject {

	/**
	 * Access to the database to add a customer to the database.
	 * @param customer The customer object
	 * @param payment The payment object
	 * @return success If the customer insertion was successful.
	 */
	public boolean addCustomerToDatabase(Customer customer);
	
	
	/**
	 * 
	 * @param reservation
	 * @return success If reservation insertion was successful
	 */
	public boolean addReservationToDatabase(Reservation reservation);
	
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
	 * @return connection to database
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		return null;
	}
	
	public Reservation getReservationFromDatabase(int bookingID) throws IOException;
	
	public int getRoomNumber(Room room);
	public Room getRoom(int roomID);
	public int getPaymentNumber(Payment payment);
	public Payment getPayment(int payID); 

}
