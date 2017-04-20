package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import customer.Customer;

public class CustomerLoginView extends JPanel implements View {
   private static final long serialVersionUID = -5943835410263850923L;
   private Customer customer;
   private JButton makeRervationButton = new JButton("Make Reservation");
   private JButton viewReservationButton = new JButton("View Reservation");
   private JButton doneButton = new JButton("Done");
   
   public CustomerLoginView(Customer customer) {
      this.customer = customer;
      JPanel mainPanel = new JPanel(new GridBagLayout());
      GridBagConstraints c = new GridBagConstraints();
      c.gridx = 0;
      c.gridy = 0;
      c.gridwidth = 2;
      mainPanel.add(createCustomerWelcome(), c);
      c.gridwidth = 1;
      c.gridx = 0;
      c.gridy = 1;
      mainPanel.add(createMakeReservation(), c);
      c.gridx = 1;
      c.gridy = 1;
      mainPanel.add(createViewReservation(), c);
      this.add(mainPanel);
   }
   
   private JPanel createCustomerWelcome() {
      JPanel panel = new JPanel();
      JLabel welcomeLabel = new JLabel(String.format("Welcome %s!", customer.toString()));
      panel.add(welcomeLabel);
      return panel;
   }
   
   private JPanel createMakeReservation() {
      JPanel panel = new JPanel(new GridBagLayout());
      panel.add(makeRervationButton);
      return panel;
   }
   
   private JPanel createViewReservation() {
      JPanel panel = new JPanel(new GridBagLayout());
      panel.add(viewReservationButton);
      return panel;
   }
   
   public void addMakeReservationButtonListener(ActionListener a) {
      makeRervationButton.addActionListener(a);
   }

   public void addViewReservationButtonListener(ActionListener a) {
      viewReservationButton.addActionListener(a);
   }
   
   public void addDoneButtonActionListener(ActionListener a) {
      doneButton.addActionListener(a);
   }
   
   @Override
   public void displayErrorMessage(String message) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public ViewType getViewType() {
      // TODO Auto-generated method stub
      return ViewType.CUSTOMER_LOGIN;
   }

}
