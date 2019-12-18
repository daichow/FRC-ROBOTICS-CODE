package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurretCommand extends Command {
	boolean turret;
    public TurretCommand(boolean turret) {
    	this.turret = turret;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(turret && Robot.turretSubsystem.encoder() <10000){
    		Robot.turretSubsystem.turn(.4);
    	}else if(!turret && Robot.turretSubsystem.encoder() >-10000){
    		Robot.turretSubsystem.turn(-.4);
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if((turret && Robot.turretSubsystem.encoder() >10000)||(!turret && Robot.turretSubsystem.encoder() <-10000)){
    		return true;
    	}else{
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.turretSubsystem.turn(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
