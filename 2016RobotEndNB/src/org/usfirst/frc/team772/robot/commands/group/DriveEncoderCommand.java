package org.usfirst.frc.team772.robot.commands.group;

import org.usfirst.frc.team772.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveEncoderCommand extends Command {

	int initEnc;
	
    public DriveEncoderCommand() {
        
    }

    protected void initialize() {
    	initEnc = Robot.driveSubsystem.getLeftPosition();
    	Robot.driveSubsystem.move(-0.5, 0);
    }

    protected void execute() {
    	Robot.driveSubsystem.move(-0.5, 0);
    }

    protected boolean isFinished() {
        return (Robot.driveSubsystem.getLeftPosition() - initEnc) / Robot.conversion > 72;
    }

    protected void end() {
    	Robot.driveSubsystem.move(0, 0);
    }

    protected void interrupted() {
    	end();
    }
}