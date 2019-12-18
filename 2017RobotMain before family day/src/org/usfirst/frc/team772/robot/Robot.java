
package org.usfirst.frc.team772.robot;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Ultrasonic.Unit;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.echoClasses.Target;
import org.usfirst.frc.team772.robot.commands.DriveCommand;
import org.usfirst.frc.team772.robot.commands.WaitCommand;
import org.usfirst.frc.team772.robot.commands.group.AutonomousCommand;
import org.usfirst.frc.team772.robot.commands.group.TrackCommand;
import org.usfirst.frc.team772.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team772.robot.subsystems.EnvelopeSubsystem;
import org.usfirst.frc.team772.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team772.robot.subsystems.IntakeSubsystem;
//import org.usfirst.frc.team772.robot.subsystems.LidarSubsystem;
import org.usfirst.frc.team772.robot.subsystems.LifterSubsystem;
import org.usfirst.frc.team772.robot.subsystems.ShooterSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static final DriveSubsystem driveSubsystem = new DriveSubsystem();
	public static final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
	public static final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
	public static final LifterSubsystem lifterSubsystem = new LifterSubsystem();
	public static final EnvelopeSubsystem envelopeSubsystem = new EnvelopeSubsystem();
//	public static final LidarSubsystem lidarSubsystem = new LidarSubsystem(I2C.Port.kMXP);
	public static final DriveCommand drive = new DriveCommand(); 
	public static OI oi;
	public static double light = 0;
	//vision
	public static ArrayList<Target> currentTargets;
	public static double gearDegreeOffsettarget1 = 0;
	public static double gearDegreeOffsettarget2 = 0;
	public static double gearCenterYtarget1 = 0;
	public static double gearCenterXtarget1 = 0;
	public static double gearContourAreaTarget1 = 0;
	public static double gearContourAreaTarget2 = 0;
	public static double gearRectAreaTarget1 = 0;
	public static double gearRectAreaTarget2 = 0;
	
	public static final  Ultrasonic ultrasonic = new Ultrasonic(0,1);
	
	
	public static boolean haveCurrentTargets = false;
	public static boolean sortCurrentTargets = false;
	public static boolean lazer;
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	Compressor compressor = new Compressor(RobotMap.PCM);
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
       
		oi = new OI();
		chooser.addDefault("Default Auto", new WaitCommand(1.5));
//		driveSubsystem.gyroReset();
		driveSubsystem.encoderReset();
		driveSubsystem.gyroReset();
//		lidarSubsystem.start();
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);
		ultrasonic.setEnabled(true);
		ultrasonic.setAutomaticMode(true);
		
//		CameraServer.getInstance().startAutomaticCapture();
//		ultrasonic.setEnabled(true);
//        ultrasonic.setAutomaticMode(true);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
//		light = SmartDashboard.getNumber("DB/Slider 3");
//		Robot.driveSubsystem.ringLight(light);
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();
		autonomousCommand = new AutonomousCommand( (int)SmartDashboard.getNumber("DB/Slider 0"), (int)SmartDashboard.getNumber("DB/Slider 1"), (int)SmartDashboard.getNumber("DB/Slider 2"));
//		autonomousCommand = new TrackCommand();
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putString("DB/String 0", "SPIGyro: "+driveSubsystem.gyroValue());
		SmartDashboard.putString("DB/String 1", "EncoderAve: "+driveSubsystem.encoderAverage());
		SmartDashboard.putString("DB/String 2", "LazerBeam: "+envelopeSubsystem.gearCheck());
//		SmartDashboard.putString("DB/String 3", "Lazer: "+lazer);
		SmartDashboard.putString("DB/String 4", "Ultra: "+ultrasonic.getRangeInches());
		if(haveCurrentTargets){
			SmartDashboard.putString("DB/String 6", "Right: "+gearDegreeOffsettarget1);
			SmartDashboard.putString("DB/String 7", "Right: "+gearDegreeOffsettarget2);
			SmartDashboard.putString("DB/String 8", "average: "+RobotMap.averageDegreeOffset );
			SmartDashboard.putString("DB/String 5", "Aquired Targets");
		}else{
			SmartDashboard.putString("DB/String 5", "No targets Found");
		}
//		lazer = SmartDashboard.getBoolean("DB/Button 1");
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		drive.start();
		compressor.start();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putString("DB/String 0", "LeftEnc: "+driveSubsystem.LeftEncoder());
		SmartDashboard.putString("DB/String 1", "RightEnc: "+driveSubsystem.RightEncoder());
		SmartDashboard.putString("DB/String 2", "average: "+driveSubsystem.encoderAverage());
//		SmartDashboard.putString("DB/String 0", "SPIGyro: "+driveSubsystem.gyroValue());
//		SmartDashboard.putString("DB/String 1", "EncoderAve: "+driveSubsystem.encoderAverage());
//		SmartDashboard.putString("DB/String 2", "LazerBeam: "+envelopeSubsystem.gearCheck());
//		SmartDashboard.putString("DB/String 3", "Lazer: "+lazer);
//		SmartDashboard.putString("DB/String 4", "Ultra: "+ultrasonic.getRangeInches());
//		if(haveCurrentTargets){
//			SmartDashboard.putString("DB/String 6", "Right: "+gearDegreeOffsettarget1);
//			SmartDashboard.putString("DB/String 7", "Right: "+gearDegreeOffsettarget2);
//			SmartDashboard.putString("DB/String 8", "average: "+RobotMap.averageDegreeOffset );
//			SmartDashboard.putString("DB/String 5", "Aquired Targets");
//		}else{
//			SmartDashboard.putString("DB/String 5", "No targets Found");
//		}
//		lazer = SmartDashboard.getBoolean("DB/Button 1");
		
		 light = SmartDashboard.getNumber("DB/Slider 3");
		 driveSubsystem.ringLight(light);
	}
	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
