package org.usfirst.frc.team772.robot.subsystems;

import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class FlywheelSubsystem extends Subsystem {
    
	CANTalon flywheelMotor1 = new CANTalon(10); // create flywheel motor
    
	public void shoot(){
		flywheelMotor1.set(-1);
	}
	public void spit(){
		flywheelMotor1.set(.5);
	}
	
	public void set(double speed){
		flywheelMotor1.set(-speed);
	}
	
    public void initDefaultCommand() {
    }
}

