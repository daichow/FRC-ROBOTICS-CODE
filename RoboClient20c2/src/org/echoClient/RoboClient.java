package org.echoClient ;

// This program works in conjuction with a Server program on the Orange PI.
// The server currently accepts commands.
// -- "targets"  : returns current found targets in current camera frame
// 				, logs info and images to server
// -- "snapshot" : logs info and images to server - does not return targets.
// -- "gearmode" : this is the default startup mode of the server. Targets it is looking for
//					are the squares on each side of the gear peg. 
// -- "fuelmode" : Switches targets mode to looking for circles. To find guage on boiler as aim point.
// -- "shutdownpi" : This shutdown the OrangePI - use at end of match to safetly turn off server.
// -- "rebootpi" 	: Reboot and restart the pi . 


// This code runs in a forever loop. But on the RoboRio, 
// a single pass should be run , with a string equal to the command you wish to send to the server
// Basically, we need a robot command that takes in a string, one of the above commands, and expects 
// the results to be placed in the variables that this code demonstrates.

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import org.echoClasses.*;
//import org.echoVariables.RobotVariables.*;;

public class RoboClient  {
    public static void GetBoilerTargets() throws IOException, IOException   {
    	

        String userInput;
        
        BufferedReader stdIn =
    	        new BufferedReader(new InputStreamReader(System.in));
   
        System.out.println("opened Socket The first time...");
							
        while (true) {        
        	
        	userInput = stdIn.readLine();
        	
        	try {
	
               	System.out.println("trying to send command  ..." +userInput);
               	org.echoVariables.RobotVariables.outt.writeObject(userInput);
               			    
				System.out.println("sent command  ...");
            
            if (userInput.equals("targets") || userInput.equals("2goodtargets") ||
            		userInput.equals("3targets") || userInput.equals("4targets") )
            
            {    
            	System.out.println("Waiting for Targets");
    					ArrayList<Target> currentTargets = (ArrayList<Target>) org.echoVariables.RobotVariables.inn.readObject();	
    					System.out.println("Recieved Targets from Server are:");
    				    if (currentTargets.size() == 0) {
    				    	System.out.println("No Valid Targets");
    				    }
          //      print out stats on Targets created
    				
    				for (int x = 0; x < currentTargets.size(); x++) {		
    					System.out.println("Target # " + x);		
    					org.echoClasses.myAllTargets.printTargetInfo(currentTargets, x);  			
    					System.out.println("*************************");
    				}     

            }
            
            else {
    				  // not requesting targets, just want an OK from Server
    				  
    				  String expectOK = (String) org.echoVariables.RobotVariables.inn.readObject();
    				  System.out.println("Recieved OK/other from Server for non target request :"+expectOK +":");

            }	
            
        	} catch (Exception e) {
        		System.out.println("Server Not responding... Continuing");
				e.printStackTrace();
			}
            
            
            System.out.println("One MO time  ...");
	}
    }
}
 
	
        
				
				
        
   

	