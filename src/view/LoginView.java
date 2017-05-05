package view;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginView extends JPanel implements View {
   private static final long serialVersionUID = 2090770939931306216L;
   private JPanel mainPanel = new JPanel();
   private JLabel userLabel = new JLabel("Username");
   private JLabel passwordLabel = new JLabel("Password");
   private JTextField userField = new JTextField(20);
   private JPasswordField passwordField = new JPasswordField(20);
   private JButton loginButton = new JButton("Log In");
   private JButton newCustomerButton = new JButton("New Customer");
   
   public LoginView() {
      mainPanel.setLayout(new GridLayout(2, 0));
      
      JPanel loginPanel = new JPanel();
      loginPanel.add(createUserPanel());
      loginPanel.add(createPasswordPanel());
      mainPanel.add(loginPanel);
      
      JPanel buttonPanel = new JPanel();
      buttonPanel.add(newCustomerButton);
      buttonPanel.add(loginButton);
      mainPanel.add(buttonPanel);
      
      this.add(mainPanel);
   }
   
   public void setUserLabel(String text) {
      userLabel.setText(text);
   }
   
   private JPanel createUserPanel() {
      JPanel panel = new JPanel();
      panel.add(userLabel);
      panel.add(userField);
      return panel;
   }
   
   private JPanel createPasswordPanel() {
      JPanel panel = new JPanel();
      panel.add(passwordLabel);
      panel.add(passwordField);
      return panel;
   }
   
   public String getUsername() {
      return userField.getText();
   }
   
   public char[] getPassword() {
      return passwordField.getPassword();
   }
   
   public void addLoginButtonListener(ActionListener a) {
      loginButton.addActionListener(a);
   }
   
   public void addLoginNewCustomerButtonListener(ActionListener a) {
      newCustomerButton.addActionListener(a);
   }

   @Override
   public void displayErrorMessage(String message) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public ViewType getViewType() {
      // TODO Auto-generated method stub
      return ViewType.LOGIN;
   }
   
   public JButton getLoginButton() {
      return loginButton;
   }
   
   public JButton getNewCustomerButton() {
      return newCustomerButton;
   }
}
