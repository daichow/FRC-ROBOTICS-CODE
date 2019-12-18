package org.usfirst.frc.team772.robot.commands.group;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class ShooterCommand extends Command {
	
	// Flywheel then shoot
	
    public ShooterCommand(){
    	
    }
    	
    protected void initialize() {
    	if(Robot.intakeSubsystem.isBallInShooter()/* && RobotMap.distance >= 6 && RobotMap.distance <= 11.0*/) { // start if ball in and in range
    		setTimeout(4); // sets timeout to 4 seconds
    		Robot.flywheelSubsystem.forward(); // turns on flywheel
    	} else {
    		end();    	
    	}
    }

    protected void execute() {
    	if (Robot.intakeSubsystem.isBallInShooter()/* && RobotMap.distance >= 6 && RobotMap.distance <= 11.0*/ && Robot.flywheelSubsystem.isRateRight()) {
    		Robot.shooterSubsystem.shoot(); // shoots if ball in, in range, and flywheel fast enough
    	}
    }

    protected boolean isFinished() {
    	if (Robot.intakeSubsystem.isBallInShooter()) {
    		return isTimedOut(); // returns if timed out
    	} else {
    		return true; // returns when ball out
    	}
    }

    protected void end() {
    	Robot.shooterSubsystem.shootFalse(); // stop shoot
    	Timer.delay(0.5); // flywheel off after 0.5 seconds
    	Robot.flywheelSubsystem.move(0); // stops flywheel
    }

    protected void interrupted() {
    	end(); // go to end
    }
}