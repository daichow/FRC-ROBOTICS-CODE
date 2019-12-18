package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShootCommand extends Command {

	// Shoot
	
    public ShootCommand() {

    }

    protected void initialize() {
    	setTimeout(0.5);
    	Robot.shooterSubsystem.shoot();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
    	Robot.shooterSubsystem.shootFalse();
    }

    protected void interrupted() {
    	end();
    }
}