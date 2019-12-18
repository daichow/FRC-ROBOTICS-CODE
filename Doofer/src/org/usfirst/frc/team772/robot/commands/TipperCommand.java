package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TipperCommand extends Command {
	boolean tip;
    public TipperCommand(boolean tip) {
    	this.tip = tip;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(tip && Robot.tipperSubsystem.getEncPosition() > 200){
    		Robot.tipperSubsystem.brakeOff();
    		Robot.tipperSubsystem.move(RobotMap.tipperUpSpeed);
    	}else{
    		Robot.tipperSubsystem.brakeOff();
    		Robot.tipperSubsystem.move(RobotMap.tipperDownSpeed);
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
    	Robot.tipperSubsystem.move(0);
    	Robot.tipperSubsystem.brakeOn();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
