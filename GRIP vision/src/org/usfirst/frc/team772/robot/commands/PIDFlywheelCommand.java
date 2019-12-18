package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class PIDFlywheelCommand extends Command {

	// center 160, 120
	double setpoint;
	
	double crosstrackError;
	double intCrosstrackError = 0.0;
	double diffCrosstrackError;
	
	double rotation = 0;
	double waitTime = 0.01;
	
    public PIDFlywheelCommand(double setpoint) {
        this.setpoint = setpoint; // takes parameters and places in local
    }

    protected void initialize() {
    	crosstrackError = (setpoint - RobotMap.centerX); // get error
    }

    protected void execute() {
    	diffCrosstrackError = ((RobotMap.centerX - setpoint) - crosstrackError) / 0.02; // calc d
		crosstrackError = (RobotMap.centerX - setpoint); // calc p
		intCrosstrackError += crosstrackError * 0.02; // calc i
		rotation = -RobotMap.kP * crosstrackError - RobotMap.kD * diffCrosstrackError - RobotMap.kI * intCrosstrackError;
		if (rotation > 1) { // caps rotation
			rotation = 1;
		} else if (rotation < -1){
		}
		Robot.flywheelSubsystem.move(rotation);
    }

    protected boolean isFinished() {
    	if (Math.abs(crosstrackError) >= 0.5) { // room for error
			return false;
		} else {
			return true;
		}
    }

    protected void end() {
    	Robot.flywheelSubsystem.stop();
    }

    protected void interrupted() {
    	end();
    }
}