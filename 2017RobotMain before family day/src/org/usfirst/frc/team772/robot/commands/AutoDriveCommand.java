package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveCommand extends Command {
	double Kp = 0.3;
	double degrees;
	double distance = 0;
	double intCrosstrackError = 0.0;
	double waitTime = 0.01;
	double diffCrosstrackError;
	double crosstrackError;
	
    public AutoDriveCommand(double degrees) {
    	this.degrees = degrees;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	Robot.drive.end();
    	Robot.driveSubsystem.encoderReset();
    	Robot.driveSubsystem.gyroReset();
    	crosstrackError = (degrees - Robot.driveSubsystem.encoderAverage()); // get error
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double angle = Robot.driveSubsystem.gyroValue();
//    	diffCrosstrackError = ((Robot.driveSubsystem.encoderAverage() - degrees) - crosstrackError) / 0.02; // calc d
		crosstrackError = (Robot.driveSubsystem.encoderAverage() - degrees); // calc p
//		intCrosstrackError += crosstrackError * 0.02; // calc i
		distance = 0.2 * crosstrackError;
		if (distance > 1) { // caps rotation
			distance = 1;
		} else if (distance < -1){
			distance = -1;
		}else if((distance <1) && distance >0){
			distance = 1;
		}else if(distance >-1 && distance <0){
			distance = -1;
		}

		Robot.driveSubsystem.auto(distance, angle*Kp);
		Timer.delay(0.05);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Math.abs(crosstrackError) <= .33) { // room for error
			return true;
		} else {
			return false;
		}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSubsystem.auto(0, 0);
//    	Robot.drive.start();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
