package org.usfirst.frc.team772.robot.subsystems;

import org.usfirst.frc.team772.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterSubsystem extends Subsystem {
	
	CANTalon flywheelLeft = new CANTalon(RobotMap.flywheelLeft);
	CANTalon flywheelRight = new CANTalon(RobotMap.flywheelRight);
	CANTalon feeder = new CANTalon(RobotMap.feeder);
	CANTalon windowMotor1 = new CANTalon(RobotMap.windowMotor1);
	CANTalon windowMotor2 = new CANTalon(RobotMap.windowMotor2);
	
	
	public void flywheel(double speed){
		flywheelLeft.set(-speed);
		flywheelRight.set(-speed);
	}
	
	public void feeder(double speed){
		feeder.set(-speed);
	}
	
	
	
	public void windowMotor(double speed){
		windowMotor1.set(speed);
		windowMotor2.set(-speed);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

