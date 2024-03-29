package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoCommand extends Command {
    public AutoCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

	// Called just before this Command runs the first time
    protected void initialize() {
    	Robot.tipperSubsystem.breakOn();
    	Robot.turretSubsystem.encoderReset();
    	Robot.tipperSubsystem.resetEncoder();
    	setTimeout(5);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveSubsystem.auto(0.7, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSubsystem.auto(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
