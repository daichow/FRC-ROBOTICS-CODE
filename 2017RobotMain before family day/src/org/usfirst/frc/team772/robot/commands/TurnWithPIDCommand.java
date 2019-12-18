package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnWithPIDCommand extends Command {
	
	double degrees;
	double rotation = 0;
	double intCrosstrackError = 0.0;
	double waitTime = 0.01;
	double diffCrosstrackError;
	double crosstrackError;
	
    public TurnWithPIDCommand(double degrees) {
    	this.degrees = degrees;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	Robot.drive.end();
    	Robot.driveSubsystem.gyro.reset();
    	crosstrackError = (degrees - Robot.driveSubsystem.gyroValue()); // get error
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	diffCrosstrackError = ((Robot.driveSubsystem.gyroValue() - degrees) - crosstrackError) / 0.02; // calc d
		crosstrackError = (Robot.driveSubsystem.gyroValue() - degrees); // calc p
//		intCrosstrackError += crosstrackError * 0.02; // calc i
		rotation = RobotMap.kP * crosstrackError;
		//+ RobotMap.kI * intCrosstrackError;
				//RobotMap.kD * diffCrosstrackError; 
				//- RobotMap.kD * diffCrosstrackError - RobotMap.kI * intCrosstrackError;
		if (rotation > 1) { // caps rotation
			rotation = 1;
		} else if (rotation < -1){
			rotation = -1;
		}else if((rotation <1 && rotation >0)){
			rotation = 1;
		}else if(rotation >-1 && rotation <0){
			rotation = -1;
		}

		Robot.driveSubsystem.auto(0, rotation);
		SmartDashboard.putString("DB/String 5", "spinning");
		setTimeout(5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Math.abs(crosstrackError) <= .5) { // room for error
			return true;
		} else {
			return isTimedOut();
		}
    }

    // Called once after isFinished returns true
    protected void end() {
    		Robot.driveSubsystem.auto(0, 0);
//        	Robot.drive.start();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
