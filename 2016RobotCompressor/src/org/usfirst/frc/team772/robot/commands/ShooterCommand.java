package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class ShooterCommand extends Command {
	
	// Shoots shooter while held
	
    public ShooterCommand(){
    	
    }
    	
    protected void initialize() {
    	if(Robot.ultrasonicSubsystem.isBallInShooter()/* && RobotMap.distance >= 6 && RobotMap.distance <= 11.0*/) {
    		setTimeout(2);
    		Robot.flywheelSubsystem.forward();
//    		Timer.delay(3);
    	} else {
    		end();    	
    	}
    }

    protected void execute() {
//    	if (Robot.intakeSubsystem.isBallInShooter()/* && RobotMap.distance >= 6 && RobotMap.distance <= 11.0  && Robot.flywheelSubsystem.isRateRight()*/) {
//    		Robot.shooterSubsystem.shoot(); // shoots
//    	}
    }

    protected boolean isFinished() {
    	if (Robot.ultrasonicSubsystem.isBallInShooter()) {
    		return isTimedOut();
    	} else {
    		return true;
    	}
    }

    protected void end() {
    	Robot.shooterSubsystem.shoot(); // shoots
    	Timer.delay(0.5);
    	Robot.shooterSubsystem.shootFalse();
    	Timer.delay(2.5);
    	Robot.flywheelSubsystem.move(0); // stops flywheel
    }

    protected void interrupted() {
    	end(); // go to end
    }
}