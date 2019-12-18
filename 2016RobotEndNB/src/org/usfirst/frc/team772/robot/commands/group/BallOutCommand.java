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
    	Robot.flywheelSubsystem.flywheelSpit();
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
    		return false;
    	}
    }

    protected void end() {
    	Robot.flywheelSubsystem.move(0);
    	Robot.intakeSubsystem.move(0);
    	Robot.shooterSubsystem.shootFalse();
    }

    protected void interrupted() {
    	end();
    }
}