package customer;
/** Edited 04/28/2016 by Tiff, combined house number & street
 *      Added zip variable and methods
 */

public class Address {
   private static String streetAddress;
   private static String city;
   private static String state;
   private static int zip;
   
   public Address() {
	   
   }
   
   public Address(String streetAddress, String city, String state, int zip) {
      this.streetAddress = streetAddress;
      this.city = city;
      this.state = state;
      this.zip = zip;
   }
      
   public static void setStreetAddress(String streetAddress) {
      Address.streetAddress = streetAddress;
   }
   
   public static void setCity(String city) {
      Address.city = city;
   }
   
   public static void setState(String state) {
      Address.state = state;
   }
   
   public static void setZip(int zip) {
	   Address.zip= zip;
   }
   
   public String getStreetAddress() { return streetAddress; }
   public String getCity() { return city; }
   public String getState() { return state; }
   public int getZip() { return zip; }
}
