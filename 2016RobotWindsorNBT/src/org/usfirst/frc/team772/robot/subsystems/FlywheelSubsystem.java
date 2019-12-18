package org.usfirst.frc.team772.robot.subsystems;

import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class FlywheelSubsystem extends Subsystem {
	
	CANTalon flywheelMotor1 = new CANTalon(RobotMap.flywheelMotor1); // create flywheel motor
	CANTalon flywheelMotor2 = new CANTalon(RobotMap.flywheelMotor2); // create flywheel motor
	
	public FlywheelSubsystem(){
		flywheelMotor2.setInverted(true);
	}
    
    public void reverse1(){ // sets flywheel motor reverse 
		flywheelMotor1.set(RobotMap.flywheelRSpeed);
	}
	
	public void forward1(){ // sets flywheel motor forward
		flywheelMotor1.set(RobotMap.flywheelFSpeed);
	}
	
	public void flywheelSpit1(){
		flywheelMotor1.set(RobotMap.flywheelSpit);
	}
	
	public void move1(double speed){ // sets flywheel motor to specified speed
		flywheelMotor1.set(speed);
	}
	
    public void reverse2(){ // sets flywheel motor reverse 
		flywheelMotor2.set(RobotMap.flywheelRSpeed);
	}
	
	public void forward2(){ // sets flywheel motor forward
		flywheelMotor2.set(RobotMap.flywheelFSpeed);
	}
	
	public void flywheelSpit2(){
		flywheelMotor2.set(RobotMap.flywheelSpit);
	}
	
	public void move2(double speed){ // sets flywheel motor to specified speed
		flywheelMotor2.set(speed);
	}
	
	public void initDefaultCommand() {
        
    }
}