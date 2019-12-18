package org.usfirst.frc.team772.robot.subsystems;

import com.ctre.CanTalonJNI;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ConveyorSubsystem extends Subsystem {
    
	com.ctre.CANTalon conveyor = new com.ctre.CANTalon(9);
	
	public void conveyor(double speed){
		conveyor.set(speed);
	}
	
    public void initDefaultCommand() {
    }
}

