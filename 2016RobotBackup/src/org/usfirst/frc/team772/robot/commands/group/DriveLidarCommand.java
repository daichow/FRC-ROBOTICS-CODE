package org.usfirst.frc.team772.robot.commands.group;

import org.usfirst.frc.team772.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveLidarCommand extends Command {

	// Drives until lidar distance
	
	int setpoint;
	int initEnc;
	double initLidar;
	
    public DriveLidarCommand(int setpoint) {
    	this.setpoint = setpoint;
    }

    protected void initialize() {
    	initEnc = Robot.driveSubsystem.getLeftPosition();
    	initLidar = Robot.lidarSubsystem.getDistance();
    	if (Robot.lidarSubsystem.getDistance() - setpoint > 0) {
    		Robot.driveSubsystem.move(-0.5, 0);
    	} else {
    		Robot.driveSubsystem.move(0.5, 0);
    	}
    }

    protected void execute() {
    	if (Robot.lidarSubsystem.getDistance() != 0) {
    		if (Robot.lidarSubsystem.getDistance() - setpoint > 0) {
        		Robot.driveSubsystem.move(-0.5, 0);
        	} else {
        		Robot.driveSubsystem.move(0.5, 0);
        	}
    	} else {
    		Robot.driveSubsystem.move(0, 0);
    	}
    }

    protected boolean isFinished() {
    	return Math.abs(Robot.lidarSubsystem.getDistance() - setpoint) < 5 /*|| (Robot.driveSubsystem.getLeftPosition() - initEnc) / Robot.conversion > initLidar - setpoint*/;
    }
    
    protected void end() {
    	Robot.driveSubsystem.move(0, 0);
    }

    protected void interrupted() {
    	end();
    }
}