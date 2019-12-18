package org.usfirst.frc.team772.robot.commands.group;

import org.usfirst.frc.team772.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveSpeedCommand extends Command {
	
	// Drive with speed
	
	double speed;
	
	public DriveSpeedCommand(double speed) {
		this.speed = speed; // sets local speed to paramter speed
	}

	protected void initialize() {
		Robot.driveSubsystem.move(speed, 0); // moves at speed
	}

	protected void execute() {
		Robot.driveSubsystem.move(speed, 0); // moves at speed
	}

	protected boolean isFinished() {
		return false; // doesn't stop on its own
	}

	protected void end() {
		Robot.driveSubsystem.move(0, 0); // stops drive
	}

	protected void interrupted() {
		end(); // go to end
	}
}