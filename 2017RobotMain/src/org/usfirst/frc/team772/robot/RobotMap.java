package org.usfirst.frc.team772.robot;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.Duration;
import java.util.ArrayList;

import org.echoClasses.Target;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static double averageDegreeOffset = 0;
	
	//motors
	public static int backLeftDrive = 1;
	public static int frontLeftDrive = 2;
	public static int backRightDrive = 3;
	public static int frontRightDrive = 4;
	public static int flywheelLeft = 5;
	public static int flywheelRight = 6;
	public static int feeder = 7;
	public static int winch = 8;
	public static int intakeConvayor = 9;
	public static int intake = 10;
	public static int windowMotor1 = 11;
	public static int windowMotor2 = 12;
	
	//motorSpeeds
	public static double intakeInSpeed = 1;//1
	public static double intakeOutSpeed = -1;//-1
	public static double flywheelSpeed = 0.75; //from wall 67, auto position1: 0.8
	public static double feederForwardSpeed = -1.0;//positive
	public static double feederBackwardSpeed = 1.0;
	public static double winchSpeed = -0.75;
	public static int driveSpeed = 1;
	public static int convayorSpeed = 1;//
	public static double windowMotorSpeed = 1;
	
	//pneumatics
	public static int PCM = 25;
	public static int shotPinForward = 1;
	public static int shotPinReverse = 0;
	public static int envelopeOut = 3;
	public static int envelopeIn = 2;
	
	//PID valuesturn
	public static double kP = 0.05;
	
	//vision
	public static String orangePi = "10.7.72.9";
	public static int orangePiPort = 5803;
	public static boolean connectedToGearPI = false;
	public static String orangePiBoiler = "10.7.72.10";
	public static int orangePiPortBoiler = 5803;
	public static boolean connectedToBoilerPI = false;
		
}
