package org.usfirst.frc.team772.robot.subsystems;

import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeSubsystem extends Subsystem {
    
  	CANTalon intakeMotor = new CANTalon(RobotMap.intakeMotor); // creates intake moto

  	
  	public void topOut(){
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
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

