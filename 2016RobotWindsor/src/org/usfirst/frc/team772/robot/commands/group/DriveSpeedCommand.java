package org.usfirst.frc.team772.robot.commands.group;

import org.usfirst.frc.team772.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveSpeedCommand extends Command {
	
	// Drive at speed and rotation until timeout
	
	double speed;
	double rotation;
	double timeout;
	
	public DriveSpeedCommand(double speed, double rotation, double timeout) {
		this.speed = speed;
		this.rotation = rotation;
		this.timeout = timeout;
	}

	protected void initialize() {
		setTimeout(timeout);
		Robot.driveSubsystem.move(speed, rotation);
	}

	protected void execute() {
		Robot.driveSubsystem.move(speed, rotation);
	}

	protected boolean isFinished() {
		return isTimedOut(); // doesn't stop on its own
	}

	protected void end() {
		Robot.driveSubsystem.move(0, 0);
	}

	protected void interrupted() {
		end(); // goto end
	}
}