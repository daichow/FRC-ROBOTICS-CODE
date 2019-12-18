import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import org.echoClient.*;

public class theMain {

	
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
System.out.println("Main Command");
		
String hostName = "10.7.72.10";
int portNumber = 5803;

//		Socket echoSocketBoiler = null;
//		org.echoVariables.RobotVariables.outt = null;
//		org.echoVariables.RobotVariables.inn = null;
		
		//@SuppressWarnings("resource")
		 
	org.echoVariables.RobotVariables.echoSocketBoiler = new Socket(hostName, portNumber);
		
		 
		org.echoVariables.RobotVariables.outt = new ObjectOutputStream(org.echoVariables.RobotVariables.echoSocketBoiler.getOutputStream());					
		org.echoVariables.RobotVariables.inn = new ObjectInputStream(org.echoVariables.RobotVariables.echoSocketBoiler.getInputStream());
		
		org.echoClient.RoboClient.GetBoilerTargets(); 		
	}
}
