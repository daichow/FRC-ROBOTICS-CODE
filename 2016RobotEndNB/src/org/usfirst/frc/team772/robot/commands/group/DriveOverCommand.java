package org.usfirst.frc.team772.robot.commands.group;

import org.usfirst.frc.team772.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveOverCommand extends Command {
	
	double speed;
	double time = 2.5;

	boolean turnedUp = false; // have we turned up yet
	boolean turnedDown = false; // have we turned down yet
	boolean isNeutral = true; // are we neutral 10
	boolean isNeutralAfter = false; // are we neutral 30
	
    public DriveOverCommand(double speed) {
    	this.speed = speed;
    }
    
    public DriveOverCommand(double speed, double time){
    	this.speed = speed;
    	this.time = time;
    }
    protected void initialize() {
    	setTimeout(time);
    }

    protected void execute() {
    	if (turnedUp) {
    		Robot.driveSubsystem.move(-1, 0);
    	} else {
    		Robot.driveSubsystem.move(speed, 0);
    	}
    	switch(Robot.tilt){
	    	case "up": turnedUp = true;
	    		isNeutral = false;
	    		isNeutralAfter = false;
	    		break;
	    	case "down": turnedDown = true;
	    		isNeutral = false;
	    		isNeutralAfter = false;
	    		break;
//	    	case "neutral": isNeutral = true;
//	    		isNeutralAfter = false;
//	    		break;
	    	case "neutralAfter": isNeutralAfter = true;
	    		break;
	    	default:
    	}
    }

    protected boolean isFinished() {
        return turnedUp && turnedDown && isNeutralAfter || isTimedOut(); // have we turned up, down and are neutral
    }

    protected void end() {
    	Robot.driveSubsystem.move(0, 0);
    }

    protected void interrupted() {
    	end();
    }
}