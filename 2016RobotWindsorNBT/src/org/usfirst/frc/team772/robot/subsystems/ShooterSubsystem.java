package org.usfirst.frc.team772.robot.subsystems;

import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterSubsystem extends Subsystem {

	Solenoid shootCylinder = new Solenoid(RobotMap.PCM, RobotMap.shooter);// declares a single solenoid for puncher
	
	public ShooterSubsystem(){
		
	}
	
	public void shoot(){ // shoots
		shootCylinder.set(true);
	}
	
	public void shootFalse(){ // stops shooting
		shootCylinder.set(false);
	}
	
    public void initDefaultCommand() {
       
    }
}