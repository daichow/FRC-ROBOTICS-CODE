package org.usfirst.frc.team772.robot.subsystems;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

//calls motors, solenoids, and gyros for drive subsystem in robot
public class DriveSubsystem extends Subsystem {
	RobotDrive robotDrive;
	CANTalon backLeftDrive = new CANTalon(RobotMap.backLeftDrive);
	CANTalon frontLeftDrive = new CANTalon(RobotMap.frontLeftDrive);
	CANTalon backRightDrive = new CANTalon(RobotMap.backRightDrive);
	CANTalon frontRightDrive = new CANTalon(RobotMap.frontRightDrive);
	Spark ringLight = new Spark(1);
	public ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	DoubleSolenoid gearShift = new DoubleSolenoid(RobotMap.PCM, RobotMap.shotPinForward, RobotMap.shotPinReverse);
	
	//Determines the directions of the motors and also allows for the inversion of the motors to be set
	public DriveSubsystem() {
		
		robotDrive = new RobotDrive(frontLeftDrive,backLeftDrive,frontRightDrive,backRightDrive);
		
		robotDrive.setInvertedMotor(MotorType.kFrontLeft, true);
		robotDrive.setInvertedMotor(MotorType.kFrontRight, true);
		robotDrive.setInvertedMotor(MotorType.kRearLeft, true);
		robotDrive.setInvertedMotor(MotorType.kRearRight, true);
		robotDrive.setSafetyEnabled(true);
		
		
	}
	
	//assigns motor controls in the controller
	public void move(Joystick joystick, double speed){
		
		robotDrive.arcadeDrive(joystick.getY()*speed, joystick.getX()*speed);
	}
	
	//forward speed and rotation speed in autonomous
	public void auto(double forwardSpeed, double rotationSpeed){
	
		robotDrive.arcadeDrive(forwardSpeed, rotationSpeed);
	}
	
	//gets the right encoder  value
	public double RightEncoder () {
		return frontRightDrive.getEncPosition();
	}
	
	//gets the left encoder value
	public double LeftEncoder() {
		return -frontLeftDrive.getEncPosition();
	}
	
	//converts the average of the encoder values into feet
	public double encoderAverage(){
		double encoderAverage = (((frontRightDrive.getEncPosition() + (-frontLeftDrive.getEncPosition()))/2)/4327.834);
		return encoderAverage;
	}
	
	public double lengthTravelled(){
		double dinches = 0;
		double encoderAverage = (frontRightDrive.getEncPosition() + -frontLeftDrive.getEncPosition())/2;
		double travelledLenght = encoderAverage/dinches;
		return travelledLenght;
	}
	
	//resets encoder
	public void encoderReset(){
		frontLeftDrive.setEncPosition(0);
		frontRightDrive.setEncPosition(0);
	}
	
	//determines the angle of the robot with the gyro
	public double gyroValue(){
		if (gyro.getAngle() > 360){
			return gyro.getAngle() -360;
		}else if(gyro.getAngle() < -360){
			return gyro.getAngle() +360;
		}else{
			return gyro.getAngle();
		}
	}
    
	//resets the gyro value to zero
	public void gyroReset(){
		gyro.reset();
	}
	
	//sets the brightness of the ring light
	public void ringLight(double speed){
		ringLight.set(speed);
	}
	
	//shifts the gear
	public void gearShift1(){
		gearShift.set(DoubleSolenoid.Value.kForward);
	}
	
	public void gearShift2(){
		gearShift.set(DoubleSolenoid.Value.kReverse);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

