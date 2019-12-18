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
//	double intCrosstrackError = 0.0;
//	double waitTime = 0.01;
	double diffCrosstrackError;
	double crosstrackError;
	
    public AutoDriveCommand(double degrees) {
    	this.degrees = degrees;
    }

    protected void initialize() {
    	Robot.driveSubsystem.encoderReset();
    	Robot.driveSubsystem.gyroReset();
    	crosstrackError = (degrees - Robot.driveSubsystem.encoderAverage()); // get error
    }

    protected void execute() {
    	double angle = Robot.driveSubsystem.gyroValue();
		crosstrackError = (Robot.driveSubsystem.encoderAverage() - degrees); // calc p
		distance = 0.2 * crosstrackError;
		
		if (distance > 1) { // caps rotation
			distance = 1;
		} else if (distance < -1){
			distance = -1;
		}else if((distance <0.75) && distance >0){
			distance = 0.75;
		}else if(distance >-0.75 && distance <0){
			distance = -0.75;
		}

		Robot.driveSubsystem.auto(distance, angle*Kp);
		Timer.delay(0.05);
    }

    protected boolean isFinished() {
    	if (Math.abs(crosstrackError) <= .5) { // room for error
			return true;
		} else {
			return false;
		}
    }

    protected void end() {
    	Robot.driveSubsystem.auto(0, 0);
    }

    protected void interrupted() {
    	end();
    }
}
