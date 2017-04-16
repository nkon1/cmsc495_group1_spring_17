import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginView extends JPanel {
   private static final long serialVersionUID = 2090770939931306216L;
   private JPanel mainPanel = new JPanel();
   private JLabel userLabel = new JLabel("Username");
   private JLabel passwordLabel = new JLabel("Password");
   private JTextField userField = new JTextField(20);
   private JPasswordField passwordField = new JPasswordField(20);
   private JButton loginButton = new JButton("Log In");
   private JButton newCustomerButton = new JButton("New Customer");
   
   LoginView() {
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
   
   public String getPassword() {
      return String.valueOf(passwordField.getPassword());
   }
   
   public JButton getLoginButton() { return loginButton; }
   public JButton getNewCustomerButton() { return newCustomerButton; }
}
