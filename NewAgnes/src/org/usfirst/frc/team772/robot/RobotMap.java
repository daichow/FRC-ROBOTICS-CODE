package org.usfirst.frc.team772.robot;

public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
	
	// motors
	public static int frontrightmotor = 4;
	public static int frontleftmotor = 1;
	public static int rearrightmotor = 3;
	public static int rearleftmotor = 2;
	public static int flywheel = 5; 
	
	// speed variables
	public static double flywheelSpeed1 = 1; // one direction
	public static double flywheelSpeed2 = -1; // other direction
	public static double flywheelStop = 0;
	
	// actuator variables
	public static int PCM = 25;

	
	//public static double intake1 = 1;
	//public static double intake2 = 2;
	public static boolean intake = true;
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
}
