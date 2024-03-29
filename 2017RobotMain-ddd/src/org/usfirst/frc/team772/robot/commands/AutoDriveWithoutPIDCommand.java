package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveWithoutPIDCommand extends Command {
	double Kp = 0.3;
	double degrees;
	double speed;
	double crosstrackError;

    public AutoDriveWithoutPIDCommand(double speed, double degrees) {
    	this.speed = speed;
    	this.degrees = degrees;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSubsystem.encoderReset();
    	Robot.driveSubsystem.gyroReset();
    	crosstrackError = (degrees - Robot.driveSubsystem.encoderAverage()); // get error
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double angle = Robot.driveSubsystem.gyroValue();
		crosstrackError = (Robot.driveSubsystem.encoderAverage() - degrees); // calc p
		
		Robot.driveSubsystem.auto(speed, angle*Kp);
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
