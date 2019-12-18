package org.usfirst.frc.team772.robot.commands.group;

import org.usfirst.frc.team772.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class BallInTeleCommand extends Command {

	// ball in from intake to shooter
	
    public BallInTeleCommand() {

    }

    protected void initialize() {
    	setTimeout(2); // sets timeout to 1 second
    	Robot.flywheelSubsystem.reverse(); // flywheel goes reverse
    	Robot.intakeSubsystem.topIn(); // intake goes in
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
    	if (Robot.intakeSubsystem.isBallInShooter()) {
    		return true; // doesn't stop on its own
    	} else {
    		return isTimedOut(); // returns when timed out
    	}
    }

    protected void end() {
    	Robot.flywheelSubsystem.move(0); // stops flywheel
    	Robot.intakeSubsystem.move(0); // stops intake
    }

    protected void interrupted() {
    	end(); // go to end
    }
}