package room;

public interface Room {
   public int getNumOfBeds();
   public int getMaxOccupants();
   public double getCost();
   public RoomType getRoomType();
   public boolean hasJacuzzi();
   public boolean hasFireplace();
   public boolean hasMicrowave();
}
