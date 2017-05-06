package hotel;
import java.util.Random;

import customer.Payment;
import room.Room;
public class Reservation {

   private Occupant occupant;
   private Room room;
   private Payment payment;
   private String date;
   private int numOfNights = 0;
   private double cost = 0;
   private int bookingID;
   
   public Reservation() {}
  
   public Reservation(Room room, String date, int numOfNights, double cost) {
      this.room = room;
      this.date = date;
      this.numOfNights = numOfNights;
      this.cost = cost;
   }
   
   public Reservation(Occupant occupant, Room room, Payment payment, String date, int numOfNights, double cost) {
      this.occupant = occupant;
      this.room = room;
      this.payment = payment;
      this.date = date;
      this.numOfNights = numOfNights;
      this.cost = cost;
      Random random = new Random();
      bookingID = random.nextInt(100000);
   }
   
   public void setBookingID(int bookingID) {
	   this.bookingID = bookingID;
   }
   
   public void setOccupant(Occupant occupant) {
      this.occupant = occupant;
   }
   
   public void setRoom(Room room) {
      this.room = room;
   }
   
   public void setPayment(Payment payment) {
	   this.payment = payment;
   }
   
   public void setDate(String date) {
      this.date = date;
   }
   
   public void setNumOfNights(int numOfNights) {
      this.numOfNights = numOfNights;
   }
   
   public void setCost(double cost) {
	   this.cost = cost;
   }
   
   public int getBookingID() {
      return bookingID;
   }
   
   public Occupant getOccupant() { return occupant; }
   public Room getRoom() { return room; }
   public Payment getPayment() { return payment; }
   public String getDate() { return date; }
   public int getNumOfNights() { return numOfNights; }
   public double getCost() { return cost; }
}
