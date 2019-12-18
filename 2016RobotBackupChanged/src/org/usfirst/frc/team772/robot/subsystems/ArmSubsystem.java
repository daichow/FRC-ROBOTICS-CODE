package org.usfirst.frc.team772.robot.subsystems;

import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ArmSubsystem extends Subsystem {
    
	CANTalon arm = new CANTalon(RobotMap.armMotor); // creates arm motor

    public void initDefaultCommand() {

    }
    
    public void move(double speed){ // move arm using specified speed value
    	arm.set(-speed);
    }
}