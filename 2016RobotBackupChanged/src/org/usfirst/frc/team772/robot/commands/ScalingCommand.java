package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.Robot.Direction;

import edu.wpi.first.wpilibj.command.Command;

public class ScalingCommand extends Command {

	// Moves scaling arm in direction
	
	Direction direction; // direction of scaling arm motion
	
    public ScalingCommand(Direction direction) {
    	this.direction = direction; // local direction is set to parameter direction
    }

    protected void initialize() {
    	if (direction == Direction.Forward) { // scaling arm moves in direction
    		Robot.scalingSubsystem.scaleUp();
    	} else {
    		Robot.scalingSubsystem.scaleDown();
    	}
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return true; // finishes
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	end();
    }
}