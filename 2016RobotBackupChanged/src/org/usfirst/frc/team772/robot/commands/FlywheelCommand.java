package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.Robot.Direction;

import edu.wpi.first.wpilibj.command.Command;

public class FlywheelCommand extends Command {

	// Move flywheel motor
	
	Direction direction; // direction of flywheel

    public FlywheelCommand(Direction direction) {
    	this.direction = direction; // sets local direction to parameter direction
    }

    protected void initialize() {
    	switch (direction) { // move flywheel in direction
	    	case Forward: Robot.flywheelSubsystem.forward();
	    		break;
	    	case Reverse: Robot.flywheelSubsystem.reverse();
	    		break;
	    	case Stop: Robot.flywheelSubsystem.move(0);
	    		break;
    		default: Robot.flywheelSubsystem.move(0);
    	}
    }

    protected void execute() {
    
    }

    protected boolean isFinished() {
        return true; // no finish condition
    }

    protected void end() {

    }

    protected void interrupted() {
    	end(); // go to end
    }
}