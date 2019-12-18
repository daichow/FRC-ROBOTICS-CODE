package org.usfirst.frc.team772.robot.commands.group;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class TurretFixCommand extends Command {

    public TurretFixCommand() {

    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	if (RobotMap.centerX > 0) { // sets turret speed
    		Robot.turretSubsystem.move(0.15);
    	} else {
    		Robot.turretSubsystem.move(-0.15);
    	}
    }

    protected boolean isFinished() {
        return Math.abs(RobotMap.centerX) <= 10; // finishes when within 10 centerX values
    }

    protected void end() {
    	Robot.turretSubsystem.move(0); // turret stop
    }

    protected void interrupted() {
    	end();
    }
}