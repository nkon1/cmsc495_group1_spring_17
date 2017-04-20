package view;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import hotel.Reservation;

public class ReservationView extends JPanel implements View {
   private static final long serialVersionUID = 1944212264480093931L;
   
   private JScrollPane mainViewScrollPane;
   
   public ReservationView() {
      mainViewScrollPane = createReservationsView();
      this.add(mainViewScrollPane);
   }
   
   private JScrollPane createReservationsView() {
      JPanel panel = new JPanel();
      JScrollPane scrollPane = new JScrollPane(panel);
      panel.setBorder(BorderFactory.createTitledBorder("Reservations"));
      return scrollPane;
   }
   
   private void addReservationView(Reservation reservation) {
      JPanel panel = new JPanel();
      panel.setBorder(BorderFactory.createTitledBorder("Reservation"));
      
      JLabel roomLabel = new JLabel(String.format("Room: %s", reservation.getRoom().getRoomType()));
      JLabel dateLabel = new JLabel(String.format("Date: %s", reservation.getDate()));
      JLabel nightsLabel = new JLabel(String.format("Nights: %d", reservation.getNumOfNights()));
      JLabel guestsLabel = new JLabel(String.format("Guests %d:", reservation.getOccupant().getNumOfGuests())); 
      JLabel costLabel = new JLabel(String.format("Cost: %.2f", reservation.getCost()));
      
      panel.add(roomLabel);
      panel.add(dateLabel);
      panel.add(nightsLabel);
      panel.add(guestsLabel);
      panel.add(costLabel);
      
      mainViewScrollPane.add(panel);
   }

   public void addReservation(Reservation reservation) {
      addReservationView(reservation);
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
