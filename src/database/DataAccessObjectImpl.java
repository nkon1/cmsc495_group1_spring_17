package database;

import customer.Customer;

//import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
//import javax.crypto.spec.SecretKeySpec;
import java.sql.*;

public class DataAccessObjectImpl implements DataAccessObject {
	/** TODO: add insertPaymentInfo method
	 * 		  add getCustomerFromDatabase()
	 * 		  add ReservationToDatabase(Reservation reservation)
	 *        add getReservationFromDatabase()
	 */
	
	@Override
	public boolean addCustomerToDatabase(Customer customer, Payment payment) {
        try {
        	// establish connection to database
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://51.255.204.89:3306/hotelDB","admins","cmsc495");
            
            String query = "SELECT * FROM User WHERE email = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, customer.getEmail());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                // System.out.println("\nSorry, a user with that email already"
                //        + " exists.");
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
	                return true;
	                // System.out.println("insert completed");
				} catch (Exception e) {					
					e.printStackTrace();
					return false;
				}                
            }           
            // System.out.println(insert);
        } catch(ClassNotFoundException | SQLException e) {
        	e.printStackTrace();       
            return false;
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

}
