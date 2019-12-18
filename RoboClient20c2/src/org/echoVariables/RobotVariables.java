package org.echoVariables;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class RobotVariables {

	
	public static ObjectOutputStream outt;
	public static ObjectInputStream inn;
	public static Socket echoSocketBoiler;
	
	//Socket echoSocketBoiler = new Socket("10.7.72.10", 5803);


	public void openVisionSocket() throws IOException, IOException {
		
		System.out.println("opened Socket The first time...");
		
//		Socket echoSocketBoiler = null;
//		org.echoVariables.RobotVariables.outt = null;
//		org.echoVariables.RobotVariables.inn = null;
		
		//@SuppressWarnings("resource")
		
			echoSocketBoiler = new Socket("10.7.72.10", 5803);
		 
		org.echoVariables.RobotVariables.outt = new ObjectOutputStream(echoSocketBoiler.getOutputStream());					
		org.echoVariables.RobotVariables.inn = new ObjectInputStream(echoSocketBoiler.getInputStream());
		
	}
}
