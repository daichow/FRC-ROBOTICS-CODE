package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class WaitFlyCommand extends Command {

    public WaitFlyCommand() {

    }

    protected void initialize() {
    }

    protected void execute() {
    }

    protected boolean isFinished() {
    	return (1 / Robot.encoderCounter.getPeriod()) > 1950 && Robot.ultrasonicSubsystem.isBallInShooter() && Math.abs(RobotMap.centerX - 10) < 10;
    }

    protected void end() {
    }

    protected void interrupted() {
    	end();
    }
}
