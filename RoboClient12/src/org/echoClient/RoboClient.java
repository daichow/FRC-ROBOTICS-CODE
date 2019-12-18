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

public class RoboClient  {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        
        if (args.length != 2) {
            System.err.println(
                "Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);
        
        String userInput;
        
        BufferedReader stdIn =
    	        new BufferedReader(
    	            new InputStreamReader(System.in));
    
        while (true) {
        /*
         * The following statements in the try-with-resources statement 
         * in the EchoClient example are critical. 
         * These lines establish the socket connection between the client 
         * and the server and open a 
         * PrintWriter and a BufferedReader on the socket:
         * 
         * The second statement in the try-with resources statement 
         * gets the socket's output stream and opens a ObjectOutputStream on it. 
         * 
         * Similarly, the third statement gets the socket's input stream 
         * and opens a BufferReader on it. 
         * The example uses readers and writers so 
         * that it can write Unicode characters over the socket.
         */
     
        	
         
            userInput = stdIn.readLine();
            
            try {
				ObjectOutputStream outt;
				ObjectInputStream inn;
				
					System.out.println("opened Socket ...");
					
					Socket echoSocket = new Socket(hostName, portNumber);
					outt = new ObjectOutputStream(echoSocket.getOutputStream());
					
					inn = new ObjectInputStream(echoSocket.getInputStream());

				System.out.println("read a line of user input ...");        
				outt.writeObject(userInput);
				    
        if (userInput.equals("targets")) { 
     
					ArrayList<Target> currentTargets = (ArrayList<Target>) inn.readObject();		
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
				
//     		outt.close();
//     		inn.close();
//     		echoSocket.close();
				
        }
        
        else {
				  // not requesting targets, just want an OK from Server
				  
				  String expectOK = (String) inn.readObject();
				  System.out.println("Recieved OK/other from Server for non target request :"+expectOK +":");
				  
//        	  outt.close();
//        	  inn.close();
//        	  echoSocket.close();
        
        }
			} catch (Exception e) {
				System.out.println("Remote EchoServer on PI not reachable..continuing...");
				
			}
      
			 
    
		} 
    }

    }

    

	