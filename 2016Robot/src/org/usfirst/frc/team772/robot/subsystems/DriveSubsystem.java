package org.usfirst.frc.team772.robot.subsystems;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class DriveSubsystem extends PIDSubsystem {
	
	RobotDrive robotDrive; // creates robotDrive
	CANTalon driveMotor1 = new CANTalon(RobotMap.driveL1); // creates drive motor 1
	CANTalon driveMotor2 = new CANTalon(RobotMap.driveL2); // creates drive motor 2
	CANTalon driveMotor3 = new CANTalon(RobotMap.driveR1); // creates drive motor 3
	CANTalon driveMotor4 = new CANTalon(RobotMap.driveR2); // creates drive motor 4
	
	public DriveSubsystem(){
		super("DriveSubsystem", -RobotMap.driveP, 0, 0); // mainly sets pid values
    	getPIDController().setContinuous(false); // sets continuous to false
		
		robotDrive = new RobotDrive(driveMotor4, driveMotor3, driveMotor2, driveMotor1); // assigns motors to drive
		
		// inverting drive motors
		robotDrive.setInvertedMotor(MotorType.kRearRight, true); 
		robotDrive.setInvertedMotor(MotorType.kFrontLeft, true); 
		robotDrive.setInvertedMotor(MotorType.kFrontRight, true);
		robotDrive.setInvertedMotor(MotorType.kRearLeft, true); 
		
		robotDrive.setSafetyEnabled(true); // enable safety
	}
	
	public void move(Joystick joystick, double driveSpeed, double rotSpeed){ // drives robot using joystick and speed parameters
		robotDrive.arcadeDrive(joystick.getY() * driveSpeed, -joystick.getX() * rotSpeed); // change speed
	}
	
	public void move(double speed, double rotation){ // drives robot using speed and rotation parameters
		robotDrive.arcadeDrive(speed, rotation);
	}

	public RobotDrive getDrive(){ // returns robot drive
		return robotDrive;
	}
	
	public int getLeftPosition(){ // returns left encoder
		return -driveMotor1.getEncPosition();
	}
	
	public int getRightPosition(){ // returns right encoder
		return -driveMotor3.getEncPosition();
	}
	
    public void initDefaultCommand() {
        
    }

	protected double returnPIDInput() { // pid input
		return Robot.getGoodAngle();
	}

	protected void usePIDOutput(double output) { // pid output
//		SmartDashboard.putString("DB/String 9", Robot.round(output, 3) + "");
		if (output > 0) {
			move(0, output + 0.3);
		} else if (output < 0) {
			move(0, output - 0.3);
		} else {
			move(0, 0);
		}
		
	}
	
	public void reset(){
		driveMotor1.setEncPosition(0);
		driveMotor2.setEncPosition(0);
		driveMotor3.setEncPosition(0);
		driveMotor4.setEncPosition(0);
	}
}