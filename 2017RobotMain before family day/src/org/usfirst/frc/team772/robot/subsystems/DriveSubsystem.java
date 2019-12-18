package org.usfirst.frc.team772.robot.subsystems;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;


public class DriveSubsystem extends Subsystem {
	
	RobotDrive robotDrive;
	CANTalon backLeftDrive = new CANTalon(RobotMap.backLeftDrive);
	CANTalon frontLeftDrive = new CANTalon(RobotMap.frontLeftDrive);
	CANTalon backRightDrive = new CANTalon(RobotMap.backRightDrive);
	CANTalon frontRightDrive = new CANTalon(RobotMap.frontRightDrive);
	Spark ringLight = new Spark(1);
	public ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	DoubleSolenoid gearShift = new DoubleSolenoid(RobotMap.PCM, RobotMap.shotPinForward, RobotMap.shotPinReverse);
	
	public DriveSubsystem() {
		robotDrive = new RobotDrive(frontLeftDrive,backLeftDrive,frontRightDrive,backRightDrive);
		
		robotDrive.setInvertedMotor(MotorType.kFrontLeft, false);
		robotDrive.setInvertedMotor(MotorType.kFrontRight, false);
		robotDrive.setInvertedMotor(MotorType.kRearLeft, false);
		robotDrive.setInvertedMotor(MotorType.kRearRight, false);
		robotDrive.setSafetyEnabled(true);
		
		
	}
	
	public void move(Joystick joystick, double speed){
		robotDrive.arcadeDrive(joystick.getY()*speed, joystick.getX()*speed);
	}
	
	public void auto(double forwardSpeed, double rotationSpeed){
		robotDrive.arcadeDrive(forwardSpeed, rotationSpeed);
	}
	
	public double RightEncoder () {
		return frontRightDrive.getEncPosition()/7910;
	}
	
	public double LeftEncoder() {
		return -frontLeftDrive.getEncPosition()/7462;
	}
	
	public double encoderAverage(){
		double encoderAverage = (((frontRightDrive.getEncPosition() + (-frontLeftDrive.getEncPosition()))/2)/7541.2);
		return encoderAverage;
	}
	
	public double lengthTravelled(){
		double dinches = 0;
		double encoderAverage = (frontRightDrive.getEncPosition() + -frontLeftDrive.getEncPosition())/2;
		double travelledLenght = encoderAverage/dinches;
		return travelledLenght;
	}
	
	public void encoderReset(){
		frontLeftDrive.setEncPosition(0);
		frontRightDrive.setEncPosition(0);
	}
	
	public double gyroValue(){
		if (gyro.getAngle() > 360){
			return gyro.getAngle() -360;
		}else if(gyro.getAngle() < -360){
			return gyro.getAngle() +360;
		}else{
			return gyro.getAngle();
		}
//		return 0;
	}
    
	public void gyroReset(){
		gyro.reset();
	}
	
	public void ringLight(double speed){
		ringLight.set(speed);
	}
	
	public double centreToTarget(){
		if (Robot.haveCurrentTargets && Robot.sortCurrentTargets){
			return ((Robot.gearDegreeOffsettarget1+Robot.gearDegreeOffsettarget2)/2);
		}
		return 0;
	}
	
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

