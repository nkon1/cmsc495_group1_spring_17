package view;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import customer.Customer;

public class CustomerLoginView extends JPanel implements View {
   private static final long serialVersionUID = -5943835410263850923L;
   private Customer customer;
   private JButton doneButton = new JButton("Done");
   
   public CustomerLoginView(Customer customer) {
      this.customer = customer;
      JPanel mainPanel = new JPanel();
      mainPanel.add(createCustomerWelcome());
      this.add(mainPanel);
   }
   
   private JPanel createCustomerWelcome() {
      JPanel panel = new JPanel();
      JLabel welcomeLabel = new JLabel(String.format("Welcome %s!", customer.toString()));
      panel.add(welcomeLabel);
      return panel;
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
