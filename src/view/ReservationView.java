package view;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import hotel.Reservation;

public class ReservationView extends JPanel implements View {
   private static final long serialVersionUID = 1944212264480093931L;
   
   public ReservationView() {
      this.add(createReservationsView());
   }
   
   private JScrollPane createReservationsView() {
      JPanel panel = new JPanel();
      JScrollPane scrollPane = new JScrollPane(panel);
      panel.setBorder(BorderFactory.createTitledBorder("Reservations"));
      return scrollPane;
   }
   
   public void addReservation(Reservation reservation) {
      
   }

   @Override
   public void displayErrorMessage(String message) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public ViewType getViewType() {
      // TODO Auto-generated method stub
      return ViewType.RESERVATION;
   }
}
