package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeCommand extends Command {
	boolean toggle;
    public IntakeCommand(boolean toggle) {
    	this.toggle = toggle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(toggle){//intakeBalls
    		Robot.intakeSubsystem.intake(RobotMap.intakeInSpeed);
    	}else{
    		Robot.intakeSubsystem.intake(RobotMap.intakeOutSpeed);
    		Robot.shooterSubsystem.windowMotor(-1);
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
    	Robot.intakeSubsystem.intake(0);
    	Robot.shooterSubsystem.windowMotor(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
