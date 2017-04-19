import customer.Customer;

public class Occupant {
   
   private Customer customer;
   private int numOfGuests = 0;
   
   public Occupant(Customer customer, int numOfGuests) {
      this.customer = customer;
      this.numOfGuests = numOfGuests;
   }
   
   public void setCustomer(Customer customer) {
      this.customer = customer;
   }
   
   public void setNumOfGuests(int numOfGuests) {
      this.numOfGuests = numOfGuests;
   }
   
   public Customer getCustomer() { return customer; }
   public int getNumOfGuests() { return numOfGuests; }
   
   @Override
   public String toString() {
      return String.format("%s, %s", customer.getLastName(), customer.getFirstName());
   }
}
