package hotel;
import java.util.Date;

import room.Room;
public class Reservation {

   private Occupant occupant;
   private Room room;
   private Date date;
   private int numOfNights = 0;
   private double cost = 0;
  
   public Reservation(Occupant occupant, Room room, Date date, int numOfNights) {
      this.occupant = occupant;
      this.room = room;
      this.date = date;
      this.numOfNights = numOfNights;
      cost = calculateCost();
   }
   
   public void setOccupant(Occupant occupant) {
      this.occupant = occupant;
   }
   
   public void setRoom(Room room) {
      this.room = room;
   }
   
   public void setDate(Date date) {
      this.date = date;
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
   public Date getDate() { return date; }
   public int getNumOfNights() { return numOfNights; }
   public double getCost() { return cost; }
}
