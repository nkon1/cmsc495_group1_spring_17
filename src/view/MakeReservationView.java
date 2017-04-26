/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import hotel.Occupant;
import hotel.Reservation;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MakeReservationView extends JPanel implements View {
   private static final long serialVersionUID = 1944212264480093931L;
   
   //private JScrollPane mainViewScrollPane;
   
   public MakeReservationView(Occupant occupant) {
      makeReservationView(occupant);
     
   }
   
    private void makeReservationView(Occupant occupant) {
       JPanel panel = new JPanel(new GridLayout(3, 0));
       JPanel top = new JPanel();
       JPanel center = new JPanel();
       JPanel bottom = new JPanel();
      
       panel.add(top);
       panel.add(bottom);
       panel.setBorder(BorderFactory.createTitledBorder("Room Booking"));
       
       JLabel rooms = new JLabel("Rooms");
       JLabel dateLabel = new JLabel("Check in");
       JLabel stay = new JLabel("How many nights stay?");
       
       String[] roomTypes = new String[]{"Studio Room", "Suite Room", "Paradise Room"};
       JComboBox<String> room = new JComboBox<String>(roomTypes);
       JFormattedTextField dateField = new JFormattedTextField(new SimpleDateFormat("MM/dd/yyyy"));
       JTextField lengthOfStay = new JTextField("Length Of Stay");
       
       top.add(rooms);
       top.add(room);
       center.add(dateLabel);
       center.add(dateField);
       bottom.add(stay);
       bottom.add(lengthOfStay);
       
       panel.add(top);
       panel.add(center);
       panel.add(bottom);
   }


    @Override
    public void displayErrorMessage(String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ViewType getViewType() {
       return ViewType.RESERVATION;
    }
    
}
