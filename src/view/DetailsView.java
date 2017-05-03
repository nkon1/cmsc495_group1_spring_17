package view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import room.Room;

public class DetailsView extends JPanel implements View {
   private static final long serialVersionUID = -739205794016753711L;
   private Room selectedRoom;
   private JButton nextButton = new JButton("Next");
   private JTextField numOfNightsTextField;
   private JLabel costLabel = new JLabel();
   private JFormattedTextField fromDateTextField;
   
   public DetailsView(Room selectedRoom) {
      this.selectedRoom = selectedRoom;
      this.setLayout(new GridLayout(5, 0));
      this.add(createRoomNameView());
      this.add(createDetailsView());
      this.add(createGuestsView());
      this.add(createCostView());
      this.add(createNextButtonView());
      this.setFocusable(true);
   }
   
   public JPanel createRoomNameView() {
      JPanel panel = new JPanel(new GridBagLayout());
      GridBagConstraints c = new GridBagConstraints();
      
      JLabel roomLabel = new JLabel();
      roomLabel.setFont(new Font(Font.MONOSPACED, Font.ROMAN_BASELINE, 20));
      roomLabel.setText(selectedRoom.getRoomType().name());
      c.gridx = 0;
      c.gridy = 0;
      c.gridwidth = 2;
      panel.add(roomLabel);
      
      return panel;
   }
   
   public JPanel createDetailsView() {
      JPanel panel = new JPanel(new GridBagLayout());
      GridBagConstraints c = new GridBagConstraints();
      
      c.gridx = 0;
      c.gridy = 0;
      JLabel fromLabel = new JLabel("Check-in (dd/MM/yyyy)");
      panel.add(fromLabel, c);
      
      c.gridx = 1;
      c.gridy = 0;
      JLabel toLabel = new JLabel("Nights");
      panel.add(toLabel, c);
      
      DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      
      c.gridx = 0;
      c.gridy = 1;
      fromDateTextField = new JFormattedTextField(sdf);
      fromDateTextField.setColumns(10);
      
      panel.add(fromDateTextField, c);
      
      c.gridx = 1;
      c.gridy = 1;
      numOfNightsTextField = new JTextField(5);
      numOfNightsTextField.getDocument().addDocumentListener(new DetailsViewNumNightsDocListener());
         
      panel.add(numOfNightsTextField, c);
      
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
      costLabel.setText(String.format("Cost: %.2f", selectedRoom.getCost()));
      panel.add(costLabel);
      
      return panel;
   }
   
   public JPanel createNextButtonView() {
      JPanel panel = new JPanel();
      panel.add(nextButton);
      
      return panel;
   }
   
   public boolean validDate(String dateAsString) {
      return Pattern.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}", dateAsString);
   }
   
   public void addNextButtonActionListener(ActionListener a) {
      nextButton.addActionListener(a);
   }
   
   @Override
   public void displayErrorMessage(String message) {
      // TODO Auto-generated method stub
      JOptionPane.showMessageDialog(this, message, "Invalid Content Error Message", JOptionPane.ERROR_MESSAGE);
   }
   
   private void updateCost() {
      if(numOfNightsTextField.getText().trim() == "")
         return;
      
      try {
         int cost = Integer.parseInt(numOfNightsTextField.getText().trim());
         costLabel.setText(String.format("Cost: %.2f", selectedRoom.getCost() * cost));
         this.repaint();
         this.revalidate();
      } catch(NumberFormatException e) {
         displayErrorMessage(String.format("%s is not a valid value", numOfNightsTextField.getText()));
      }
   }
   
   public String getDateTextField() {
      return fromDateTextField.getText();
   }

   @Override
   public ViewType getViewType() {
      // TODO Auto-generated method stub
      return ViewType.DETAILS;
   }
   
   private class DetailsViewNumNightsDocListener implements DocumentListener {

      @Override
      public void insertUpdate(DocumentEvent e) {
         // TODO Auto-generated method stub
         updateCost();
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
         // TODO Auto-generated method stub
         updateCost();
      }

      @Override
      public void changedUpdate(DocumentEvent e) {
         // TODO Auto-generated method stub
      }
       
   }
}
