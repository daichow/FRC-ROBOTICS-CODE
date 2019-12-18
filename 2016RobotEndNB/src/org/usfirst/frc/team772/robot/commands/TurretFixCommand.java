package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class TurretFixCommand extends Command {

	boolean slow = true;
	
    public TurretFixCommand(boolean slow) {
    	this.slow = slow;
    }

    protected void initialize() {

    }

    protected void execute() {
    	if (Math.abs(RobotMap.centerX - 10) > 5) {
    		if (slow) {
    			if (RobotMap.centerX - 10 > 0) {
		    		Robot.turretSubsystem.move(Robot.min((RobotMap.centerX - 10) * 0.008, 0.07));
		    	} else if (RobotMap.centerX - 10 < 0){
		    		Robot.turretSubsystem.move(Robot.max((RobotMap.centerX - 10) * 0.008, -0.07));
		    	}
    		} else {
    			if (RobotMap.centerX - 10 > 0) {
		    		Robot.turretSubsystem.move(Robot.min((RobotMap.centerX - 10) * 0.008, 0.12));
		    	} else if (RobotMap.centerX - 10 < 0){
		    		Robot.turretSubsystem.move(Robot.max((RobotMap.centerX - 10) * 0.008, -0.12));
		    	}
    		}
    	} else {
    		end();
    	}
    }

    protected boolean isFinished() {
    	if (Robot.cancel) {
    		return true;
    	} else {
    		if (slow) {
	    		return Math.abs(RobotMap.centerX - 10) < 5;
	    	} else {
	    		return Math.abs(RobotMap.centerX - 10) < 5;
	    	}
    	}
    	
    }

    protected void end() {
    	Robot.turretSubsystem.move(0);
    }

    protected void interrupted() {
    	end();
    }
}