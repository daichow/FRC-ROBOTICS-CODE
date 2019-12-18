// Import the basic graphics classes.

//from http://beginwithjava.blogspot.ca/2008/07/most-basic-graphics-app.html

// and http://beginwithjava.blogspot.ca/2008/07/in-most-basic-graphics-app-we-had.html

import java.awt.*;
import javax.swing.*;

public class BasicPanel extends JPanel{

    // Create a constructor method
    public BasicPanel(){
        super();
    }

     public void paintComponent(Graphics g){
    //    g.drawLine(10,10,150,150); // Draw a line from (10,10) to (150,150)
    	 
    	 g.drawLine(50,20,150,20); // Draw a horizontal line from (50,20) to (150,20)
    	 g.drawLine(150,20,150,150); // Draw a vertical line from (150,20) to (150,150)
    	 g.drawLine(150,150,20,150); // Draw a horizontal line from (150,150) to (20,150)
    	 g.drawLine(20,150,50,20); // Draw a vertical line from (20,150)
    }

    public static void main(String arg[]){
        JFrame frame = new JFrame("BasicPanel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200,200);

        BasicPanel panel = new BasicPanel();
        frame.setContentPane(panel);          
        frame.setVisible(true);                   
    }
}
