package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RotateCommand extends Command {

	double angle; // angle to turn to
	boolean isRelative; // if angle to turn to is relative or not
	
    public RotateCommand(double angle, boolean isRelative) {
    	this.angle = angle; // local angle set to parameter angle
    	this.isRelative = isRelative; // local isRelative set to parameter isRelative
    }

    protected void initialize() {
    	setTimeout(2000000); // doesn't run for more than 2 seconds
    	Robot.drive.cancel(); // stops drive
    	if (isRelative) { // sets setpoint
    		Robot.driveSubsystem.setSetpoint(angle + (SmartDashboard.getNumber("DB/Slider 0") - 2.5) / 2.5 * 180 - 180);
    	} else {
    		Robot.driveSubsystem.setSetpoint(angle);
    	}
    	Robot.driveSubsystem.enable(); // start pid controller
    }

    protected void execute() {
    }

    protected boolean isFinished() { // if -170 - 170 check extra condition
    	return Math.abs(((SmartDashboard.getNumber("DB/Slider 0") - 2.5) / 2.5 * 180) - Robot.driveSubsystem.getSetpoint()) < 10 || ((SmartDashboard.getNumber("DB/Slider 0") - 2.5) / 2.5 * 180 + Robot.driveSubsystem.getSetpoint()) < 10 || isTimedOut();
    }

    protected void end() {
    	Robot.driveSubsystem.disable(); // disables pid controller
    	Robot.driveSubsystem.move(0, 0); // stops motors
    	Robot.drive.start(); // start drive again
    }

    protected void interrupted() {
    	end(); // go to end
    }
}