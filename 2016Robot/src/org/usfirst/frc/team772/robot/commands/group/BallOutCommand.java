package org.usfirst.frc.team772.robot.commands.group;

import org.usfirst.frc.team772.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class BallOutCommand extends Command {

	// ball from shooter to intake
	// tipper must be up
	
    public BallOutCommand() {
    	setTimeout(2);
    }

    protected void initialize() {
    	Robot.flywheelSubsystem.flywheelSpit1();
    	Robot.flywheelSubsystem.flywheelSpit2();
    	Robot.intakeSubsystem.topOut();
    	Timer.delay(1);
    }

    protected void execute() {
    	Robot.shooterSubsystem.shoot();
    }

    protected boolean isFinished() {
    	if (Robot.ultrasonicSubsystem.isBallIn()){
    		return true;
    	} else {
    		return isTimedOut();
    	}
    }

    protected void end() {
    	Robot.flywheelSubsystem.move1(0);
    	Robot.intakeSubsystem.move(0);
    	Robot.shooterSubsystem.shootFalse();
    }

    protected void interrupted() {
    	end();
    }
}