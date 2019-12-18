package org.usfirst.frc.team772.robot.commands.group;

import org.usfirst.frc.team772.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveSpeedCommand extends Command {
	
	// Drive with joystick
	
	double speed;
	double rotation;
	
	public DriveSpeedCommand(double speed, double rotation) {
		this.speed = speed;
		this.rotation = rotation;
	}

	protected void initialize() {
//		Robot.drive.cancel();
		Robot.driveSubsystem.move(speed, rotation);
	}

	protected void execute() {
		Robot.driveSubsystem.move(speed, rotation);
	}

	protected boolean isFinished() {
		return false; // doesn't stop on its own
	}

	protected void end() {
		Robot.driveSubsystem.move(0, 0);
//		Robot.drive.start();
	}

	protected void interrupted() {
		end(); // goto end
	}
}