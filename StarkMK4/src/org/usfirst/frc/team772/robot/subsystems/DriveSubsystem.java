package org.usfirst.frc.team772.robot.subsystems;

import org.usfirst.frc.team772.robot.OI;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
	
	Relay underGlow = new Relay(2);
	
	//motor declarations
    com.ctre.CANTalon rightT1 = new com.ctre.CANTalon(1);
    com.ctre.CANTalon rightT2 = new com.ctre.CANTalon(2);
    com.ctre.CANTalon leftT3 = new com.ctre.CANTalon(3);
    com.ctre.CANTalon leftT4 = new com.ctre.CANTalon(4);
    
    RobotDrive robotDrive; //robotdrive declaration
    
    public DriveSubsystem(){
    	//rear and front motor assignments
    	robotDrive = new RobotDrive(rightT1, rightT2, leftT3, leftT4);
    	//motor inversions
    	robotDrive.setInvertedMotor(MotorType.kRearRight, false); 
    	robotDrive.setInvertedMotor(MotorType.kFrontLeft, false); 
    	robotDrive.setInvertedMotor(MotorType.kFrontRight, false);
    	robotDrive.setInvertedMotor(MotorType.kRearLeft, false); 		
    	//safety
    	robotDrive.setSafetyEnabled(true);
    }
    
    /*used when robot controlled by joystick, the speed is used as a
     * multiplying factor to dumb down motor, set when using method
     * in command
     */
    public void move(Joystick joystick, double speed){
    	robotDrive.arcadeDrive(joystick.getY()*speed, joystick.getX()*speed);
    }
    
    //used for auto movement
    public void auto(double speedDrive, double speedRot){
    	robotDrive.arcadeDrive(speedDrive, speedRot);
    }
    
    public void underGlowOn(){
		underGlow.set(Relay.Value.kForward);
	}
    
	public void underGlowOff(){
		underGlow.set(Relay.Value.kOff);
	}
    
    public void initDefaultCommand() {
    }
}

