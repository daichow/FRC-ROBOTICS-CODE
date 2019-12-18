package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

//this command is what allows the robot to drive

public class DriveCommand extends Command {
    public DriveCommand() {
    }

    protected void initialize() {
    	Robot.driveSubsystem.auto(0, 0); //dont want the robot to take off at init (safety)
    }

    protected void execute() {
    	/*this is where the robot gets the command to move by constantly
    	  checking for joystick input*/
    	Robot.driveSubsystem.move(Robot.oi.getJoystick1(), RobotMap.driveSpeed); 
    }

    protected boolean isFinished() {
        return false; //never want a drive command to stop or else your robot stops
    }

    protected void end() {
    	Robot.driveSubsystem.auto(0, 0); //stops the drive motors
    }

    protected void interrupted() {
    	end(); //if something go wrong the commend is send to end method
    }
}
