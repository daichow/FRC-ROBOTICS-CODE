package org.usfirst.frc.team772.robot.subsystems;

import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LiftingSubsystem extends Subsystem {
    
	CANTalon window = new CANTalon(RobotMap.windowMotor);
	
    public void initDefaultCommand() {

    }
    
    public void move(double speed){ // move window using specified speed value
    	window.set(-speed);
    }
}