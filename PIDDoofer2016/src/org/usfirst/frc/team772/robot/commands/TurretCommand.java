package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurretCommand extends Command {
	boolean turn;
    public TurretCommand(boolean turn) {
    	this.turn = turn;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(turn&& Robot.turretSubsystem.getEncPosition() > -10000){
    		Robot.turretSubsystem.move(-0.4);//left
    	}else if(!turn && Robot.turretSubsystem.getEncPosition() <10000){
    		Robot.turretSubsystem.move(0.4);//right
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(turn && Robot.turretSubsystem.getEncPosition() < -10000){
    		return true;
    	}else if(!turn && Robot.turretSubsystem.getEncPosition() >10000){
    		return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.turretSubsystem.move(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
