package customer;

public class Address {
   private int houseNumber;
   private String streetName;
   private String city;
   private String state;
   
   public Address(int houseNumber, String streetName, String city, String state) {
      this.houseNumber = houseNumber;
      this.streetName = streetName;
      this.city = city;
      this.state = state;
   }
   
   public void setHouseNumber(int houseNumber) {
      this.houseNumber = houseNumber;
   }
   
   public void setStreetName(String streetName) {
      this.streetName = streetName;
   }
   
   public void setCity(String city) {
      this.city = city;
   }
   
   public void setState(String state) {
      this.state = state;
   }
   
   public int getHouseNumber() { return houseNumber; }
   public String getStreetName() { return streetName; }
   public String getCity() { return city; }
   public String getState() { return state; }
}
