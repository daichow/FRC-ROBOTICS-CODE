package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveUltraSonicBackwardCommand extends Command {
	double Kp = 0.3;
	double speed;
	double stop;
	String orin;
	
    public AutoDriveUltraSonicBackwardCommand(double speed, double stop, String orin) {
    	this.speed = speed;
    	this.stop = stop;
    	this.orin = orin;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	Robot.drive.end();
    	Robot.driveSubsystem.encoderReset();
    	Robot.driveSubsystem.gyroReset();
    	Robot.driveSubsystem.auto(0, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double angle = Robot.driveSubsystem.gyroValue(); // get current heading
    	Robot.driveSubsystem.auto(-speed, angle*Kp); // drive towards heading 0
        Timer.delay(0.05);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(orin.equals("back")){
    		if(Robot.ultrasonicBack.getRangeInches() >= stop){
    			return true;
    		}else{
    			return false;
    		}
    	}else{
    		if(Robot.ultrasonicFront.getRangeInches() >= stop){
    			return true;
    		}else{
    			return false;
    		}
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
