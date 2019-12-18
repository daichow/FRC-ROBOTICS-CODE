package org.usfirst.frc.team772.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// PID
	public static double kP = 0.0007 / 2.2;
	public static double kI = (0.0007 / 2.2) / ((5/22) / 1.2);
	public static double kD = 0.000;
}
