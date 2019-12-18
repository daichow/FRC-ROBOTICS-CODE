package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.Robot.Mode;

import edu.wpi.first.wpilibj.command.Command;

public class TurretCommand extends Command {
	
	// Auto: set setpoint, move until within 50 of setpoint
	// Manual: move in direction while held
	// Variable: move with joystick while held
	
	Mode mode; // command mode
	double setpoint;
	
    public TurretCommand(double setpoint, Mode mode) { // Auto or Manual
    	this.setpoint = setpoint; // local pos is set to parameter pos
    	this.mode = mode; // local mode is set to parameter mode
    }
    
    public TurretCommand(Mode mode) { // Variable
    	this.mode = mode; // local mode is set to parameter mode
    }

    protected void initialize() {
    	if(mode == Mode.Auto){ // Auto
    		Robot.turretSubsystem.setSetpoint(setpoint);
	    	Robot.turretSubsystem.enable(); // start pid controller
    	}else if(mode == Mode.Manual){ // Manual
    		Robot.turretSubsystem.disable();
    		Robot.visionTurretSubsystem.disable();
    		switch((int)setpoint){ // move in direction
	    		case -15989: Robot.turretSubsystem.move(-0.2);
					break;
				case 16018: Robot.turretSubsystem.move(0.2);
					break;
				default:
					break;
    		}
    	}
    }

    protected void execute() {

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