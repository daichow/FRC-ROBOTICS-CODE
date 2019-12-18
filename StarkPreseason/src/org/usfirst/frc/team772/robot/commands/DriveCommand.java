package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveCommand extends Command {
String mode;
double speed;
    public DriveCommand(String mode,double speed) {
    this.mode = mode;
    this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(mode == "Manual"){
    		Robot.driveSubsystem.move(Robot.oi.getJoystick1(), 0);
    	}else { // Auto Mode
    		Robot.driveSubsystem.automove(speed,0);
    	}
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(mode == "Manual"){
    		Robot.driveSubsystem.move(Robot.oi.getJoystick1(), 1);
    	}else {
    		Robot.driveSubsystem.automove(speed,0);
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	if(mode == "Manual"){
    		Robot.driveSubsystem.move(Robot.oi.getJoystick1(), 0);
    	}else{
    		Robot.driveSubsystem.automove(0, 0);
    	}
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end (); 
    }
}
