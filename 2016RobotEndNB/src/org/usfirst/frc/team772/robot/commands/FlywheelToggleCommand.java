//package org.usfirst.frc.team772.robot.commands;
//
//import org.usfirst.frc.team772.robot.Robot;
//import org.usfirst.frc.team772.robot.Robot.Direction;
//
//import edu.wpi.first.wpilibj.command.Command;
//
//public class FlywheelToggleCommand extends Command {
//
//	// Toggle flywheel motor
//	
//	Direction direction; // direction of flywheel
//	boolean toggle = false; // toggle variable
//	
//    public FlywheelToggleCommand(Direction direction) {
//    	this.direction = direction; // sets local direction to parameter direction
//    }
//
//    protected void initialize() {
//    	if (toggle) { // toggles flywheel
//    		Robot.flywheelSubsystem.move(0); // stops flywheel
//    		toggle = false;
//    	} else {
//    		if (direction == Direction.Reverse) { // flywheel moves in direction
//    			Robot.flywheelSubsystem.reverse();
//    		} else {
//    			Robot.flywheelSubsystem.forward();
//    		}
//    		toggle = true;
//    	}
//    }
//
//    protected void execute() {
//    
//    }
//
//    protected boolean isFinished() {
//        return true; // no finish condition
//    }
//
//    protected void end() {
//    	
//    }
//
//    protected void interrupted() {
//    	end(); // go to end
//    }
//}