package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ManualTurretCommand extends Command {

	boolean direction;
	
    public ManualTurretCommand(boolean direction) {
    	this.direction = direction;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(direction)
    		Robot.turretSubsystem.move(0.3);
    	else
    		Robot.turretSubsystem.move(-0.3);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.turretSubsystem.move(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
