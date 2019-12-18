package org.usfirst.frc.team772.robot.commands.visionGear;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

import org.echoClasses.Target;
import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class VisionGearFunctionCommand extends Command {
	String command;// possible commands: snapshot, gearmode, fuelmode, robootpi, shutdownpi

	String hostName = RobotMap.orangePi;
	int portNumber = RobotMap.orangePiPort;
    public VisionGearFunctionCommand(String command) {
        this.command = command;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
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
			if (RobotMap.connectedToGearPI && InetAddress.getByName("10.7.72.10").isReachable(5)) {
				try {
					System.out.println("opened Socket ...");
					Robot.outtGear = new ObjectOutputStream(
					Robot.echoSocketGear.getOutputStream());
					Robot.innGear = new ObjectInputStream(Robot.echoSocketGear.getInputStream());
					System.out.println("read a line of user input ...");
					Robot.outtGear.writeObject(command);
					String expectOK = (String) Robot.innGear.readObject();
					System.out.println("Recieved OK/other from Server for non target request :"
									+ expectOK + ":");
				} catch (Exception e) {
					System.out.println("Remote EchoServer on PI not reachable..continuing...");
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
