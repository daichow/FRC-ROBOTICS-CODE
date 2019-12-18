package org.usfirst.frc.team772.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
    
   CANTalon FrontRight = new CANTalon(2);
   CANTalon FrontLeft = new CANTalon(4);
   CANTalon BackLeft = new CANTalon(3);
   CANTalon BackRight = new CANTalon(1);
   
   RobotDrive robotDrive;
   
   public DriveSubsystem(){
	   robotDrive = new RobotDrive(FrontLeft, BackLeft, FrontRight, BackRight);
		   
		   robotDrive.setInvertedMotor(MotorType.kFrontLeft, false);
		   robotDrive.setInvertedMotor(MotorType.kRearLeft, false);
		   robotDrive.setInvertedMotor(MotorType.kFrontRight, false);
		   robotDrive.setInvertedMotor(MotorType.kRearLeft, false);
		   
		   robotDrive.setSafetyEnabled(true);
		   
	   }
   public void drive (Joystick joystick,double speed){
	   robotDrive.arcadeDrive(joystick.getY()*speed, joystick.getX()*speed);
   }
   
   public void auto (double speed){
	   robotDrive.arcadeDrive(speed, speed);
   }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

