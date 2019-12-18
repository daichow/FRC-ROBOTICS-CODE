package org.usfirst.frc.team772.robot.commands.visionBoiler;

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
public class VisionBoilerTargetCommand extends Command {
String hostName = "10.7.72.10";
int portNumber = 5803;
String command;
    public VisionBoilerTargetCommand(String command) {
    	this.command = command;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.haveCurrentTargetsBoiler = false;
		if (!RobotMap.connectedToBoilerPI) {
			try {
				if (InetAddress.getByName("10.7.72.10").isReachable(5)) {
					SmartDashboard.putString("DB/String 9","Connecting To BoilerPI");
					try {
						Robot.echoSocketBoiler = new Socket("10.7.72.10", 5803);
						Robot.outtBoiler = new ObjectOutputStream(Robot.echoSocketBoiler.getOutputStream());
						Robot.innBoiler = new ObjectInputStream(Robot.echoSocketBoiler.getInputStream());
						SmartDashboard.putString("DB/String 9","Connected To BoilerPI");
						RobotMap.connectedToBoilerPI = true;
					} catch (IOException e) {
						SmartDashboard.putString("DB/String 9",	"Not Connected To BoilerPI");
						RobotMap.connectedToBoilerPI = false;
					}
				}
			} catch (IOException e) {
			}
		}
		try {
			if (RobotMap.connectedToBoilerPI && (InetAddress.getByName("10.7.72.10").isReachable(5)) ) {
				try {
					Robot.outtBoiler.writeObject(command);
					SmartDashboard.putString("DB/String 5", "Sent Command");
					Robot.currentTargets = (ArrayList<Target>) Robot.innBoiler.readObject();
				} catch (Exception e) {
					SmartDashboard.putString("DB/String 5", "Failed to send");
				}

				System.out.println("Recieved Targets from Server are:");

				if (Robot.currentTargets.size() == 0) {
					System.out.println("No Valid Targets");
				} else {
					Robot.haveCurrentTargetsBoiler = true;
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
