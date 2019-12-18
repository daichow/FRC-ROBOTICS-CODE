package org.usfirst.frc.team772.robot.commands.group;

import org.usfirst.frc.team772.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class BallOutCommand extends Command {

	// ball from shooter to intake
	// tipper must be up
	
    public BallOutCommand() {

    }

    protected void initialize() {
    	Robot.flywheelSubsystem.flywheelSpit(); // flywheel out
    	Robot.intakeSubsystem.topOut(); // intake out
    	Timer.delay(1); // delay 1 second
    }

    protected void execute() {
    	Robot.shooterSubsystem.shoot(); // shoot
    }

    protected boolean isFinished() {
    	if (Robot.intakeSubsystem.isBallIn()){ // returns when ball is in
    		return true;
    	} else {
    		return false;
    	}
    }

    protected void end() {
    	Robot.flywheelSubsystem.move(0); // flywheel stop
    	Robot.intakeSubsystem.move(0); // intake stop
    	Robot.shooterSubsystem.shootFalse(); // stop shoot
    }

    protected void interrupted() {
    	end(); // go to end
    }
}