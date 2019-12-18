package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.Robot.Direction;
import org.usfirst.frc.team772.robot.Robot.Mode;

import edu.wpi.first.wpilibj.command.Command;

public class TipperCommand extends Command {
	
	// Auto: set direction, moves until setpoint
	// Manual: move in direction while held
	// Variable: move with joystick while held
	
	int setpoint = 9001; // tipper desired position
	boolean brake = false;
	Direction direction; // direction of tipper 
	Mode mode; // mode to move the tipper
    
    public TipperCommand(Direction direction, Mode mode){ // Manual
    	this.direction = direction;
    	this.mode = mode;
    }
    
    public TipperCommand(int setpoint, Mode mode, boolean brake){ // Setpoint
    	this.mode = mode;
    	this.setpoint = setpoint;
    	this.brake = brake;
    }
    
    public TipperCommand(Mode mode){ // Variable
    	this.mode = mode;
    }
    
    protected void initialize() {
    	Robot.tipperSubsystem.brakeOff(); // disengages brake
    	if (mode == Mode.Auto) { // Auto
	    	if (direction == Direction.Forward && setpoint == 9001) { // sets setpoint depending on position boolean
	    		setpoint = -350;
	    	} else if (direction == Direction.Reverse && setpoint == 9001) {
	    		setpoint = -1000;
	    	}
    		
	    	if (setpoint - Robot.tipperSubsystem.getEncPosition() > 0) { // sets direction
	    		direction = Direction.Forward;
	    	} else {
	    		direction = Direction.Reverse;
	    	}
    	}
    	if (mode != Mode.Variable) { // Manual or Auto
    		if (direction == Direction.Forward) {
    			Robot.turretSubsystem.setSetpoint(0);
    			Robot.turretSubsystem.enable();
	    		Robot.tipperSubsystem.up(); // moves tipper up if direction is true
    		} else {
	    		Robot.tipperSubsystem.down(); // moves tipper down if direction is false
    		}
    	}
    	
    }

    protected void execute() {
    	if (mode == Mode.Variable) { // Variable
    		Robot.tipperSubsystem.move(Robot.oi.getJoystick2().getY()); // move tipper with joystick
    	}
    }

    protected boolean isFinished() {
    	if (mode == Mode.Auto) { // Auto
	    	if (Robot.tipperSubsystem.getEncPosition() > setpoint && direction == Direction.Forward) { // returns if tipper passes setpoint
	    		return true;
	    	}
	    	
	    	if (Robot.tipperSubsystem.getEncPosition() < setpoint && direction == Direction.Reverse) { // returns if tipper passes setpoint
	    		return true;
	    	}
	    	return false; // continues moving if not at setpoint yet
    	} else { // Manual or Variable
    		return false; // doesn't finish on its own
    	}
    }

    protected void end() {
    	Robot.tipperSubsystem.move(0); // stops tipper
    	Robot.turretSubsystem.disable();
    	
    	if (direction == Direction.Forward || mode == Mode.Manual) { // if going up or manual
    		Robot.tipperSubsystem.brakeOn();  	
    	}
    }

    protected void interrupted() {
    	end(); // goto end
    }
}