package org.usfirst.frc.team772.robot.subsystems;

import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeSubsystem extends Subsystem {
    
	Ultrasonic intakeUltrasonic = new Ultrasonic(RobotMap.intakeUltra1, RobotMap.intakeUltra2); // creates intake ultrasonic
	Ultrasonic shooterUltrasonic = new Ultrasonic(RobotMap.shooterUltra1, RobotMap.shooterUltra2); // creates shooter ultrasonic
  	CANTalon intakeMotor = new CANTalon(RobotMap.intakeMotor); // creates intake motor
  	
  	public IntakeSubsystem(){
  		intakeUltrasonic.setEnabled(true); // enables intake ultrasonic
  		intakeUltrasonic.setAutomaticMode(true); // sets intake ultrasonic to automatic
  		shooterUltrasonic.setEnabled(true); // enables shooter ultrasonic
  		shooterUltrasonic.setAutomaticMode(true); // sets shooter ultrasonic to automatic
	}
  	
  	public double getShooterUS(){ // returns shooter ultrasonic value
		return shooterUltrasonic.getRangeInches();
	}
  	
  	public double getIntakeUS(){ // returns intake ultrasonic value
  		return intakeUltrasonic.getRangeInches();
  	}
	
	public boolean isBallInShooter(){ // returns if ball in shooter
  		return shooterUltrasonic.getRangeInches() < 8;
  	}
	
  	public boolean isBallIn(){ // returns if ball is in intake
  		return intakeUltrasonic.getRangeInches() < 6.0;
    }
  	
  	public void topOut(){ // intake motor top out
  		intakeMotor.set(RobotMap.intakeOutTopSpeed);
  	}
  	
  	public void topIn(){ // intake motor top in
  		intakeMotor.set(RobotMap.intakeTopInSpeed);
  	}
  	
  	public void bottomIn(){ // intake motor bottom in
  		intakeMotor.set(RobotMap.intakeBottomInSpeed);
  	}
  
  	public void out(){ // intake motor out
  		intakeMotor.set(RobotMap.intakeOutSpeed);
  	}
  	
  	public void reverseIntake(){ // intake motor reverse
  		intakeMotor.set(RobotMap.reverseIntakeSpeed);
  	}
  	
  	public void move(double speed){ // move intake motor using specified speed
  		intakeMotor.set(speed);
  	}
	
  	public void initDefaultCommand(){
	  
  	}
}