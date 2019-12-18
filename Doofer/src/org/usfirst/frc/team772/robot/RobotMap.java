package org.usfirst.frc.team772.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// Motor Ports
		public static int driveR1 = 1;
		public static int driveR2 = 2;
		public static int driveL1 = 3;	
		public static int driveL2 = 4;
		public static int winchMotor = 5;
		public static int tipperMotor = 6;
		public static int flywheelMotor = 7;
		public static int turretMotor = 8;
		public static int intakeMotor = 9;
		
		// pneumatic
		public static int PCM = 25;
		public static int scalingArms1 = 1;
		public static int scalingArms2 = 0;
		public static int brake1 = 2; //poo1 on robot, set numbers
		public static int brake2 = 3;
		public static int shooter = 7;
			
		//Ultrasonic Ports
		public static int intakeUltra1 = 1;
		public static int intakeUltra2 = 0;	
		public static int shooterUltra1 = 2;
		public static int shooterUltra2 = 3;
		
		// speeds
		public static double tipperUpSpeed = -0.6;
		public static double tipperDownSpeed = 0.4;
		public static double flywheelSpit = -0.6;
		public static double flywheelFSpeed = -1;
		public static double flywheelRSpeed = 1; // used in ReverseIntakeCommand
		public static double flywheelShoot = -1;	
		public static double intakeTopInSpeed = -.4;
		public static double intakeOutTopSpeed = 0.4;
		public static double intakeBottomInSpeed = -0.3;
		public static double intakeOutSpeed = 1;
		public static double reverseIntakeSpeed = .3; // used in ReverseIntakeCommand
		public static double winchFSpeed = -1;	
		public static double winchRSpeed = 1;
		
		// PID
		public static double kP = 0.0007 / 2.2;
		public static double kI = 0/*(0.0007 / 2.2) / ((5/22) / 1.2)*/;
		public static double kD = 0.000;
}
