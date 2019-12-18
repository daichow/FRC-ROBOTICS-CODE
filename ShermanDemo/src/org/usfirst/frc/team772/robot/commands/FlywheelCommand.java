package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.Robot.Direction;
import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class FlywheelCommand extends Command {

	// Move flywheel motor
	
	Direction direction; // direction of flywheel

    public FlywheelCommand(Direction direction) {
    	this.direction = direction; // sets local direction to parameter direction
    }

    protected void initialize() {
    	switch(direction){
	    	case Forward: 
	    		Robot.flywheelSubsystem.forward1();
	    		Robot.flywheelSubsystem.forward2();
	    		RobotMap.timeFlyOn = System.currentTimeMillis();
	    		break;
	    	case Reverse:
	    		Robot.flywheelSubsystem.reverse1();
	    		Robot.flywheelSubsystem.reverse2();
	    		break;
	    	case Stop: 
	    		Robot.flywheelSubsystem.move1(0);
	    		Robot.flywheelSubsystem.move2(0);
	    		RobotMap.timeFlyOn = 0;
	    		break;
    		default:
    			Robot.flywheelSubsystem.move1(0);
	    		Robot.flywheelSubsystem.move2(0);
	    		break;
    	}
    }

    protected void execute() {
    
    }

    protected boolean isFinished() {
        return true; // no finish condition
    }

    protected void end() {

    }

    protected void interrupted() {
    	end(); // go to end
    }
}