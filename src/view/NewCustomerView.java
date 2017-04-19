package view;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class NewCustomerView extends JPanel implements View {
   private static final long serialVersionUID = 3518506359986385254L;
   private JPanel mainPanel = new JPanel();
   
   // User panel labels
   private JLabel firstNameLabel = new JLabel("First Name");
   private JLabel lastNameLabel = new JLabel("Last Name");
   private JLabel streetNameLabel = new JLabel("Address");
   private JLabel cityLabel = new JLabel("City");
   private JLabel stateLabel = new JLabel("State");
   private JLabel emailLabel = new JLabel("Email");
   private JLabel confirmEmailLabel = new JLabel("Confirm Email");
   private JLabel passwordLabel = new JLabel("Password");
   private JLabel confirmPasswordLabel = new JLabel("Confirm Password");
   private JLabel ccNumberLabel = new JLabel("Credit Card Number");
   private JLabel ccExpDateLabel = new JLabel("Expiration Date");
   private JLabel ccSecCodeLabel = new JLabel("Security Code");
   
   // User panel fields
   private JTextField firstNameTextField = new JTextField(20);
   private JTextField lastNameTextField = new JTextField(20);
   private JTextField streetNameTextField = new JTextField(20);
   private JTextField cityTextField = new JTextField(20);
   private JTextField stateTextField = new JTextField(20);
   private JTextField emailTextField = new JTextField(20);
   private JTextField confirmEmailTextField = new JTextField(20);
   private JPasswordField passwordTextField = new JPasswordField(20);
   private JPasswordField confirmPasswordTextField = new JPasswordField(20);
   private JTextField ccNumberTextField = new JTextField(16);
   private JTextField ccExpDateTextField = new JTextField(7);
   private JTextField ccSecCodeTextField = new JTextField(4);
   
   // Buttons
   private JButton submitButton = new JButton("Submit");
   private JButton cancelButton = new JButton("Cancel");
   
   public NewCustomerView() {
      JPanel infoPanel = new JPanel(new GridLayout(3, 2));
      infoPanel.add(createUserPanel());
      infoPanel.add(createPasswordPanel());
      infoPanel.add(createAddressPanel());
      infoPanel.add(createEmailPanel());
      infoPanel.add(createCreditCardPanel());
      
      mainPanel.setLayout(new GridLayout(2, 0));
      mainPanel.add(infoPanel);
      mainPanel.add(createSubmissionPanel());
      
      this.add(mainPanel);
   }
   
   private JPanel createUserPanel() {
      JPanel panel = new JPanel(new GridLayout(2, 0));
      
      JPanel top = new JPanel();
      top.add(firstNameLabel);
      top.add(firstNameTextField);
      
      JPanel bottom = new JPanel();
      bottom.add(lastNameLabel);
      bottom.add(lastNameTextField);
      
      panel.add(top);
      panel.add(bottom);
      panel.setBorder(BorderFactory.createTitledBorder("Customer Information"));
      return panel;
   }
   
   private JPanel createPasswordPanel() {
      JPanel panel = new JPanel(new GridLayout(2, 0));
      
      JPanel top = new JPanel();
      top.add(passwordLabel);
      top.add(passwordTextField);
      panel.add(top);
      
      JPanel bottom = new JPanel();
      bottom.add(confirmPasswordLabel);
      bottom.add(confirmPasswordTextField);
      panel.add(bottom);
      panel.setBorder(BorderFactory.createTitledBorder("Password"));
      return panel; 
   }
   
   private JPanel createEmailPanel() {
      JPanel panel = new JPanel(new GridLayout(2, 0));
      
      JPanel top = new JPanel();
      top.add(emailLabel);
      top.add(emailTextField);
      panel.add(top);
      
      JPanel bottom = new JPanel();
      bottom.add(confirmEmailLabel);
      bottom.add(confirmEmailTextField);
      panel.add(bottom);
      panel.setBorder(BorderFactory.createTitledBorder("Email"));
      return panel;
   }
   
   private JPanel createAddressPanel() {
      JPanel panel = new JPanel(new GridLayout(3, 0));
      
      JPanel stNamePanel = new JPanel();
      stNamePanel.add(streetNameLabel);
      stNamePanel.add(streetNameTextField);
      panel.add(stNamePanel);
      
      JPanel cityPanel = new JPanel();
      cityPanel.add(cityLabel);
      cityPanel.add(cityTextField);
      panel.add(cityPanel);
      
      JPanel statePanel = new JPanel();
      statePanel.add(stateLabel);
      statePanel.add(stateTextField);
      panel.add(statePanel);
      panel.setBorder(BorderFactory.createTitledBorder("Address"));
      return panel;
   }
   
   private JPanel createCreditCardPanel() {
      JPanel panel = new JPanel(new GridLayout(2, 0));
      
      JPanel top = new JPanel();
      top.add(ccNumberLabel);
      top.add(ccNumberTextField);
      panel.add(top);
      
      JPanel bottom = new JPanel();
      bottom.add(ccExpDateLabel);
      bottom.add(ccExpDateTextField);
      bottom.add(ccSecCodeLabel);
      bottom.add(ccSecCodeTextField);
      panel.add(bottom);
      panel.setBorder(BorderFactory.createTitledBorder("Credit Card"));
      return panel;
   }
   
   private JPanel createSubmissionPanel() {  
      JPanel panel = new JPanel();
      panel.add(cancelButton);
      panel.add(submitButton);
      return panel;
   }
   
   public void addCancelButtonListener(ActionListener a) {
      cancelButton.addActionListener(a);
   }
   
   public void addSubmitButtonListener(ActionListener a) {
      submitButton.addActionListener(a);
   }

   @Override
   public void displayErrorMessage(String message) {
      // TODO Auto-generated method stub
      JOptionPane.showMessageDialog(this, message, "New Customer Error Message", JOptionPane.ERROR_MESSAGE);
   }

   @Override
   public ViewType getViewType() {
      // TODO Auto-generated method stub
      return ViewType.NEW_CUSTOMER;
   }
}
