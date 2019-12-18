package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveCommand extends Command {
	
	// Drive with joystick
	
	public DriveCommand() {
	    	
	}

	protected void initialize() {
		Robot.driveSubsystem.move(0, 0); // stops drive
	}

	protected void execute() {
		Robot.driveSubsystem.move(Robot.oi.getJoystick1(), 1); // moves robot using joystick and 1 speed
	}

	protected boolean isFinished() {
		return false; // doesn't stop on its own
	}

	protected void end() {
		Robot.driveSubsystem.move(0, 0); // stops drive
	}

	protected void interrupted() {
		end(); // goto end
	}
}