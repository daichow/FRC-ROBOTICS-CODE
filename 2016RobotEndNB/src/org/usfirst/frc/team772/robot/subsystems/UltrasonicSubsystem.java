package org.usfirst.frc.team772.robot.subsystems;

import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

public class UltrasonicSubsystem extends Subsystem {
    
	Ultrasonic intakeUltrasonic = new Ultrasonic(RobotMap.intakeUltra1, RobotMap.intakeUltra2); // creates intake ultrasonic
	Ultrasonic shooterUltrasonic = new Ultrasonic(RobotMap.shooterUltra1, RobotMap.shooterUltra2); // creates shooter ultrasonic
//	Ultrasonic distanceUltrasonic = new Ultrasonic(RobotMap.distanceUltra1, RobotMap.distanceUltra2); // creates distance ultrasonic
	
	public UltrasonicSubsystem(){
		intakeUltrasonic.setEnabled(true); // enables intake ultrasonic
  		intakeUltrasonic.setAutomaticMode(true); // sets intake ultrasonic to automatic
  		shooterUltrasonic.setEnabled(true); // enables shooter ultrasonic
  		shooterUltrasonic.setAutomaticMode(true); // sets shooter ultrasonic to automatic
//  		distanceUltrasonic.setEnabled(true); // enables distance ultrasonic
//  		distanceUltrasonic.setAutomaticMode(true); // sets distance ultrasonic to automatic
	}
	
	public double getShooterUS(){ // returns shooter ultrasonic value
		return shooterUltrasonic.getRangeInches();
	}
  	
  	public double getIntakeUS(){ // returns intake ultrasonic value
  		return intakeUltrasonic.getRangeInches();
  	}
	
//  	public double getDistanceUS(){ // returns distance ultrasonic value
//  		return distanceUltrasonic.getRangeInches();
//  	}
  	
	public boolean isBallInShooter(){ // returns if ball in shooter
  		return shooterUltrasonic.getRangeInches() < 4;
  	}
	
  	public boolean isBallIn(){ // returns if ball is in intake
  		return intakeUltrasonic.getRangeInches() <= 12.0 && intakeUltrasonic.getRangeInches() >= 4.0;
    }
	
    public void initDefaultCommand() {

    }
}