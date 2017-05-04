package view;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

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
   private JLabel streetNameLabel = new JLabel("Street");
   private JLabel cityLabel = new JLabel("City");
   private JLabel stateLabel = new JLabel("State");
   private JLabel zipCodeLabel = new JLabel("zipcode");
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
   private JTextField zipCodeTextField = new JTextField(5);
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
      JPanel infoPanel = new JPanel(new GridBagLayout());
      GridBagConstraints c = new GridBagConstraints();
      c.gridx = 0;
      c.gridy = 0;
      c.fill = GridBagConstraints.BOTH;
      infoPanel.add(createUserPanel(), c);
      c.gridx = 1;
      c.gridy = 0;
      infoPanel.add(createPasswordPanel(), c);
      c.gridx = 0;
      c.gridy = 1;
      infoPanel.add(createAddressPanel(), c);
      c.gridx = 1;
      c.gridy = 1;
      infoPanel.add(createEmailPanel(), c);
      c.gridx = 0;
      c.gridy = 2;
      infoPanel.add(createCreditCardPanel(), c);
      
      mainPanel.setLayout(new GridBagLayout());
      c.gridx = 0;
      c.gridy = 0;
      mainPanel.add(infoPanel, c);
      c.gridx = 0;
      c.gridy = 1;
      mainPanel.add(createSubmissionPanel(), c);
      
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
      cityPanel.add(zipCodeLabel);
      cityPanel.add(zipCodeTextField);
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
   
   public String getFirstName(){
       return firstNameTextField.getText();
   }
   
   public String getLastName(){
       return lastNameTextField.getText();
   }
   
   public char[] getPassword(){
       if (Arrays.equals(passwordTextField.getPassword(), confirmPasswordTextField.getPassword())){
          return passwordTextField.getPassword();
       } else {
             displayErrorMessage("Password mismatch"); 
             return null;
       }
   }
   
   public String getEmail() {
       if (emailTextField.getText().equals(confirmEmailTextField.getText())){
           return emailTextField.getText();
       } else {
           displayErrorMessage("Email mismatch");
           return null;
       }
   }
   
   public String getStreet(){
       return streetNameTextField.getText();
   }
   
   public String getCity(){
       return cityTextField.getText();
   }
   
   public String getState(){
       return stateTextField.getText();
   }
   
   public int getZipCode(){
       return Integer.parseInt(zipCodeTextField.getText());
   }
   
   public String getCCnumber(){
       return ccNumberTextField.getText();
   }
   
   public Date getExpiration() throws ParseException{
       SimpleDateFormat exp = new SimpleDateFormat("MMyy");
       exp.setLenient(false);
       Date expiry = exp.parse(ccExpDateTextField.getText());
       return expiry;
   }
   
   public String getSecCode(){
       return ccSecCodeTextField.getText();
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
