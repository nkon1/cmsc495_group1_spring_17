package view;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainView extends JFrame implements View {
   private static final long serialVersionUID = -6749833934809731112L;
   private JPanel mainPanel;
   private JPanel currentView;

   public MainView(JPanel currentView) {
      this.currentView = currentView;
      currentView.setPreferredSize(new Dimension(800, 600));
      mainPanel = new JPanel();
      //mainPanel.setLayout(new GridLayout(3, 0));
      mainPanel.add(createTitleBar());
      mainPanel.add(currentView);
      this.add(mainPanel);
      this.setPreferredSize(new Dimension(800, 600));
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
      mainPanel.add(currentView);
      this.setPreferredSize(new Dimension(800, 600));
      this.pack();
      this.repaint();
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
