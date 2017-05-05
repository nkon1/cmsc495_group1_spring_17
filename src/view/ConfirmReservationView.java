package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import hotel.Reservation;

public class ConfirmReservationView extends JPanel implements View {
   private static final long serialVersionUID = -787986418456221712L;
   private Reservation reservation;
   private JButton confirmButton = new JButton("Confirm");
   
   public ConfirmReservationView(Reservation reservation) {
      this.reservation = reservation;
      this.add(createConfirmation());
   }
   
   public JPanel createConfirmation() {
      JPanel mainPanel = new JPanel(new GridBagLayout());
      GridBagConstraints c = new GridBagConstraints();
      
      JPanel detailsPanel = new JPanel(new GridLayout(6, 0));
      JLabel roomLabel = new JLabel(String.format("Room: %s", reservation.getRoom().getRoomType()));
      JLabel dateLabel = new JLabel(String.format("Date: %s", reservation.getDate()));
      JLabel nightsLabel = new JLabel(String.format("Nights: %d", reservation.getNumOfNights()));
      JLabel guestsLabel = new JLabel(String.format("Guests %d:", reservation.getOccupant().getNumOfGuests())); 
      JLabel costLabel = new JLabel(String.format("Cost: %.2f", reservation.getCost()));
      
      detailsPanel.add(roomLabel);
      detailsPanel.add(dateLabel);
      detailsPanel.add(nightsLabel);
      detailsPanel.add(guestsLabel);
      detailsPanel.add(costLabel);
      
      JPanel confirmPanel = new JPanel();
      confirmPanel.add(confirmButton);
      
      c.gridx = 0;
      c.gridy = 0;
      mainPanel.add(detailsPanel, c);
      
      c.gridy = 1;
      mainPanel.add(detailsPanel, c);
      
      return mainPanel;
   }
   
   public void addConfirmButtonActionListener(ActionListener a) {
      confirmButton.addActionListener(a);
   }

   @Override
   public void displayErrorMessage(String message) {
      // TODO Auto-generated method stub
      JOptionPane.showMessageDialog(this, message, "Confirmation Error Message", JOptionPane.ERROR_MESSAGE);
   }
   
   public Reservation getReservation() {
      return reservation;
   }

   @Override
   public ViewType getViewType() {
      // TODO Auto-generated method stub
      return ViewType.CONFIRMATION;
   }

}
