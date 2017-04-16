
public class Occupant {
   
   private String name;
   private int numOfGuests = 0;
   
   public Occupant(String name, int numOfGuests) {
      this.name = name;
      this.numOfGuests = numOfGuests;
   }
   
   public void setName(String name) {
      this.name = name;
   }
   
   public void setNumOfGuests(int numOfGuests) {
      this.numOfGuests = numOfGuests;
   }
   
   public String getName() { return name; }
   public int getNumOfGuests() { return numOfGuests; }
}
