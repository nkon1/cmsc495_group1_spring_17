package view;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import hotel.Reservation;

public class ReservationView extends JPanel implements View {
   private static final long serialVersionUID = 1944212264480093931L;
   
   private JPanel mainViewScrollPane;
   
   public ReservationView() {
      mainViewScrollPane = createReservationsView();
      this.add(mainViewScrollPane);
   }
   
   private JPanel createReservationsView() {
      //JPanel panel = new JPanel();
      JPanel scrollPane = new JPanel();
      
      scrollPane.setBorder(BorderFactory.createTitledBorder("Reservations"));
      
      return scrollPane;
   }
   
   private void addReservationView(Reservation reservation) {
      
      mainViewScrollPane.setBorder(BorderFactory.createTitledBorder("Reservation"));
      mainViewScrollPane.setSize(500, 500);
      
      JLabel roomLabel = new JLabel(String.format("Room: %s", reservation.getRoom().getRoomType()));
      JLabel dateLabel = new JLabel(String.format("Date: %s", reservation.getDate()));
      JLabel nightsLabel = new JLabel(String.format("Nights: %d", reservation.getNumOfNights()));
      JLabel guestsLabel = new JLabel(String.format("Guests %d:", reservation.getOccupant().getNumOfGuests())); 
      JLabel costLabel = new JLabel(String.format("Cost: %.2f", reservation.getCost()));
      
      mainViewScrollPane.add(roomLabel);
      mainViewScrollPane.add(dateLabel);
      mainViewScrollPane.add(nightsLabel);
      mainViewScrollPane.add(guestsLabel);
      mainViewScrollPane.add(costLabel);
      JScrollPane scroller = new JScrollPane(mainViewScrollPane);
      this.add(scroller);
      //mainViewScrollPane.add(panel);
      //this.add(mainViewScrollPane);
      System.out.println("here");
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
