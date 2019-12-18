
package org.usfirst.frc.team772.robot;

import org.usfirst.frc.team772.robot.commands.DriveCommand;
import org.usfirst.frc.team772.robot.commands.ExampleCommand;
import org.usfirst.frc.team772.robot.commands.Vision;
import org.usfirst.frc.team772.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team772.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team772.robot.subsystems.FlywheelSubsystem;
import org.usfirst.frc.team772.robot.subsystems.TurretSubsystem;

import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.AxisCamera;

public class Robot extends IterativeRobot {

	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static final DriveSubsystem driveSubsystem = new DriveSubsystem();
	public static final FlywheelSubsystem flyhweelSubsystem = new FlywheelSubsystem();
	public static final TurretSubsystem turretSubsystem = new TurretSubsystem();
	public static OI oi;

	public DriveCommand drive = new DriveCommand();
	
    Command autonomousCommand;
    SendableChooser chooser;
    
    Compressor compressor = new Compressor(RobotMap.PCM);
    
    Thread visionThread;
    AxisCamera camera;
    Image frame = null;
    
    private NetworkTable data = NetworkTable.getTable("SmartDashboard");
    
    public void robotInit() {
		oi = new OI();
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", new ExampleCommand());
//        chooser.addObject("My Auto", new MyAutoCommand());
        SmartDashboard.putData("Auto mode", chooser);
        SmartDashboard.putNumber("DB/Slider 0", 0);
        SmartDashboard.putNumber("DB/Slider 1", 0);
        SmartDashboard.putNumber("DB/Slider 2", 0);
        SmartDashboard.putNumber("DB/Slider 3", 0);
        visionThread = new Thread(){
			public void run(){
				System.out.println("Starting vision");
				new Vision();
			}
		};
		visionThread.start();
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){
    	compressor.stop();
    }
	
	public void disabledPeriodic() {
		compressor.stop();
		for (double distance : data.getNumberArray("Distance", new double[0])) {
        	RobotMap.distance = distance;
        	System.out.println(RobotMap.distance);
        }
//		SmartDashboard.putString("DB/String 0", driveSubsystem.getEnc() + "");
		RobotMap.kP = SmartDashboard.getNumber("DB/Slider 0");
		RobotMap.kI = SmartDashboard.getNumber("DB/Slider 1");
		RobotMap.kD = SmartDashboard.getNumber("DB/Slider 2");
		
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
    	compressor.stop();
        autonomousCommand = (Command) chooser.getSelected();
        
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
    	compressor.start();
    	drive.start();
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control+
     */
    public void teleopPeriodic() {
//    	SmartDashboard.putString("DB/String 0", ""  + );
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
