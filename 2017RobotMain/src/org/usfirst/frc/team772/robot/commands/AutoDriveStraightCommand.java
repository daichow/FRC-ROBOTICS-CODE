package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveStraightCommand extends Command {
	double Kp = 0.3;
    public AutoDriveStraightCommand() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSubsystem.gyroReset();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double angle = Robot.driveSubsystem.gyroValue(); // get current heading
    	Robot.driveSubsystem.auto(-.6, angle*Kp); // drive towards heading 0
        Timer.delay(0.05);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
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
