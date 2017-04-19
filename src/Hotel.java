import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import room.SuiteRoom;
import room.StudioRoom;
import room.Room;

public class Hotel {

   private String NAME = "The Overlook";
   private int NUMBER_OF_ROOMS = 200;
   private int activeReservations = 0;
   
   private List<Reservation> reservations = new ArrayList<>();
   
   public void requestRoom(int numOfRooms) {
      Scanner scanner = new Scanner(System.in);
      if(canReserveRoom(numOfRooms)) {
         
         System.out.print("Enter your name: ");
         String occupantName = scanner.nextLine();
         
         System.out.print("Enter number of guests to stay in the room: ");
         int numOfGuests = Integer.parseInt(scanner.nextLine());
         
         System.out.println("Select your room type\n[0] Studio - 1 Bed\n[1] Suite - 2 Beds");
         int roomType = Integer.parseInt(scanner.nextLine());
         while(roomType != 0 && roomType != 1) {
            System.out.print(String.format("%d is an invalid selection!\nSelect your room type\n[0] Studio (1 Bed)\n[1]Suite (2 Beds)", roomType));
            roomType = Integer.parseInt(scanner.nextLine());
         }
         
         Room room = null;
         if(roomType == 0) {
            room = new StudioRoom();
         } else {
            room = new SuiteRoom();
         }
         
         System.out.print("Enter the number of nights you want to stay: ");
         int numOfNights = Integer.parseInt(scanner.nextLine());
         
         //reservations.add(new Reservation(new Occupant(new Customer(), numOfGuests), room, numOfNights));
      } else {
         System.out.println("No rooms available for reserving");
      }
   }
   
   private boolean canReserveRoom(int numOfRooms) {
      if(activeReservations < NUMBER_OF_ROOMS) {
         activeReservations += numOfRooms;
         return true;
      }
      return false;
   }
   
   public String getName() {
      return NAME;
   }
   
   public int getNumberOfRooms() {
      return NUMBER_OF_ROOMS;
   }
   
   public int getNumOfActiveReservations() {
      return activeReservations;
   }
   
   public List<Reservation> getReservations() { return reservations; }
   
   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      for(Reservation reservation : reservations) {
         sb.append(String.format("\n\n==================== Reservation for %s ====================\n\n", 
               reservation.getOccupant().toString()));
         sb.append(String.format("Occupant: %s\nGuests: %d\nNumber of Nights: %d\nCost of Room: %.2f",
               reservation.getOccupant().toString(),
               reservation.getOccupant().getNumOfGuests(),
               reservation.getNumOfNights(),
               reservation.getCost()));
      }
      return sb.toString();
   }
}
