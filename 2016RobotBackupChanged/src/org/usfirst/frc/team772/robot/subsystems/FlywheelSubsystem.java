package org.usfirst.frc.team772.robot.subsystems;

import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class FlywheelSubsystem extends Subsystem {
	
	CANTalon flywheelMotor = new CANTalon(RobotMap.flywheelMotor); // create flywheel motor
    
    public void reverse(){ // sets flywheel motor reverse 
		flywheelMotor.set(RobotMap.flywheelRSpeed);
	}
	
	public void forward(){ // sets flywheel motor forward
		flywheelMotor.set(RobotMap.flywheelFSpeed);
	}
	
	public void flywheelSpit(){ // sets flywheel motor to spit speed
		flywheelMotor.set(RobotMap.flywheelSpit);
	}
	
	public void move(double speed){ // sets flywheel motor to specified speed
		flywheelMotor.set(speed);
	}
	
	public boolean isRateRight(){ // returns whether flywheel fast enough
		return flywheelMotor.getEncVelocity() >= 280; // not actual for new motor
	}
	
	public int getRate(){ // returns flywheel rate
		return flywheelMotor.getEncVelocity();
	}
	
	public int getEncPosition(){ // returns flywheel encoder position
		return flywheelMotor.getEncPosition();
	}
	
	public void encoderReset(){ // resets flywheel encoder
		flywheelMotor.setEncPosition(0);
	}
	
	public void initDefaultCommand() {
        
    }
}