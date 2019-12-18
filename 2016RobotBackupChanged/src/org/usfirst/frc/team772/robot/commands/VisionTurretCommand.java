package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class VisionTurretCommand extends Command {

	// Turns turret to target
	
    public VisionTurretCommand() {
        
    }

    protected void initialize() {
    	setTimeout(3);
    	if (RobotMap.isTarget) {
	    	Robot.visionTurretSubsystem.setSetpoint(0); // set setpoint to 0
	    	Robot.visionTurretSubsystem.enable(); // start pid controller
    	} else {
    		end();
    	}
    	
    }

    protected void execute() {
    }

    protected boolean isFinished() {
    	if(Math.abs(Robot.turretSubsystem.getEncPosition()) > 16018){ // if greater than 90 degrees
    		return true;
    	}
    	if (Math.abs(Robot.visionTurretSubsystem.getSetpoint() - RobotMap.centerX) < 5) {
    		return true;
    	}
    	return isTimedOut(); // returns after timeout exceeded
    }

    protected void end() {
    	Robot.visionTurretSubsystem.disable(); // stop pid controller
    	Robot.turretSubsystem.move(0); // stops turret
    }

    protected void interrupted() {
    	end(); // goto end
    }
}