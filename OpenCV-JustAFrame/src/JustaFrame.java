import java.awt.*;
import javax.swing.*;
// from http://beginwithjava.blogspot.ca/2008/07/java-graphics-start-with-jframe.html
public class JustaFrame extends JFrame{

  public JustaFrame(){
    super();
  }

  public void paint(Graphics g){
    g.drawLine(10,10,150,150);
  }

  public static void main(String[] arg){
    JustaFrame frame = new JustaFrame();

    frame.setSize(200,200);
    frame.setVisible(true);
  }
}
