package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class TurretFixCommand extends Command {

    public TurretFixCommand() {

    }

    protected void initialize() {
    }

    protected void execute() {
    	if (RobotMap.centerX > 0) {
    		Robot.turretSubsystem.move(0.15);
    	} else {
    		Robot.turretSubsystem.move(-0.15);
    	}
    }

    protected boolean isFinished() {
        return Math.abs(RobotMap.centerX) <= 10;
    }

    protected void end() {
    	Robot.turretSubsystem.move(0);
    }

    protected void interrupted() {
    	end();
    }
}