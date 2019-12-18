package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TipperCommand extends Command {
	boolean tipper;
    public TipperCommand(boolean tipper) {
    	this.tipper = tipper;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//&& Robot.tipperSubsystem.encoder() < -200
    	if (tipper ){//up
    		Robot.turretFixCommand.start();
    		Robot.tipperSubsystem.breakOff();
    		Robot.tipperSubsystem.tipperMotor(-0.5);
    	}else if(!tipper){//false
    		Robot.tipperSubsystem.breakOff();
    		Robot.tipperSubsystem.tipperMotor(0.25);
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//    	if (tipper &&  Robot.tipperSubsystem.encoder() > -200){
//    		return true;
//    	}	
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	if (tipper){
    		Robot.tipperSubsystem.tipperMotor(0);
    		Robot.tipperSubsystem.breakOn();
    	}else if(!tipper){
    		Robot.tipperSubsystem.tipperMotor(0);
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
