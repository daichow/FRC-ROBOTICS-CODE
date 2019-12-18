package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class UltrasonicTestCommand extends Command {

    public UltrasonicTestCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (Robot.ultrasonic.getRangeInches() > 10){
    		Robot.conveyorSubsystem.conveyor(RobotMap.convayorInSpeed);
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Robot.ultrasonic.getRangeInches() < 5){
    		return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.conveyorSubsystem.conveyor(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
