package org.usfirst.frc.team772.robot.commands.vision;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

import org.echoClasses.Target;
import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class VisionTargetCommand extends Command {
String hostName = RobotMap.orangePi;
int portNumber = RobotMap.orangePiPort;
String command;
    public VisionTargetCommand(String command) {
    	this.command = command;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.haveCurrentTargets = false;
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
 
				Robot.currentTargets = (ArrayList<Target>) inn.readObject();		
				System.out.println("Recieved Targets from Server are:");
			    if (Robot.currentTargets.size() == 0) {
			    	System.out.println("No Valid Targets");
			    }else{
			    	Robot.haveCurrentTargets = true;
			    }
  //      print out stats on Targets created
			
			for (int x = 0; x < Robot.currentTargets.size(); x++) {		
				System.out.println("Target # " + x);		
				org.echoClasses.myAllTargets.printTargetInfo(Robot.currentTargets, x);  			
				System.out.println("*************************");
			}     
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
