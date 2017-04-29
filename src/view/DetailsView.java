package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import room.Room;

public class DetailsView extends JPanel implements View {
   private static final long serialVersionUID = -739205794016753711L;
   private Room selectedRoom;
   private JButton nextButton = new JButton("Next");
   
   public DetailsView(Room selectedRoom) {
      this.selectedRoom = selectedRoom;
      this.setLayout(new GridLayout(4, 0));
      this.add(createDetailsView());
      this.add(createGuestsView());
      this.add(createCostView());
      this.add(createNextButtonView());
   }
   
   public JPanel createDetailsView() {
      JPanel panel = new JPanel(new GridBagLayout());
      GridBagConstraints c = new GridBagConstraints();
      
      c.gridx = 0;
      c.gridy = 0;
      JLabel fromLabel = new JLabel("From");
      panel.add(fromLabel, c);
      
      c.gridx = 1;
      c.gridy = 0;
      JLabel toLabel = new JLabel("To");
      panel.add(toLabel, c);
      
      DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
      
      c.gridx = 0;
      c.gridy = 1;
      JFormattedTextField fromDateTextField = new JFormattedTextField(format);
      fromDateTextField.setColumns(10);
      panel.add(fromDateTextField, c);
      
      c.gridx = 1;
      c.gridy = 1;
      JFormattedTextField toDateTextField = new JFormattedTextField(format);
      toDateTextField.setColumns(10);
      panel.add(toDateTextField, c);
      
      return panel;
   }
   
   public JPanel createGuestsView() {
      JPanel panel = new JPanel(new GridBagLayout());
      GridBagConstraints c = new GridBagConstraints();
      
      c.gridx = 0;
      c.gridy = 0;
      JLabel guestsLabel = new JLabel("Guests");
      panel.add(guestsLabel, c);
      
      c.gridx = 0;
      c.gridy = 1;
      JTextField guests = new JTextField(3);
      panel.add(guests, c);
      
      return panel;
   }
   
   public JPanel createCostView() {
      JPanel panel = new JPanel(new GridBagLayout());
      JLabel costLabel = new JLabel(String.format("Cost: %.2f", selectedRoom.getCost()));
      panel.add(costLabel);
      
      return panel;
   }
   
   public JPanel createNextButtonView() {
      JPanel panel = new JPanel();
      panel.add(nextButton);
      
      return panel;
   }
   
   public void addNextButtonActionListener(ActionListener a) {
      nextButton.addActionListener(a);
   }
   
   @Override
   public void displayErrorMessage(String message) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public ViewType getViewType() {
      // TODO Auto-generated method stub
      return ViewType.DETAILS;
   }

}
