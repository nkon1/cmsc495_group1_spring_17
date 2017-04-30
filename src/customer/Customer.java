package customer;

/**
 * Edited 04/28/2017 by Tiff
 * 		-added customer()
 * 		-added salt & employeeStatus variables and methods
 */

public class Customer {
   private String firstName;
   private String lastName;
   private Address address;
   private String email;
   private String password;
   private byte[] salt;
   private boolean employeeStatus;
   
   public Customer() {
	   this.firstName = firstName;
	   this.lastName = lastName;
	   this.password = password;
	   this.salt = salt;
	   this.employeeStatus = employeeStatus;
   }
   
   public Customer(String firstName, String lastName, Address address) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.address = address;      
   }
   
   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }
   
   public void setLastName(String lastName) {
      this.lastName = lastName;
   }
   
   public void setAddress(Address address) {
      this.address = address;
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
   public Address getAddresss() { return address; }
   public String getEmail() { return email; }
   public String getPassword() { return password; }
   public byte[] getSalt() { return salt; }
   public boolean getEmployeeStatus() { return employeeStatus; }
   
   public String toString() {
      return String.format("%s, %s", lastName, firstName);
   }




}
