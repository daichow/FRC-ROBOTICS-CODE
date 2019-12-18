package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

//this command is for when the conveyor is need to shoot the ball

public class ConveyorShootCommand extends Command {
    public ConveyorShootCommand() {
    }

    protected void initialize() {
    	/*
    	 * check ultrasonic if ball is in chamber
    	 * if ball is not present command will end
    	 * if ball is in then the conveyor w:)ill turn on
    	 */
    	if (Robot.ultrasonic.getRangeInches() > 6) //if greater than 10 inches then ball not present
    		end();
    	else if (Robot.ultrasonic.getRangeInches() < 5 ) //ball present if less than 4 inches
    		Robot.conveyorSubsystem.conveyor(1);
    }	

    protected void execute() {
    }

    protected boolean isFinished() {
    	// only finishes the command if the ball fully leaves the chamber
        if (Robot.ultrasonic.getRangeInches() > 5)
        	return true; 
        else
        	return false;
    }

    protected void end() {
    	//stops the conveyor 
    	Robot.conveyorSubsystem.conveyor(0);
    }

    protected void interrupted() {
    	end(); //sends the command to end if interrupted by something
    }
}
