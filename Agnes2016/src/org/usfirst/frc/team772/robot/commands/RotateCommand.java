package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class RotateCommand extends Command {

	double setpoint = 0;
	boolean direction = true; // true is CW, false is CCW
	double acceptableError = 0;
	
	double rotation = 0;
	
	double crosstrackError;
	double intCrosstrackError = 0.0;
	double diffCrosstrackError;
	
    public RotateCommand(double setpoint, double acceptableError) {
    	this.setpoint = setpoint;
    	this.acceptableError = acceptableError;
    }

    protected void initialize() {   	
    	crosstrackError = Robot.navXSub.getAngle() - setpoint;
    	double diff = crosstrackError;
    	while(diff < 0){
    		diff += 360;
    	}
    	direction = diff < 180 && diff > 0;
    }

    protected void execute() {
    	diffCrosstrackError = ((Robot.navXSub.getAngle() - setpoint) - crosstrackError) / 0.02;
    	crosstrackError = Robot.navXSub.getAngle() - setpoint;
    	intCrosstrackError += crosstrackError * 0.02;
    	rotation = -RobotMap.kP * crosstrackError - RobotMap.kD * diffCrosstrackError - RobotMap.kI * intCrosstrackError;
    	if (rotation > 1) { //caps speed
    		rotation = 1;
    	} else if (rotation < -1){
    		rotation = -1;
    	}
    	if(direction)
    		Robot.driveSub.move(0, rotation);
    	else
    		Robot.driveSub.move(0, -rotation);
    }

    protected boolean isFinished() {
    	if(Robot.navXSub.getAngle() > (setpoint - acceptableError) && Robot.navXSub.getAngle() < (setpoint + acceptableError)){
    		return true;
    	}else{
    		return false;
    	}
    }

    protected void end() {
    	Robot.driveSub.ArcadeDriveStop();
    }

    protected void interrupted() {
    	end();
    }
}