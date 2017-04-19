import room.Room;
public class Reservation {

   private Occupant occupant;
   private Room room;
   private int numOfNights = 0;
   private double cost = 0;
  
   public Reservation(Occupant occupant, Room room, int numOfNights) {
      this.occupant = occupant;
      this.room = room;
      this.numOfNights = numOfNights;
      cost = calculateCost();
   }
   
   public void setOccupant(Occupant occupant) {
      this.occupant = occupant;
   }
   
   public void setRoom(Room room) {
      this.room = room;
   }
   
   public void setNumOfNights(int numOfNights) {
      this.numOfNights = numOfNights;
   }
   
   private double calculateCost() {
      cost = room.getCost() * numOfNights;
      return cost;
   }
   
   public Occupant getOccupant() { return occupant; }
   public Room getRoom() { return room; }
   public int getNumOfNights() { return numOfNights; }
   public double getCost() { return cost; }
}
