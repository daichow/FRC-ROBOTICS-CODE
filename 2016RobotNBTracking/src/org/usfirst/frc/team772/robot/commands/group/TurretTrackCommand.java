package org.usfirst.frc.team772.robot.commands.group;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class TurretTrackCommand extends Command {

    public TurretTrackCommand() {

    }

    protected void initialize() {
    	
    	setTimeout(1.5);
    	Robot.turretSubsystem.setSetpoint(RobotMap.encToX);
    	Robot.turretSubsystem.enable();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
    	if(Math.abs(Robot.turretSubsystem.getEncPosition()) > 16018){ // if greater than 90 degrees
    		return true;
    	}
    	return (Math.abs(Robot.turretSubsystem.getSetpoint() - Robot.turretSubsystem.getEncPosition()) < 167) || isTimedOut();
    }

    protected void end() {
    	Robot.turretSubsystem.disable();
    	Robot.turretSubsystem.move(0);
    }

    protected void interrupted() {
    	end();
    }
}