package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CancelCommand extends Command {

    public CancelCommand() {
    	
    }

    protected void initialize() {
    	Robot.cancel = true;
    	Robot.turretSubsystem.disable();
    	Robot.visionTurretSubsystem.disable();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.cancel = false;
    }

    protected void interrupted() {
    	end();
    }
}