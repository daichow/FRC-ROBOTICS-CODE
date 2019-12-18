package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.Robot.Direction;

import edu.wpi.first.wpilibj.command.Command;

public class TipperBrakeCommand extends Command {

	// Set brake on tipper
	
	Direction direction;
	
    public TipperBrakeCommand(Direction direction) {
    	this.direction = direction;
    }

    protected void initialize() {
    	if (direction == Direction.Forward) {
    		Robot.tipperSubsystem.brakeOn();
    	} else {
    		Robot.tipperSubsystem.brakeOff();
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