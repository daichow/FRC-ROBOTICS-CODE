package org.usfirst.frc.team772.robot.commands;

import java.text.ParseException;

import org.usfirst.frc.team772.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveLidarCommand extends Command {
	double Kp = 0.3;
	double stop;
	double crosstrackError = 0;
	double distance = 0;
    public AutoDriveLidarCommand(double stop) {
    	this.stop = stop;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.lidarSubsystem.testLidar(0);
    	
    	crosstrackError = (stop - Robot.lidarSubsystem.lidarDistance() );
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
crosstrackError = (Robot.lidarSubsystem.lidarDistance() - stop); 
		distance = 0.2 * crosstrackError;
		
		if (distance > .5) { // caps rotation
			distance = .5;
		} else if (distance < -.5){
			distance = -.5;
		}else if((distance <0.3) && distance >0){
			distance = 0.3;
		}else if(distance >-0.3 && distance <0){
			distance = -0.3;
		}

		Robot.lidarSubsystem.testLidar(distance);
		Timer.delay(0.05);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Math.abs(crosstrackError) <= .5) { // room for error
			return true;
		} else {
			return false;
		}
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
