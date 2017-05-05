package hotel;
import view.MainView;
import view.StarterView;
import java.sql.SQLException;

import database.DataAccessObjectImpl;

public class HotelReservationSimulator {

   public static void main(String[] args) throws ClassNotFoundException, SQLException {
      // TODO Auto-generated method stub
      StarterView sv = new StarterView();
      MainView mainView = new MainView(sv);
      Controller controller = new Controller(new Model(new DataAccessObjectImpl()), mainView);
   }

}
