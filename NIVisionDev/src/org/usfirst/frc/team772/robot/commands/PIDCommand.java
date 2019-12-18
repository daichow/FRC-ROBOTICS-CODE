package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Command;

public class PIDCommand extends Command {

	CANTalon motor;
	double speed = 0;
	double setpoint = 0;
	double intCrosstrackError = 0.0;
	double waitTime = 0.01;
	double diffCrosstrackError;
	double crosstrackError;
	double finishError;
    
    public PIDCommand(double setpoint, double finishError) {
    	this.setpoint = setpoint;
    	this.finishError = finishError;
    }

    protected void initialize() {
    	crosstrackError = RobotMap.centerX - setpoint; // get error
    }

    protected void execute() {
    	diffCrosstrackError = ((RobotMap.centerX - setpoint) - crosstrackError) / 0.02;
    	crosstrackError = RobotMap.centerX - setpoint;
    	intCrosstrackError += crosstrackError * 0.02;
    	speed = -RobotMap.kP * crosstrackError - RobotMap.kD * diffCrosstrackError - RobotMap.kI * intCrosstrackError;
    	if (speed > 1) { //caps speed
    		speed = 1;
    	} else if (speed < -1){
    		speed = -1;
    	}
    	motor.set(speed);
    }

    protected boolean isFinished() {
        if (Math.abs(crosstrackError) >= finishError) {
        	return false;
        } else {
        	return true;
        }
    }

    protected void end() {
    	motor.set(0);
    }

    protected void interrupted() {
    	end();
    }
}