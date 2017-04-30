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
   private String password;
   private byte[] salt;
   private boolean employeeStatus;
   
   public Customer () {
	   
   }
   
   public Customer(String firstName, String lastName, String password, byte[] salt, boolean employeeStatus) {
	   this.firstName = firstName;
	   this.lastName = lastName;
	   this.password = password;
	   this.salt = salt;
	   this.employeeStatus = employeeStatus;
   }
   
   public Customer(String firstName, String lastName) {
	  this.employeeStatus = false;
      this.firstName = firstName;
      this.lastName = lastName;   
   }
   
   public Customer(String firstName, String lastName, String password, String email) {
	   this.firstName = firstName;
	   this.lastName = lastName;
	   this.password = password;
	   this.email = email;
	   this.employeeStatus = false;
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
   
   public void setPassword(String password) {
	   this.password = password;
   }
   
   public void setSalt(byte[] salt) {
	   this.salt = salt;
   }
   
   public void setEmployeeStatus(boolean employeeStatus) {
	   this.employeeStatus = employeeStatus;
   }
   
   public String getFirstName() { return firstName; }
   public String getLastName() { return lastName; }
   public String getEmail() { return email; }
   public String getPassword() { return password; }
   public byte[] getSalt() { return salt; }
   public boolean getEmployeeStatus() { return employeeStatus; }
   
   public String toString() {
      return String.format("%s, %s", lastName, firstName);
   }




}
