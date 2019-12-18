package org.usfirst.frc.team772.robot.subsystems;

import java.awt.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
    
   com.ctre.CANTalon FrontRight = new com.ctre.CANTalon(1);
   com.ctre.CANTalon BackRight = new com.ctre.CANTalon(2);
   com.ctre.CANTalon FrontLeft = new com.ctre.CANTalon(3);
   com.ctre.CANTalon BackLeft = new com.ctre.CANTalon(4);
   Relay Underglow = new Relay (2);

   RobotDrive robotDrive;
   
   public DriveSubsystem(){
   robotDrive = new RobotDrive(FrontRight, BackRight, FrontLeft, BackLeft);
	   
	   robotDrive.setInvertedMotor(MotorType.kFrontLeft, false);
	   robotDrive.setInvertedMotor(MotorType.kRearLeft, false);
	   robotDrive.setInvertedMotor(MotorType.kFrontRight, false);
	   robotDrive.setInvertedMotor(MotorType.kRearLeft, false);
	   
	   robotDrive.setSafetyEnabled(true);
	   
   }
   
   public void move(Joystick joystick, double speed){
	   robotDrive.arcadeDrive(joystick.getY()*-speed, joystick.getX()*-speed); 
   }
   public void automove(double speed, double rotspeed){
	   robotDrive.arcadeDrive(-speed, -rotspeed);
   }
   
   public void lighton (){
	   Underglow.set(Relay.Value.kForward);
   }
   
   public void lightdown (){
	   Underglow.set(Relay.Value.kOff);
   }
   public double encoder(){
	   return FrontRight.getEncPosition();
   }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

