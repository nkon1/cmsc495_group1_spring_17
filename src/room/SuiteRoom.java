package room;

public class SuiteRoom implements Room {

   private int NUMBER_OF_BEDS = 2;
   private int NUMBER_OF_OCCUPANTS = 4;
   private double cost = 179.99;
   
   @Override
   public int getNumOfBeds() {
      // TODO Auto-generated method stub
      return NUMBER_OF_BEDS;
   }

   @Override
   public int getMaxOccupants() {
      // TODO Auto-generated method stub
      return NUMBER_OF_OCCUPANTS;
   }

   @Override
   public double getCost() {
      // TODO Auto-generated method stub
      return cost;
   }

   @Override
   public RoomType getRoomType() {
      // TODO Auto-generated method stub
      return RoomType.SUITE;
   }
}
