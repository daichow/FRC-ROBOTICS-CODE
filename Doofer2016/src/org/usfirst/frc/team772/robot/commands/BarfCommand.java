package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BarfCommand extends Command {

    public BarfCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(4);
    	Robot.flywheelSubsystem.set(.6);
    	Robot.intakeSubsystem.topOut();
    	Timer.delay(1);
    	Robot.shooterSubsystem.shoot();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.flywheelSubsystem.set(0);
    	Robot.intakeSubsystem.move(0);
    	Robot.shooterSubsystem.shootFalse();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
