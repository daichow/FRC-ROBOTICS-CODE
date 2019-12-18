package org.usfirst.frc.team772.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//speed variables
	public static double driveSpeed = -1;
	public static double intakeInSpeed = 1;
	public static double intakeOutSpeed = -1;
	public static double convayorInSpeed = .5;
	public static double convayorOutSpeed = -1;
	public static double flywheelSpeed = -1;
	
	//Pnumatics
	public static int PCM = 25;
}
