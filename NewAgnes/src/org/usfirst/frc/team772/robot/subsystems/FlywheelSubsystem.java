package org.usfirst.frc.team772.robot.subsystems;

import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class FlywheelSubsystem extends Subsystem {
	
	Talon flywheel = new Talon(RobotMap.flywheel);
	
	public void RunOneWay(){
		flywheel.set(RobotMap.flywheelSpeed1); // flywheel full speed one way
	}
	
	public void RunTheOtherWay(){
		flywheel.set(RobotMap.flywheelSpeed2); // flywheel full speed the other way
	}
	
	public void Stop(){
		flywheel.set(RobotMap.flywheelStop); // no movement
	}

    public void initDefaultCommand() {

    }
}