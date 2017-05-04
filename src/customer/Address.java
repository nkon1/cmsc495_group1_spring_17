package customer;
/** Edited 04/28/2016 by Tiff, combined house number & street
 *      Added zip variable and methods
 *  Edited 05/03/2017 by Tiff
 *  	added blank Address constructor
 */

public class Address {
   private String streetAddress;
   private String city;
   private String state;
   private int zip;
   
   public Address() {
	   
   }
   
   public Address(String streetAddress, String city, String state, int zip) {
      this.streetAddress = streetAddress;
      this.city = city;
      this.state = state;
      this.zip = zip;
   }
      
   public void setStreetAddress(String streetAddress) {
      this.streetAddress = streetAddress;
   }
   
   public void setCity(String city) {
      this.city = city;
   }
   
   public void setState(String state) {
      this.state = state;
   }
   
   public void setZip(int zip) {
	   this.zip= zip;
   }
   
   public String getStreetAddress() { return streetAddress; }
   public String getCity() { return city; }
   public String getState() { return state; }
   public int getZip() { return zip; }
}
