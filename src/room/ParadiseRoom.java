package room;
public class ParadiseRoom implements Room {

   private int NUMBER_OF_BEDS = 2;
   private int NUMBER_OF_OCCUPANTS = 4;
   private double cost = 279.99;
   
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

   public boolean hasJacuzzi(){
	    return true;
   }

   public boolean hasFireplace(){
	    return true;
   }

   public boolean hasMicrowave(){
	    return true;
   }

   @Override
   public RoomType getRoomType() {
      // TODO Auto-generated method stub
      return RoomType.PARADISE;
   }

}
