package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class CheckShootCommand extends Command {

	// Check conditions then shoot
	
    public CheckShootCommand() {

    }

    protected void initialize() {
    	setTimeout(0.5);
    	if(Robot.ultrasonicSubsystem.isBallInShooter() &&/* Math.abs(RobotMap.centerX) < 10 && RobotMap.centerX != 0 */ (RobotMap.encToX - Robot.turretSubsystem.getEncPosition()) < 167 && System.currentTimeMillis() - RobotMap.timeFlyOn > 1500 && RobotMap.timeFlyOn != 0){
    		Robot.shooterSubsystem.shoot();
    	}
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
    	Robot.shooterSubsystem.shootFalse();
    }

    protected void interrupted() {
    	end();
    }
}