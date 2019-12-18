package org.usfirst.frc.team772.robot.commands.vision;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import org.echoClasses.Target;
import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class VisionFunctionCommand extends Command {
	String command;// possible commands: snapshot, gearmode, fuelmode, robootpi, shutdownpi

	String hostName = RobotMap.orangePi;
	int portNumber = RobotMap.orangePiPort;
    public VisionFunctionCommand(String command) {
        this.command = command;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	try {
			ObjectOutputStream outt;
			ObjectInputStream inn;
			
				System.out.println("opened Socket ...");
				
				Socket echoSocket = new Socket(hostName, portNumber);
				outt = new ObjectOutputStream(echoSocket.getOutputStream());
				
				inn = new ObjectInputStream(echoSocket.getInputStream());

			System.out.println("read a line of user input ...");        
			outt.writeObject(command);
			  
			  String expectOK = (String) inn.readObject();
			  System.out.println("Recieved OK/other from Server for non target request :"+expectOK +":");
    	} catch (Exception e) {
			System.out.println("Remote EchoServer on PI not reachable..continuing...");
			
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
