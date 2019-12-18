package org.usfirst.frc.team772.robot.subsystems;

import org.usfirst.frc.team772.robot.RobotMap;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.CANTalon;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
    
	RobotDrive robotDrive; // creates robotDrive
	CANTalon driveMotor1 = new CANTalon(RobotMap.driveL1); // creates drive motor 1
	CANTalon driveMotor2 = new CANTalon(RobotMap.driveL2); // creates drive motor 2
	CANTalon driveMotor3 = new CANTalon(RobotMap.driveR1); // creates drive motor 3
	CANTalon driveMotor4 = new CANTalon(RobotMap.driveR2); // creates drive motor 4

	public DriveSubsystem(){
		
		robotDrive = new RobotDrive(driveMotor4, driveMotor3, driveMotor2, driveMotor1); // assigns motors to drive
		
		// inverting drive motors
		robotDrive.setInvertedMotor(MotorType.kRearRight, true); 
		robotDrive.setInvertedMotor(MotorType.kFrontLeft, true); 
		robotDrive.setInvertedMotor(MotorType.kFrontRight, true);
		robotDrive.setInvertedMotor(MotorType.kRearLeft, true); 
		
		robotDrive.setSafetyEnabled(true); // enable safety
	}
	
	public void move(Joystick joystick, double speed){ // drives robot using joystick and speed parameters
		robotDrive.arcadeDrive(joystick.getY() * speed, -joystick.getX() * speed);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

