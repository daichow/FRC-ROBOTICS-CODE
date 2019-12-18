
package org.usfirst.frc.team772.robot;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Stack;

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
import org.usfirst.frc.team772.robot.commands.AutoDriveCommand;
import org.usfirst.frc.team772.robot.commands.DriveCommand;
import org.usfirst.frc.team772.robot.commands.WaitCommand;
import org.usfirst.frc.team772.robot.commands.group.AutonomousCommand;
import org.usfirst.frc.team772.robot.commands.visionGear.OpenGearSocketCommand;
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
	
	//Command Objects
	public static final DriveCommand drive = new DriveCommand(); 
	public static final OpenGearSocketCommand socket= new OpenGearSocketCommand();
	
	public static final  Ultrasonic ultrasonicFront = new Ultrasonic(0,1);
	public static final  Ultrasonic ultrasonicBack = new Ultrasonic(4,5);
	public static OI oi;
	
	//visionVariables
	public static ArrayList<Target> currentTargets;
	public static double DegreeOffsettarget1 = 0;
	public static double DegreeOffsettarget2 = 0;
	public static boolean haveCurrentTargetsGear = false;
	public static boolean haveCurrentTargetsBoiler = false;
	public static boolean sortCurrentTargets = false;
	//vision Socket Stuff
	public static ObjectOutputStream outtBoiler;
	public static ObjectInputStream innBoiler;
	public static Socket echoSocketBoiler;
	public static ObjectOutputStream outtGear;
	public static ObjectInputStream innGear;
	public static Socket echoSocketGear;
	
	public static double light = 0;

//	static PrintWriter output;
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
		driveSubsystem.encoderReset();
		driveSubsystem.gyroReset();
		SmartDashboard.putData("Auto mode", chooser);
		
		ultrasonicFront.setEnabled(true);
		ultrasonicFront.setAutomaticMode(true);
		ultrasonicBack.setEnabled(true);
		ultrasonicBack.setAutomaticMode(true);
		clearDashboard();
	}

	/**
	 * This function is called once Bach time the robot enters Disabled mode.
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
		SmartDashboard.putString("DB/String 0", "SPIGyro: "+driveSubsystem.gyroValue());
		SmartDashboard.putString("DB/String 2", "UltraFront: "+ultrasonicFront.getRangeInches());
		SmartDashboard.putString("DB/String 3", "UltraBack: "+ultrasonicBack.getRangeInches());
		SmartDashboard.putString("DB/String 1", "EncoderAve: "+driveSubsystem.encoderAverage());
//		connectToGearPI();
		connectToBoilerPI();
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
		
		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
		
		clearDashboard();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putString("DB/String 0", "SPIGyro: "+driveSubsystem.gyroValue());
		SmartDashboard.putString("DB/String 1", "EncoderAve: "+driveSubsystem.encoderAverage());
		SmartDashboard.putString("DB/String 2", "Ultra: "+ultrasonicFront.getRangeInches());
		SmartDashboard.putString("DB/String 3", "Ultra: "+ultrasonicBack.getRangeInches());
		if(haveCurrentTargetsGear){
			SmartDashboard.putString("DB/String 5", "GearOffset1: "+DegreeOffsettarget1);
			SmartDashboard.putString("DB/String 6", "GearOffset2: "+DegreeOffsettarget2);
			SmartDashboard.putString("DB/String 7", "AverageGearOffset: "+RobotMap.averageDegreeOffset);
		}else if(haveCurrentTargetsBoiler){
			SmartDashboard.putString("DB/String 5", "BoilOffset1: "+DegreeOffsettarget1);
			SmartDashboard.putString("DB/String 6", "BoilOffset2: "+DegreeOffsettarget2);
			SmartDashboard.putString("DB/String 7", "AverageGearOffset: "+RobotMap.averageDegreeOffset);
		}else{
			SmartDashboard.putString("DB/String 5", "");
			SmartDashboard.putString("DB/String 6", "");
			SmartDashboard.putString("DB/String 7", "");
		}
	}

	@Override
	public void teleopInit() {
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		
		drive.start();
		compressor.start();
		clearDashboard();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		Scheduler.getInstance().run();
		SmartDashboard.putString("DB/String 0", "SPIGyro: "+driveSubsystem.gyroValue());
		SmartDashboard.putString("DB/String 1", "EncoderAve: "+driveSubsystem.encoderAverage());
//		SmartDashboard.putString("DB/String 2", "LazerBeam: "+envelopeSubsystem.gearCheck());
//		System.out.println(shooterSubsystem.flywheelEnc());
		SmartDashboard.putString("DB/String 4", "Flywheel: "+ shooterSubsystem.flywheelEnc());
		SmartDashboard.putString("DB/String 2", "UltraFront: "+ultrasonicFront.getRangeInches());
		SmartDashboard.putString("DB/String 3", "UltraBack: "+ultrasonicBack.getRangeInches());
		SmartDashboard.putString("DB/String 5", "RightEnc: "+driveSubsystem.RightEncoder());
		SmartDashboard.putString("DB/String 6", "LeftEnc: "+driveSubsystem.LeftEncoder());
//		try {
//			output = new PrintWriter("encoderOutput.txt");
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		output.println(shooterSubsystem.flywheelEnc());
//		if(haveCurrentTargetsGear){
//			SmartDashboard.putString("DB/String 5", "GearOffset1: "+DegreeOffsettarget1);
//			SmartDashboard.putString("DB/String 6", "GearOffset2: "+DegreeOffsettarget2);
//			SmartDashboard.putString("DB/String 7", "AverageGearOffset: "+RobotMap.averageDegreeOffset);
//			SmartDashboard.putString("DB/String 4", "Aquired Targets");
//		}else if(haveCurrentTargetsBoiler){
//			SmartDashboard.putString("DB/String 5", "BoilOffset1: "+DegreeOffsettarget1);
//			SmartDashboard.putString("DB/String 6", "BoilOffset2: "+DegreeOffsettarget2);
//			SmartDashboard.putString("DB/String 7", "AverageBoilOffset: "+RobotMap.averageDegreeOffset);
//			SmartDashboard.putString("DB/String 4", "Aquired Targets");
//		}else{
//			SmartDashboard.putString("DB/String 4", "No targets Found");
//			SmartDashboard.putString("DB/String 5", "");
//			SmartDashboard.putString("DB/String 6", "");
//			SmartDashboard.putString("DB/String 7", "");
//		}
		
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
	
	public void clearDashboard(){
    	SmartDashboard.putString("DB/String 0", "");
    	SmartDashboard.putString("DB/String 1", "");
    	SmartDashboard.putString("DB/String 2", "");
    	SmartDashboard.putString("DB/String 3", "");
    	SmartDashboard.putString("DB/String 4", "");
    	SmartDashboard.putString("DB/String 5", "");
    	SmartDashboard.putString("DB/String 6", "");
    	SmartDashboard.putString("DB/String 7", "");
    	SmartDashboard.putString("DB/String 8", "");
    	SmartDashboard.putString("DB/String 9", "");
    }
	
	public void connectToBoilerPI(){
		if (!RobotMap.connectedToBoilerPI) {
			try {
				if (InetAddress.getByName("10.7.72.10").isReachable(25)) {//If this is is reachable within 25 ms
					SmartDashboard.putString("DB/String 9","Connecting To BoilerPI");
					try {
						Robot.echoSocketBoiler = new Socket("10.7.72.10", 5803);
						Robot.outtBoiler = new ObjectOutputStream(Robot.echoSocketBoiler.getOutputStream());
						Robot.innBoiler = new ObjectInputStream(Robot.echoSocketBoiler.getInputStream());
						SmartDashboard.putString("DB/String 9","Connected To BoilerPI");
						RobotMap.connectedToBoilerPI = true;
					} catch (IOException e) {
						SmartDashboard.putString("DB/String 9",	"Not Connected To BoilerPI");
						RobotMap.connectedToBoilerPI = false;
					}
				}
			} catch (IOException e) {
			}
		}
	}
	
	public void connectToGearPI(){
		if (!RobotMap.connectedToGearPI) {
			try {
				if (InetAddress.getByName("10.7.72.9").isReachable(25)) {
					SmartDashboard.putString("DB/String 8","Connecting To GearPI");
					try {
						Robot.echoSocketGear = new Socket("10.7.72.9", 5803);
						Robot.outtGear = new ObjectOutputStream(Robot.echoSocketGear.getOutputStream());
						Robot.innGear = new ObjectInputStream(Robot.echoSocketGear.getInputStream());
						SmartDashboard.putString("DB/String 8","Connected To GearPI");
						RobotMap.connectedToGearPI = true;
					} catch (IOException e) {
						SmartDashboard.putString("DB/String 8","Not Connected To GearPI");
						RobotMap.connectedToGearPI = false;
					}
				}
			} catch (IOException e) {
			}
		}
	}
	
}
