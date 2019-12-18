package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.Robot.Mode;
import org.usfirst.frc.team772.robot.Robot.TurretPos;
import org.usfirst.frc.team772.robot.subsystems.TurretSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class TurretCommand extends Command {
	
	// Auto: set setpoint, move until within 50 of setpoint
	// Manual: move in direction while held
	// Variable: move with joystick while held
	
	TurretPos pos = null; // turret position
	Mode mode; // command mode
	double setpoint;
	
    public TurretCommand(TurretPos pos, Mode mode) { // Auto or Manual
    	this.pos = pos; // local pos is set to parameter pos
    	this.mode = mode; // local mode is set to parameter mode
    }
    
    public TurretCommand(Mode mode) { // Variable
    	this.mode = mode; // local mode is set to parameter mode
    }

    protected void initialize() {
    	if(mode == Mode.Auto){ // Auto
		    switch(pos){ // set setpoint
		    	case LEFT: Robot.turretSubsystem.setSetpoint(TurretSubsystem.LEFT);
		    		break;
		    	case CENTER: Robot.turretSubsystem.setSetpoint(TurretSubsystem.CENTER);
		    		break;
		    	case RIGHT: Robot.turretSubsystem.setSetpoint(TurretSubsystem.RIGHT);
		    		break;
		    	case LC: Robot.turretSubsystem.setSetpoint(TurretSubsystem.LC);
		    		break;
		    	case RC: Robot.turretSubsystem.setSetpoint(TurretSubsystem.RC);
		    		break;
		    	default: Robot.turretSubsystem.setSetpoint(TurretSubsystem.CENTER);
		    }
	    	Robot.turretSubsystem.enable(); // start pid controller
    	}else if(mode == Mode.Manual){ // Manual
    		Robot.turretSubsystem.disable();
    		Robot.visionTurretSubsystem.disable();
    		switch(pos){ // move in direction
	    		case LEFT: Robot.turretSubsystem.move(-0.2);
					break;
				case RIGHT: Robot.turretSubsystem.move(0.2);
					break;
				default:
					break;
    		}
    	}
    }

    protected void execute() {
    	if(mode == Mode.Variable){ // Variable
    		if (Math.abs(Robot.oi.getJoystick1().getRawAxis(4)) > 0.15) {
    			Robot.turretSubsystem.move(Robot.oi.getJoystick1().getRawAxis(4)); // move turret with joystick
    		} else if (Robot.oi.getJoystick1().getRawAxis(4) > 0.4) {
    			Robot.turretSubsystem.move(0.4); // move turret with joystick
    		} else if (Robot.oi.getJoystick1().getRawAxis(4) < -0.4) {
    			Robot.turretSubsystem.move(-0.4); // move turret with joystick
    		} else if (Math.abs(Robot.oi.getJoystick1().getRawAxis(4)) < 0.15) {
//    			Robot.turretSubsystem.move(0);
    		}
    	}
    }

    protected boolean isFinished() {
    	if(Math.abs(Robot.turretSubsystem.getEncPosition()) > 16018){ // if greater than 90 degrees
    		return true;
    	}
    	if(mode == Mode.Auto){ // Auto
    		return Math.abs(Robot.turretSubsystem.getSetpoint() - Robot.turretSubsystem.getEncPosition()) < 50; // stop when within 50 
    	}else{ // Manual or Variable
    		return false;
    	}
    }

    protected void end() {
    	if(mode == Mode.Auto){ // Auto
    		Robot.turretSubsystem.disable(); // stop pid controller
    	}else{ // Manual or Variable
    		Robot.turretSubsystem.move(0); // stop motor
    	}
    }

    protected void interrupted() {
    	end(); // goto end
    }
}