package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShootCommand extends Command {

    public ShootCommand() {

    }

    protected void initialize() {
    	Robot.shooterSubsystem.shoot(); // shoot
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.shooterSubsystem.shootFalse(); // stop shooting
    }

    protected void interrupted() {
    	end();
    }
}