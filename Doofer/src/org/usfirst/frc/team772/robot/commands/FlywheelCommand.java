package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FlywheelCommand extends Command {
	String fly;
    public FlywheelCommand(String fly) {
    	this.fly = fly;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (fly == "shoot"){
    		Robot.flywheelSubsystem.forward();
    	}else if(fly == "spit"){
    		Robot.flywheelSubsystem.flywheelSpit();
    	}else if(fly == "intake"){
    		Robot.flywheelSubsystem.reverse();
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.flywheelSubsystem.move(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
