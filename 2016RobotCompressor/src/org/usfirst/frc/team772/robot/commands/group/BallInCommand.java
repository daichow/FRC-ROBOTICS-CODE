package org.usfirst.frc.team772.robot.commands.group;

import org.usfirst.frc.team772.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class BallInCommand extends Command {

    public BallInCommand() {

    }

    protected void initialize() {
    	setTimeout(3);
//    	Robot.flywheelSubsystem.reverse(); // flywheel goes reverse
    	Robot.intakeSubsystem.topIn(); // intake goes in
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
    	if (Robot.ultrasonicSubsystem.isBallInShooter()) {
    		return true; // doesn't stop on its own
    	} else {
    		return isTimedOut();
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