package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FlywheelCommand extends Command {
boolean flywheel; //declare variable for oi
boolean toggle = true; //declare variable to toggle flywheel on and off
    public FlywheelCommand(boolean flywheel) {
    	this.flywheel = flywheel;
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassi s);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(flywheel){
    		if(toggle ){
    			Robot.flywheelSubsystem.move(1);
    			toggle = false;
    		}else {
    			Robot.flywheelSubsystem.move(0);
    			toggle = true;
    		}
    	}else {
    		if(toggle ){
    			Robot.flywheelSubsystem.move(-1);
    			toggle = false;
    		}else {
    			Robot.flywheelSubsystem.move(0);
    			toggle = true;
    		}
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
    }
}
