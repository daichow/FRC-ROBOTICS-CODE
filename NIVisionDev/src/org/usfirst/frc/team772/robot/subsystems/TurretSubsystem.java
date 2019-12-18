package org.usfirst.frc.team772.robot.subsystems;

import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class TurretSubsystem extends Subsystem {

	CANTalon turretMotor = new CANTalon(8); // creates turret motor
	
	public void CCW(){ // moves turret CCW
		turretMotor.set(-1);
	}
	
	public void CW(){ // moves turret CW
		turretMotor.set(1);
	}
	
	public void move(double speed){ // moves turret using speed parameter
		turretMotor.set(speed);
	}
	
	public void stop(){ // stops turret
		turretMotor.set(0);
	}
	
	public CANTalon getTurretMotor(){ // returns turret motor
		return turretMotor;
	}
	
	public int getEncPosition(){
		return turretMotor.getEncPosition();
	}
	
    public void initDefaultCommand() {
      
    }
}