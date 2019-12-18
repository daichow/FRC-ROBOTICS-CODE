package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class CheckShootCommand extends Command {

    public CheckShootCommand() {

    }

    protected void initialize() {
    	if(Robot.ultrasonicSubsystem.isBallInShooter() && Math.abs(RobotMap.centerX) < 20){
    		Robot.shooterSubsystem.shoot();
    	}
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.shooterSubsystem.shootFalse();
    }

    protected void interrupted() {
    	end();
    }
}