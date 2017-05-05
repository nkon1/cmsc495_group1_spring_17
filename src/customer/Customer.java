package customer;

/**
 * Edited 04/28/2017 by Tiff
 * 		-added customer()
 * 		-added salt & employeeStatus variables and methods
 */

public class Customer {
   private String firstName;
   private String lastName;
   private String email;
   private char[] password;
   private byte[] salt;
   private byte[] ePassword;
   
   public Customer () {
	   
   }
   
   public Customer(String firstName, String lastName, char[] password, byte[] salt) {
	   this.firstName = firstName;
	   this.lastName = lastName;
	   this.password = password;
	   this.salt = salt;
   }
   
   public Customer(String firstName, String lastName) {
      this.firstName = firstName;
      this.lastName = lastName;   
   }
   
   public Customer(String firstName, String lastName, char[] password, String email) {
	   this.firstName = firstName;
	   this.lastName = lastName;
	   this.password = password;
	   this.email = email;
   }
   
   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }
   
   public void setLastName(String lastName) {
      this.lastName = lastName;
   }
   
   public void setEmail(String email) {
      this.email = email;
   }
   
   public void setPassword(char[] password) {
	   this.password = password;
   }
   
   public void setEPassword(byte[] ePassword) {
	   this.ePassword = ePassword;
   }
   
   public void setSalt(byte[] salt) {
	   this.salt = salt;
   }
   
   public String getFirstName() { return firstName; }
   public String getLastName() { return lastName; }
   public String getEmail() { return email; }
   public char[] getPassword() { return password; }
   public byte[] getSalt() { return salt; }
   public byte[] getEPassword() { return ePassword; }
   
   public String toString() {
      return String.format("%s, %s", lastName, firstName);
   }

   


}
