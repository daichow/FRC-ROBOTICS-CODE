package org.usfirst.frc.team772.robot.commands.group;

import org.usfirst.frc.team772.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveUltraCommand extends Command {

	int setpoint;
	int initDist = 130;
	int initEnc;
	double initUltra;
	
    public DriveUltraCommand(int setpoint) {
    	this.setpoint = setpoint;
    	initDist -= setpoint;
    }

    protected void initialize() {
    	initEnc = Robot.driveSubsystem.getLeftPosition();
    	initUltra = Robot.ultrasonicSubsystem.getDistanceUS();
    	if (Robot.ultrasonicSubsystem.getDistanceUS() - setpoint > 0) {
    		Robot.driveSubsystem.move(-0.5, 0);
    	} else {
    		Robot.driveSubsystem.move(0.5, 0);
    	}
    }

    protected void execute() {
    	if (Robot.ultrasonicSubsystem.getDistanceUS() < 200) {
    		if (Robot.ultrasonicSubsystem.getDistanceUS() - setpoint > 0) {
        		Robot.driveSubsystem.move(-0.5, 0);
        	} else {
        		Robot.driveSubsystem.move(0.5, 0);
        	}
    	} else {
    		Robot.driveSubsystem.move(0, 0);
    	}
    }

    protected boolean isFinished() {
    	return Math.abs(Robot.ultrasonicSubsystem.getDistanceUS() - setpoint) < 5 || (Robot.driveSubsystem.getLeftPosition() - initEnc) / Robot.conversion > initUltra - setpoint;
    }
    
    protected void end() {
    	Robot.driveSubsystem.move(0, 0);
    }

    protected void interrupted() {
    	end();
    }
}