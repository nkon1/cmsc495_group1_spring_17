package view;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainView extends JFrame implements View {
   private static final long serialVersionUID = -6749833934809731112L;
   private JPanel mainPanel;
   private JPanel currentView;

   public MainView(JPanel currentView) {
      this.currentView = currentView;
      mainPanel = new JPanel();
      mainPanel.setLayout(new GridBagLayout());
      GridBagConstraints c = new GridBagConstraints();
      c.gridx = 0;
      c.gridy = 0;
      mainPanel.add(createTitleBar(), c);
      c.gridx = 0;
      c.gridy = 1;
      mainPanel.add(currentView, c);
      this.add(mainPanel);
      this.setVisible(true);
      this.pack();
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setLocationRelativeTo(null);
   }
   
   private JPanel createTitleBar() {
      JPanel panel = new JPanel();
      JLabel title = new JLabel("The Overlook Hotel");
      title.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
      panel.add(title);
      return panel;
   }
   
   public void setCurrentView(View view) {
      mainPanel.remove(this.currentView);
      this.currentView = (JPanel)view;
      GridBagConstraints c = new GridBagConstraints();
      c.gridx = 0;
      c.gridy = 1;
      mainPanel.add(currentView, c);
      this.pack();
      this.repaint();
      this.setLocationRelativeTo(null);
   }
   public View getCurrentView() { return (View)this.currentView; }

   @Override
   public void displayErrorMessage(String message) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public ViewType getViewType() {
      // TODO Auto-generated method stub
      return ViewType.MAIN;
   }
}
