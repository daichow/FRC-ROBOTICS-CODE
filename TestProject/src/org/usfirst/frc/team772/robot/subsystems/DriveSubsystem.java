package org.usfirst.frc.team772.robot.subsystems;

import org.usfirst.frc.team772.robot.RobotMap;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSubsystem extends Subsystem {
	
	RobotDrive robotDrive;
	CANTalon driveMotor1 = new CANTalon(RobotMap.rearleftmotor);
	CANTalon driveMotor2 = new CANTalon(RobotMap.rearrightmotor);
	CANTalon driveMotor3 = new CANTalon(RobotMap.frontleftmotor);
	CANTalon driveMotor4 = new CANTalon(RobotMap.frontrightmotor);
	public Encoder encoderLeft = new Encoder(1, 2);
	public Encoder encoderRight = new Encoder(3, 4);


	
    public void initDefaultCommand() {
    	
    }
    
    public DriveSubsystem(){
    	robotDrive = new RobotDrive(driveMotor1, driveMotor2, driveMotor3, driveMotor4); 
    	robotDrive.setInvertedMotor(MotorType.kFrontLeft, true);
    	robotDrive.setInvertedMotor(MotorType.kRearLeft, true); 
	    robotDrive.setSafetyEnabled(true);
    }
    
    public void ArcadeDrive(Joystick joystick, double speed){
//    	Robot.navXSub.navx.reset(); // navx resets
    	robotDrive.arcadeDrive(joystick.getY(), joystick.getX());
    }
    
    public void ArcadeDriveStop(){
    	robotDrive.arcadeDrive(0, 0);
    }  
    
    public void move(double speed, double rotation){
    	robotDrive.arcadeDrive(speed, rotation);
    }
}