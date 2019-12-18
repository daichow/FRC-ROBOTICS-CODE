package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

//this command controls the wheels on the intake that bring in the ball

public class IntakeWheelCommand extends Command {
	/*
	 * declares a boolean variable so that in OI you can control which way the wheel 
	 * spins in the paramaters 
	 */
	boolean direction; 
    public IntakeWheelCommand(boolean direction) {
    	this.direction = direction;
    }

    protected void initialize() {
    	/*
    	 * checks the given parameter
    	 * if direction = true then wheel and conveyor spins in(conveyor is active only until the ball is in chamber
    	 * if direction = false then wheel and conveyor spins out (conveyor is active only until the ball is out of chamber
    	 */
    	if (direction){
    		if(Robot.ultrasonic.getRangeInches()  > 6){
    			Robot.intakeSubsystem.intakeWheel(RobotMap.intakeInSpeed);
    			Robot.conveyorSubsystem.conveyor(RobotMap.convayorInSpeed);
    		}
    	}else{
    		if(Robot.ultrasonic.getRangeInches() < 5){
    			Robot.intakeSubsystem.intakeWheel(RobotMap.intakeOutSpeed);
    			Robot.conveyorSubsystem.conveyor(RobotMap.convayorOutSpeed);
    		}
    	}
    }

    protected void execute() {
    }

    protected boolean isFinished() {
    	if (direction){
    		if (Robot.ultrasonic.getRangeInches() < 5){
    			return true;
    		}else{
    			return false;
    		}
    	}else{
    		if (Robot.ultrasonic.getRangeInches() > 6){
    			return true;
    		}else{
    			return false;
    		}
    	}
    }

    protected void end() {
    	Robot.intakeSubsystem.intakeWheel(0); // stops intake wheels
    	Robot.conveyorSubsystem.conveyor(0);
    }

    protected void interrupted() {
    	end(); //sends command to end when interrupted by another command or release of button
    }
}
