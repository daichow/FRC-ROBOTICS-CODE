package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class EnvelopeCommand extends Command {

	boolean toggle;
	//This constructor ensures that a value will be given to toggle when an instance of this class 
	//is made which will allow the rest of the command to work
    public EnvelopeCommand(boolean toggle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.toggle = toggle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	//if the button1 is pressed, it will return true and the solenoid will extend, else, if button 2 is pressed, the solenoid will retract
    	if (toggle = true) {
    		Robot.envelopeSubsystem.envelopeOut();
    	}else if (toggle = false){
    		Robot.envelopeSubsystem.envelopeIn();
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
