import java.util.Scanner;

public class HotelReservationSimulator {

   public static void main(String[] args) {
      // TODO Auto-generated method stub
      LoginView loginView = new LoginView();
      MainView mainView = new MainView(loginView);
      Controller controller = new Controller(new Model(), mainView);
      Hotel hotel = new Hotel();
      System.out.println(String.format("Welcome to %s!", hotel.getName()));
      System.out.println("Begin by selecting the number of rooms you'd like to request");
      
      Scanner scanner = new Scanner(System.in);
      int numOfReservations = Integer.parseInt(scanner.nextLine());
      
      int counter = 1;
      while(counter <= numOfReservations) {
         hotel.requestRoom(counter);
         counter++;
      }
      scanner.close();
      System.out.println(hotel);
   }

}
