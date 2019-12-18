package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class WaitAimCommand extends Command {

	// Waits at most 2 seconds for conditions
	
    public WaitAimCommand() {
    	
    }

    protected void initialize() {
    	setTimeout(2);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
    	return (Robot.ultrasonicSubsystem.isBallInShooter() &&/* Math.abs(RobotMap.centerX) < 10 && RobotMap.centerX != 0 */ (RobotMap.encToX - Robot.turretSubsystem.getEncPosition()) < 250 && System.currentTimeMillis() - RobotMap.timeFlyOn > 1500 && RobotMap.timeFlyOn != 0) || isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    	end();
    }
}