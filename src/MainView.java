import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainView extends JFrame {
   private static final long serialVersionUID = -6749833934809731112L;

   public MainView(JPanel view) {
      this.add(view);
      this.setPreferredSize(new Dimension(800, 600));
      this.setVisible(true);
      this.pack();
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setLocationRelativeTo(null);
   }
}
