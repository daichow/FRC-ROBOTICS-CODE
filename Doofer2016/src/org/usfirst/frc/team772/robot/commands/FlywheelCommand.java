package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

// this command is used to control the flywheel

public class FlywheelCommand extends Command {
	boolean mode = true; //used to allow the command to be toggled
    public FlywheelCommand(int spin) {
    }

    protected void initialize() {
    	/*
    	 * check for variable mode declared before to identify which state the hood
    	 * and flyweel is at
    	 * if mode is true that means the flywheel is off and hood is down therefore the flywheel must be turned on the hood up and the variable mode is set to false 
    	 * if mode is not true (false) the flywheel and hood is in shooting position and must be retracted and turned off
    	 */
    	if (mode){
    		Robot.flywheelSubsystem.shoot();
    		mode = false;
    	}else{
    		Robot.flywheelSubsystem.set(0);
    		mode = true;
    	}
    		
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;// the command only needs to run once per activation or else it will be continusly toggled on and off
    }

    protected void end() {
    }


    protected void interrupted() {
    	end();
    }
}
