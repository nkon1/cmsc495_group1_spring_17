package database;

import customer.Customer;



//import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import database.Payment.cardType;

import java.sql.*;

/** Created 04/23/2016 by Tiff 
 *     	-addCustomerToDatabase()
 *     	-createSalt()
 *     	-getEncryptedPassword()
 *  Edited 04/28/2016 by Tiff
 *  	-getCustomerFromDatabase()
 *  	-addPaymentToDatabase()
 *  	-getConnection()
 */


public class DataAccessObjectImpl implements DataAccessObject {
	/** TODO: add getReservationFromDatabase()
	 * 		  add addReservationToDatabase()
	 */
	
	@Override
	public boolean addCustomerToDatabase(Customer customer) {
        try (Connection con = getConnection()) {
        	// check if user with that email already exists
        	String query = "SELECT * FROM User WHERE email = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, customer.getEmail());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
            	// customer exists, insert not completed
            	con.close(); 
                return false;
            }
            else {
            	String email = customer.getEmail();
            	String firstName = customer.getFirstName();
            	String lastName = customer.getLastName();
            	String password = customer.getPassword();
            	byte[] salt;  
            	// default value for employeeStatus is always false, requires admin 
            	// rights to reflect true
            	Boolean employeeStatus = false;
            	byte[] ePassword;
            	// insert customer into database
            	try {
					salt = createSalt();
					ePassword = getEncryptedPassword(password, salt);
					query = "INSERT INTO User (email, firstName, lastName, ePassword, salt, "
	                		+ "employeeStatus) VALUES (?,?,?,?,?,?)";             
	                pstmt = con.prepareStatement(query);
	                pstmt.setString(1, email);
	                pstmt.setString(2, firstName);
	                pstmt.setString(3, lastName);
	                pstmt.setBytes(4, ePassword);
	                pstmt.setBytes(5, salt);
	                pstmt.setBoolean(6, employeeStatus);             	                
	                pstmt.execute();
	                con.close();
	                // customer inserted
	                return true;
				} catch (Exception e) {					
					e.printStackTrace();
					return false;
				}                
            }           
        } catch(ClassNotFoundException | SQLException e) {
        	e.printStackTrace();       
            return false;
        }
	}
	
	public Customer getCustomerFromDatabase(String email) {
		try (Connection con = getConnection()) {
			// define variables
			Customer queryCustomer = new Customer();
			// check database for customer
        	String query = "SELECT * FROM User WHERE email = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
            	String firstName = rs.getString("firstName");
            	String lastName = rs.getString("lastName");
            	String ePassword = new String(rs.getBytes("ePassword"));
            	byte[] salt = rs.getBytes("salt");
            	boolean employeeStatus = rs.getBoolean("employeeStatus");
            	
            	// set values
            	queryCustomer.setFirstName(firstName);
            	queryCustomer.setLastName(lastName);
            	queryCustomer.setPassword(ePassword);
            	queryCustomer.setSalt(salt);
            	queryCustomer.setEmployeeStatus(employeeStatus);
            	
            	con.close(); 
            	return queryCustomer;            
            }
            else {
            	con.close();
            	return null;            	
            }
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	
	public boolean addPaymentToDatabase(Payment payment) {
		try (Connection con = getConnection()) {
			// define variables
			String user_email = payment.getCardHolder().getEmail();			
			String cardHolderName = payment.getCardHolder().getFirstName() + " " +
					payment.getCardHolder().getLastName();
			cardType cardType = payment.getCardType();
			long cardNumber = payment.getAccount();
			Date expireDate = (Date) Payment.getExp();
			String billingAddress1 = payment.getBillingAddress().getStreetAddress();
			String billingCity = payment.getBillingAddress().getCity();
			String billingState = payment.getBillingAddress().getState();
			int billingZip = payment.getBillingAddress().getZip();
			
			String query = "INSERT INTO Payment (User_email, cardHolderName, cardType, "
					+ "cardNumber, expireDate, billingAddress1, billingCity, "
					+ "billingState, billingZip) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, user_email);
            pstmt.setString(2, cardHolderName);
            pstmt.setString(3, cardType.name());
            pstmt.setLong(4, cardNumber);
            pstmt.setDate(5, expireDate);
            pstmt.setString(6, billingAddress1);
            pstmt.setString(7, billingCity);
            pstmt.setString(8, billingState);
            pstmt.setInt(9, billingZip);
            pstmt.execute();
            con.close();
            // payment inserted
            return true;
		}catch(ClassNotFoundException | SQLException e){
			e.printStackTrace();
			return false;
		}
	}
	
	 //maybe modify Reservation class? Need start and end dates.
//	public Boolean addReservationToDatabase(Reservation reservation) {
//		try (Connection con = getConnection()) {
//			// define variables
//			String User_email = reservation.getOccupant().getCustomer().getEmail();
//			int Rooms_roomID = reservation.getRoom().//roomid?
//			int Payment_payID = reservation.getPayment().//paymentID
//			
//			
//		}catch(ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//			return false;
//		}
//		
//	}
//	

    public byte[] createSalt() throws NoSuchAlgorithmException {
        // VERY important to use SecureRandom instead of just Random
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        // Generate a 8 byte (64 bit) salt
        byte[] dsalt = new byte[8];
        random.nextBytes(dsalt);
        return dsalt;
    }
    
    public byte[] getEncryptedPassword(String password, byte[] salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        // PBKDF2 with SHA-1 as the hashing algorithm. 
        String algorithm = "PBKDF2WithHmacSHA1";
        // SHA-1 generates 160 bit hashes
        int derivedKeyLength = 160;
        int iterations = 20000;
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, derivedKeyLength);
        SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);
        return f.generateSecret(spec).getEncoded();
    }
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(
            "jdbc:mysql://51.255.204.89:3306/hotelDB","admins","cmsc495");
        return con;
    }
    
}
