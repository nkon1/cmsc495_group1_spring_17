package hotel;
import view.LoginView;
import view.MainView;
import view.StarterView;

import java.util.Scanner;

public class HotelReservationSimulator {

   public static void main(String[] args) {
      // TODO Auto-generated method stub
      StarterView sv = new StarterView();
      MainView mainView = new MainView(sv);
      Controller controller = new Controller(new Model(), mainView);
      Hotel hotel = new Hotel();
   }

}
