package database;

import hotel.Occupant;
import hotel.Reservation;

import java.io.IOException;
//import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import room.ParadiseRoom;
import room.Room;
import room.StudioRoom;
import room.SuiteRoom;
import customer.Address;
import customer.Customer;
import database.Payment.cardType;

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
 *  	-edited methods to change pasword as char[]
 */


public class DataAccessObjectImpl implements DataAccessObject {
	
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
            	char[] password = customer.getPassword();
            	
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
	                
	                // debugging
	                System.out.println("Password is: " + password);
	                // end debugging
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
	
	public Customer getCustomerFromDatabase(String email) throws IOException {
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
            	byte[] ePassword = rs.getBytes("ePassword");
            	byte[] salt = rs.getBytes("salt");
            	
            	// set values
            	queryCustomer.setEmail(email);
            	queryCustomer.setFirstName(firstName);
            	queryCustomer.setLastName(lastName);
            	//String tempPassword = new String(ePassword);
            	//queryCustomer.setEPassword(tempPassword.toCharArray());
            	queryCustomer.setEPassword(ePassword);
            	queryCustomer.setSalt(salt);
            	
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
	
	public byte[] getPasswordFromDatabase(String email) throws IOException {
		try (Connection con = getConnection()) {
			// check database for customer
        	String query = "SELECT ePassword FROM User WHERE email = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
            	byte[] ePassword = rs.getBytes("ePassword");   	
            	con.close(); 
            	return ePassword;            
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
			String cardNumber = payment.getAccount();
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
            pstmt.setString(4, cardNumber);
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
	
	
	public boolean addReservationToDatabase(Reservation reservation) {
		try (Connection con = getConnection()) {
			// define variables
			String User_email = reservation.getOccupant().getCustomer().getEmail();
			int Rooms_roomID = getRoomNumber(reservation.getRoom());
			int Payment_payID = getPaymentNumber(reservation.getPayment());
			Date checkIn = (Date) reservation.getDate();
			int lengthOfStay = reservation.getNumOfNights();
			int numOfGuests = reservation.getOccupant().getNumOfGuests();
			double finalCost = reservation.getCost();
			
			String query = "INSERT INTO Booking (User_email, Rooms_roomID, Payment_payID, "
					+ "checkIn, lengthOfStay, numOfGuests, finalCost) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, User_email);
            pstmt.setInt(2, Rooms_roomID);
            pstmt.setInt(3, Payment_payID);
            pstmt.setDate(4, checkIn);
            pstmt.setInt(5, lengthOfStay);
            pstmt.setInt(6, numOfGuests);
            pstmt.setDouble(7, finalCost);
            pstmt.execute();
            con.close();
            return true;			
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public Reservation getReservationFromDatabase(int bookingID) throws IOException {
		try (Connection con = getConnection()) {
			// define variables
			Reservation queryReservation = new Reservation();
			// check database for customer
        	String query = "SELECT * FROM Booking WHERE bookingID = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, bookingID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
            	String User_email = rs.getString("User_email");
            	int Rooms_roomID = rs.getInt("Rooms_roomID");
            	int Payment_payID = rs.getInt("Payment_payID");
            	Date checkIn = rs.getDate(5);
            	int lengthOfStay = rs.getInt("lengthOfStay");
            	int numOfGuests = rs.getInt("numOfGuests");
            	double finalCost = rs.getDouble("finalCost");
            	
            	Customer queryCustomer = getCustomerFromDatabase(User_email);            	
            	Occupant queryOccupant = new Occupant(queryCustomer, numOfGuests);
            	Room queryRoom = getRoom(Rooms_roomID);
            	Payment queryPayment = getPayment(Payment_payID);       	
            	
            	// set values
            	queryReservation.setBookingID(bookingID);
            	queryReservation.setOccupant(queryOccupant);
            	queryReservation.setRoom(queryRoom);
            	queryReservation.setPayment(queryPayment);;
            	queryReservation.setDate(checkIn);
            	queryReservation.setNumOfNights(lengthOfStay);
            	queryReservation.setCost(finalCost);
            	
            	con.close(); 
            	return queryReservation;            
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
	
	public int getRoomNumber(Room room) {
		try (Connection con = getConnection()) {
			// define variables
			String roomtype = room.getRoomType().toString();
			boolean isAvailable = true;
			
			String query = "SELECT roomID FROM Rooms WHERE roomType = ? AND isAvailable = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, roomtype);
            pstmt.setBoolean(2, isAvailable);            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
            	int roomNumber = rs.getInt("roomID");
            	con.close();
            	return roomNumber; 
            } else {
            	con.close();
            	return 0;
            }
		}catch(ClassNotFoundException | SQLException e){
			e.printStackTrace();
			return 0;
		}	
	}
	
	public Room getRoom(int roomID) {
		Room room;
		
		try (Connection con = getConnection()) {
			// check database for customer
        	String query = "SELECT roomType FROM Rooms WHERE roomID = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, roomID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
            	String roomType = rs.getString("roomType");
//            	boolean available = rs.getBoolean("available");
//            	boolean kingBed = rs.getBoolean("kingBed");
//            	boolean queenBed = rs.getBoolean("queenBed");
//            	boolean microwave = rs.getBoolean("microwave");
//            	boolean fireplace = rs.getBoolean("fireplace");
//            	boolean jacuzzi = rs.getBoolean("jacuzzi");
//            	int numOfBeds = rs.getInt("numOfBeds");
//            	int maxOccupancy = rs.getInt("maxOccupancy");
//            	double cost = rs.getDouble("cost");
            	switch (roomType) {
            		case "PARADISE" : room = new ParadiseRoom(); break;
            		case "STUDIO" : room = new StudioRoom(); break;
            		case "SUITE" : room = new SuiteRoom(); break;
            		default : room = new StudioRoom(); break;
            	}
            	
            	return room;
            	
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
	
	public int getPaymentNumber(Payment payment) {
		try (Connection con = getConnection()) {
			// define variables
		    String accountNumber = payment.getAccount();		 
			
			String query = "SELECT payID FROM Payment WHERE cardNumber = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, accountNumber);      
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
            	int payID = rs.getInt("payID");
            	con.close();
            	return payID; 
            } else {
            	con.close();
            	return 0;
            }
		}catch(ClassNotFoundException | SQLException e){
			e.printStackTrace();
			return 0;
		}	
	}
	
	public Payment getPayment(int payID) {
		Payment queryPayment = new Payment();
		
		try (Connection con = getConnection()) {
			// check database for customer
        	String query = "SELECT * FROM Payment WHERE payID = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, payID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
            	String User_email = rs.getString("User_email");
            	String cardHolderName = rs.getString("cardHolderName");
            	String cardType = rs.getString("cardType");
            	String cardNumber = rs.getString("cardNumber");
            	Date expireDate = rs.getDate("expireDate");
            	String billingAddress1 = rs.getString("billingAddress1");
            	String billingCity = rs.getString("billingCity");
            	String billingState = rs.getString("billingState");
            	int billingZip = rs.getInt("billingZip");
            	
            	Address queryAddress = new Address(billingAddress1, billingCity, billingState, billingZip);
            	
            	queryAddress.setStreetAddress(billingAddress1);
            	queryAddress.setCity(billingCity);
            	queryAddress.setState(billingState);
            	queryAddress.setZip(billingZip);
            	
            	queryPayment.setEmail(User_email);
            	queryPayment.setCardHolderName(cardHolderName);
            	queryPayment.setCardType(cardType);
            	queryPayment.setAccountNumber(cardNumber);
            	queryPayment.setExpireDate(expireDate);
            	queryPayment.setAddress(queryAddress);
            	
            	return queryPayment;            	
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

    public byte[] createSalt() throws NoSuchAlgorithmException {
        // VERY important to use SecureRandom instead of just Random
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        // Generate a 8 byte (64 bit) salt
        byte[] dsalt = new byte[8];
        random.nextBytes(dsalt);
        return dsalt;
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
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(
            "jdbc:mysql://51.255.204.89:3306/hotelDB","admins","cmsc495");
        return con;
    }
    
}
