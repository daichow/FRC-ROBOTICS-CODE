package org.usfirst.frc.team772.robot.commands.visionGear;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
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
public class VisionGearTargetCommand extends Command {
String hostName = "10.7.72.9";
int portNumber = 5803;
String command;
    public VisionGearTargetCommand(String command) {
    	this.command = command;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.haveCurrentTargetsGear = false;
    	if (!RobotMap.connectedToGearPI) {
			try {
				if (InetAddress.getByName("10.7.72.9").isReachable(5)) {
					SmartDashboard.putString("DB/String 9","Connecting To GearPI");
					try {
						Robot.echoSocketGear = new Socket("10.7.72.9", 5803);
						Robot.outtGear = new ObjectOutputStream(Robot.echoSocketGear.getOutputStream());
						Robot.innGear = new ObjectInputStream(Robot.echoSocketGear.getInputStream());
						SmartDashboard.putString("DB/String 8","Connected To GearPI");
						RobotMap.connectedToGearPI = true;
					} catch (IOException e) {
						SmartDashboard.putString("DB/String 8","Not Connected To GearPI");
						RobotMap.connectedToGearPI = false;
					}
				}
			} catch (IOException e) {
			}
		}
    	try {
			if(RobotMap.connectedToGearPI && InetAddress.getByName("10.7.72.9").isReachable(5)){
				try{     	
					Robot.outtGear.writeObject(command);
					SmartDashboard.putString("DB/String 5", "Sent Command");
					Robot.currentTargets = (ArrayList<Target>) Robot.innGear.readObject();	
				}catch(Exception e){
					SmartDashboard.putString("DB/String 5", "Failed to send");
				}
				
				System.out.println("Recieved Targets from Server are:");
			
				if (Robot.currentTargets.size() == 0) {
					System.out.println("No Valid Targets");
				}else{
					Robot.haveCurrentTargetsGear = true;
				}
			}
		} catch (IOException e) {
		}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	 
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
