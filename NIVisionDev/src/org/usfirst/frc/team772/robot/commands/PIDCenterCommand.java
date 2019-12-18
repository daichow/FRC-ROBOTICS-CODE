package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Command;

public class PIDCenterCommand extends Command {

	double speed = 0;
	double intCrosstrackError = 0.0;
	double waitTime = 0.01;
	double diffCrosstrackError;
	double crosstrackError;
	
    public PIDCenterCommand(CANTalon tur) {
    }

    protected void initialize() {
    	crosstrackError = RobotMap.centerX; // get error
    }

    protected void execute() {
    	diffCrosstrackError = (RobotMap.centerX - crosstrackError) / 0.02;
    	crosstrackError = RobotMap.centerX;
    	intCrosstrackError += crosstrackError * 0.02;
    	speed = -RobotMap.kP * crosstrackError - RobotMap.kD * diffCrosstrackError - RobotMap.kI * intCrosstrackError;
    	if (speed > 1) { //caps speed
    		speed = 1;
    	} else if (speed < -1){
    		speed = -1;
    	}
    	Robot.turretSubsystem.move(speed);
//    	Robot.flyhweelSubsystem.set(speed / 4);
    }

    protected boolean isFinished() {
        if (Math.abs(crosstrackError) >= 5) {
        	return false;
        } else {
        	return true;
        }
    }

    protected void end() {
//    	drive.stopMotor();
    	Robot.turretSubsystem.move(0);
//    	Robot.flyhweelSubsystem.set(0);
    }

    protected void interrupted() {
    	end();
    }
}