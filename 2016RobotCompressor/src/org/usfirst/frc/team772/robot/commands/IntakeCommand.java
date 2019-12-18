package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.Robot.Direction;
import org.usfirst.frc.team772.robot.Robot.Mode;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeCommand extends Command {
	
	// Auto: move intake in direction until ball in
	// Manual: move intake in direction while held
	// Variable: move intake with joystick while held
	
	Direction direction; // direction of intake
	Mode mode; // variable that determines intake mode
	
    public IntakeCommand(Direction direction, Mode mode) { // Auto or Manual
    	this.direction = direction; // sets local direction to parameter direction
    	this.mode = mode; // sets local direction to parameter manual
    }
    
    public IntakeCommand(Mode mode) { // Variable
    	this.mode = mode; // sets local direction to parameter manual
    }
 
    protected void initialize() {
    	if(mode != Mode.Variable){ // Auto or Manual
	    	if(direction == Direction.Forward) // move in direction
	    		Robot.intakeSubsystem.bottomIn();
	    	else
	    		Robot.intakeSubsystem.out();
    	}
    }

    protected void execute() {
    	if(mode == Mode.Variable){ // Variable
    		Robot.intakeSubsystem.move(Robot.oi.getJoystick2().getY()); // move with joystick
    	}
    }

    protected boolean isFinished() {
    	if (mode == Mode.Auto) { // Auto
	    	if (Robot.ultrasonicSubsystem.isBallIn()) { // returns when ball is in
	    		return true;
	    	} else {
	    		return false;
	    	}
    	} else { // Manual or Variable
    		return false;
    	}
    }

    protected void end() {
    	Robot.intakeSubsystem.move(0); // stops intake
    }

    protected void interrupted() {
    	end(); // go to end
    } 
} 