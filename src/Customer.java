
public class Customer {
   private String firstName;
   private String lastName;
   private Address address;
   
   Customer(String firstName, String lastName, Address address) {
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
   
   public String getFirstName() { return firstName; }
   public String getLastName() { return lastName; }
   public Address getAddresss() { return address; }
}
