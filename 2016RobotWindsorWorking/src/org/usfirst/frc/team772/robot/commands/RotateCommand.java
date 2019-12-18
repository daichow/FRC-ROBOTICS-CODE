package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RotateCommand extends Command {

	// Turn robot to angle
	
	double angle; // angle to turn to
	
    public RotateCommand(double angle) {
    	this.angle = angle; // local angle set to parameter angle
    }

    protected void initialize() {
    	setTimeout(2); // doesn't run for more than 2 seconds
    	Robot.driveSubsystem.setSetpoint(angle);
    	Robot.driveSubsystem.enable(); // start pid controller
    }

    protected void execute() {
    }

    protected boolean isFinished() {
    	return Math.abs(Robot.getGoodAngle() - Robot.driveSubsystem.getSetpoint()) < 10 || isTimedOut(); // stops when at setpoint or timed out
    }

    protected void end() {
    	Robot.driveSubsystem.disable(); // disables pid controller
    	Robot.driveSubsystem.move(0, 0); // stops motors
    }

    protected void interrupted() {
    	end(); // go to end
    }
}