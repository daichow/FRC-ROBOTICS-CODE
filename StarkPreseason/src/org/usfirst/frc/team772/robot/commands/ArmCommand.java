package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmCommand extends Command {
boolean arm = true;
String mode;
boolean toggle;
    public ArmCommand(String mode,boolean toggle) {
    	this.mode = mode;
    	this.toggle = toggle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(mode == "Auto"){
    		if (arm){
        		Robot.intakesubsystem.armIn();
        		Robot.intakesubsystem.armlightOn();
        		arm = false;
        		
        	} else {
        		Robot.intakesubsystem.armOut();
        		Robot.intakesubsystem.armlightOff();
        		arm = true;
        	}
    	}else {
    		if(toggle){
    			Robot.intakesubsystem.armIn();
    			Robot.intakesubsystem.armlightOn();
    		}else {
    			Robot.intakesubsystem.armOut();
    			Robot.intakesubsystem.armlightOff();
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
    	end();
    }
}
