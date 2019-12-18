package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ConveyerCommand extends Command {
boolean conveyor;
    public ConveyerCommand(boolean conveyor) {
    	this.conveyor = conveyor;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {	
    	if(conveyor&&Robot.ultrasonic.getRangeInches()>5){
    			Robot.conveyerSubsystem.move(1);
    	}else if(!conveyor &&Robot.ultrasonic.getRangeInches()<5){
    			Robot.conveyerSubsystem.move(-1);
    	}
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(conveyor && Robot.ultrasonic.getRangeInches()<5){
    			return true; 
    	}else if (!conveyor && Robot.ultrasonic.getRangeInches()>5){
    			return true;
    	}else{
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.conveyerSubsystem.move(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
