/*


*/

import javax.swing.*;
import java.awt.*;

public class GUI
{
   
   public GUI()
   {
      JFrame frame = new JFrame("main window");
      
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      frame.getContentPane().add(this.createTabs(), BorderLayout.CENTER);
      
      frame.setSize(500, 500);
      //frame.pack();
      
      frame.setVisible(true);
      frame.setResizable(false);
      
   }
   
   private JComponent createTabs()
   {
      JTabbedPane tabbedPane = new JTabbedPane();
      tabbedPane.setTabPlacement(JTabbedPane.TOP);
      
      JComponent panel1 = makePanel("Panel #1");
      tabbedPane.addTab("Tab 1", panel1);
      
      JComponent panel2 = makePanel("Panel #2");
      tabbedPane.addTab("Tab 2", panel2);
      
      JComponent panel3 = makePanel("Panel #3");
      tabbedPane.addTab("Tab 3", panel3);
      
      
      return tabbedPane;
   }
   
   private JComponent makePanel(String text)
   {
      JPanel panel = new JPanel(false);
      JLabel label = new JLabel(text);
      label.setHorizontalAlignment(JLabel.CENTER);
      panel.setLayout(new GridLayout(1, 1));
      panel.add(label);
      return panel;
   }
   
}