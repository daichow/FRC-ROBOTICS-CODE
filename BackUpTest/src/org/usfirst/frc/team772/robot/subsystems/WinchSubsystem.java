package org.usfirst.frc.team772.robot.subsystems;

import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class WinchSubsystem extends Subsystem {
    
	CANTalon winch = new CANTalon(RobotMap.winchMotor); // creates winch motor

    public void initDefaultCommand() {

    }
    
    public void move(double speed){ // move winch using specified speed value
    	winch.set(-speed);
    }
}