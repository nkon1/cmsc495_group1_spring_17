package room;

public class StudioRoom implements Room {
   
   private int NUMBER_OF_BEDS = 1;
   private int NUMBER_OF_OCCUPANTS = 2;
   private double cost = 149.99;
   
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
}
