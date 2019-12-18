package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.Robot.Direction;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class LiftCommand extends Command {

	Direction direction;
	boolean front;
	
    public LiftCommand(Direction direction, boolean front) {
    	this.direction = direction;
    	this.front = front;
    }

    protected void initialize() {
    	if (front) {
    		if (direction == Direction.Forward) {
    			Timer.delay(0.25);
	    		Robot.liftingSubsystem.frontUp();
	    	} else {
	    		Robot.liftingSubsystem.frontDown();
	    	}
    	} else {
    		if (direction == Direction.Forward) {
	    		Robot.liftingSubsystem.backUp();
	    	} else {
	    		Robot.liftingSubsystem.backDown();
	    	}
    	}
    	
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    	end();
    }
}