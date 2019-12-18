package org.usfirst.frc.team772.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//speed variables
	// Speeds
		public static double tipperUpSpeed = -0.6;
		public static double tipperDownSpeed = 0.4;
		
		public static double flywheelSpit = -0.4;
		public static double flywheelFSpeed = -1;
		public static double flywheelRSpeed = 1; // used in ReverseIntakeCommand
		public static double flywheelShoot = -1;
		
		public static double intakeTopInSpeed = -.4;
		public static double intakeOutTopSpeed = 0.4;
		public static double intakeBottomInSpeed = -0.45;
		public static double intakeOutSpeed = 1;
		public static double reverseIntakeSpeed = .3; // used in ReverseIntakeCommand
	
	//Pnumatics
	public static int PCM = 25;
	
	// PID
		public static double kP = 0.0002;
		public static double kI = 0.000;
		public static double kD = 0.000;
	
	
}
