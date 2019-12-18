package org.usfirst.frc.team772.robot;

import org.usfirst.frc.team772.robot.commands.DriveCommand;
import org.usfirst.frc.team772.robot.commands.group.AutoCommand;
import org.usfirst.frc.team772.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team772.robot.subsystems.FlywheelSubsystem;
import org.usfirst.frc.team772.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team772.robot.subsystems.LidarSubsystem;
import org.usfirst.frc.team772.robot.subsystems.ShooterSubsystem;
import org.usfirst.frc.team772.robot.subsystems.TipperSubsystem;
import org.usfirst.frc.team772.robot.subsystems.TurretSubsystem;
import org.usfirst.frc.team772.robot.subsystems.UltrasonicSubsystem;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.AxisCamera;

public class Robot extends IterativeRobot {

	public static final DriveSubsystem driveSubsystem = new DriveSubsystem();
	public static final FlywheelSubsystem flywheelSubsystem = new FlywheelSubsystem();
	public static final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
	public static final LidarSubsystem lidarSubsystem = new LidarSubsystem(I2C.Port.kMXP);
	public static final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
	public static final TipperSubsystem tipperSubsystem = new TipperSubsystem();
	public static final TurretSubsystem turretSubsystem = new TurretSubsystem();
	public static final UltrasonicSubsystem ultrasonicSubsystem = new UltrasonicSubsystem();
	public static OI oi;
	public static final DriveCommand drive = new DriveCommand();
	
    Command autonomousCommand;
    
    AxisCamera camera;
    public static boolean isTracking = false;
    public static Image frame;
	public static Image binaryFrame = null;
    
    Relay ringLight = new Relay(0);
    Compressor compressor = new Compressor(RobotMap.PCM); 
    static Gyro gyro;
    
    public static int counter = 0;
    public static int camLim = 5;
    
	public static enum Direction{
		Forward,
		Reverse,
		Stop;
	}
	
	public static enum Mode{
	   	Auto,
	  	Manual,
	  	Variable;
	}
	
    public void robotInit() {
    	gyro = new AnalogGyro(1);
    	gyro.reset();
    	gyro.calibrate();
    	turretSubsystem.encoderReset();
        tipperSubsystem.resetEncPosition();
        tipperSubsystem.brakeOn();
        driveSubsystem.reset();
        lidarSubsystem.start();
       	
		oi = new OI();
		
		camera = new AxisCamera("10.7.72.11");
		frame = NIVision.imaqCreateImage(ImageType.IMAGE_HSL, 0); // create frame
        binaryFrame = NIVision.imaqCreateImage(ImageType.IMAGE_U8, 0); // create binaryFrame
		
		SmartDashboard.putNumber("DB/Slider 0", 0); // set slider 0 to 0
		SmartDashboard.putNumber("DB/Slider 1", 2); // set slider 1 to 0
        SmartDashboard.putNumber("DB/Slider 2", 4); // set slider 2 to 0
        SmartDashboard.putNumber("DB/Slider 3", 2); // set slider 3 to 1
		
        RobotMap.automode = (int)SmartDashboard.getNumber("DB/Slider 3");
        ringLight.set(Relay.Value.kReverse);
    }
	
    public void disabledInit(){
    	clearDashboard();
    	compressor.stop();
    	ringLight.set(Relay.Value.kReverse);
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		
		RobotMap.timeFlyOn = 0;
		RobotMap.automode = (int)SmartDashboard.getNumber("DB/Slider 3");
		
		ntPeriodic();		
		printPeriodic();
	}
    public void autonomousInit() {
    	tipperSubsystem.brakeOn();
    	gyro.reset();
    	ringLight.set(Relay.Value.kReverse);
		autonomousCommand = new AutoCommand(RobotMap.automode, (int)SmartDashboard.getNumber("DB/Slider 2"), (int)SmartDashboard.getNumber("DB/Slider 1"));
        if (autonomousCommand != null) autonomousCommand.start();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        
        if (counter > camLim) {
    		camera.getImage(frame);
	    	if (!isTracking) {
	    		CameraServer.getInstance().setImage(frame);
	    	}
	    	counter = 0;
    	} else {
    		counter++;
    	}
        
        ntPeriodic();
        printPeriodic();
    }

    public void teleopInit() {
    	drive.start();
    	compressor.start();
    	ringLight.set(Relay.Value.kReverse);
    	
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    public void teleopPeriodic() {
    	Scheduler.getInstance().run();
    	
    	if (counter > camLim) {
    		camera.getImage(frame);
	    	if (!isTracking) {
	    		CameraServer.getInstance().setImage(frame);
	    	}
	    	counter = 0;
    	} else {
    		counter++;
    	}
    	
    	
    	ntPeriodic();
        printPeriodic();
    }
    
    public void testPeriodic() {
        LiveWindow.run();
        
        printPeriodic();
    }
    
    public void printPeriodic() {
    	setDash(0, "Tipper: " + tipperSubsystem.getEncPosition()); // tipper position
    	setDash(1, "Turret: " + turretSubsystem.getEncPosition()); // turret position
    	setDash(2, "ShooterUS: " + round(ultrasonicSubsystem.getShooterUS(), 3)); // shooter US inches
    	setDash(3, "IntakeUS: " + round(ultrasonicSubsystem.getIntakeUS(), 3)); // intake US inches
    	if (RobotMap.timeFlyOn != 0) {
    		setDash(4, "FlyTime: " + (double)(System.currentTimeMillis() - RobotMap.timeFlyOn) / 1000); // flywheel ramp up time seconds
    	} else {
    		setDash(4, "Flywheel Off"); // flywheel off
    	}
		setDash(5, "Gyro: " + round(getGoodAngle(), 3)); // gyro angle degrees
    	setDash(6, "Lidar: " + round(lidarSubsystem.getDistance(), 3)); // lidar distance inches
//    	setDash(7, "Time Left: " + round(this.m_ds.getMatchTime(), 2));
    	setDash(7, "Counter: " + counter);
    	setDash(8, "CenterX: " + round(RobotMap.centerX, 3)); // centerX value pixels
    	setDash(9, "Auto: " + RobotMap.automode + " " + (int)SmartDashboard.getNumber("DB/Slider 2") + " " + (int)SmartDashboard.getNumber("DB/Slider 1")); // automode
    }
    
    public void ntPeriodic(){
    	RobotMap.encToX = Robot.turretSubsystem.getEncPosition() + RobotMap.centerX * RobotMap.XTConversion; // converts centerX to turret encoder position
    }
    
    public void setDash(int num, String value) {
    	SmartDashboard.putString("DB/String " + num, value);
    }
    
    public void setDash(int num, double value){
    	SmartDashboard.putString("DB/String " + num, "" + round(value, 3));
    }
    
    public void setDash(int num, int value){
    	SmartDashboard.putString("DB/String " + num, "" + value);
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
    
    public static double round(double num, int digits){
    	if(digits > 0)
    		return Math.round(num * Math.pow(10, digits)) / Math.pow(10, digits);
	 	else
	 		return num;
    }
    
    public static double getGoodAngle(){
    	if (gyro.getAngle() >= 0) {
    		return (gyro.getAngle() - (int)((gyro.getAngle() + 180) / 360) * 360);
    	} else { 
    		return (gyro.getAngle() - (int)((gyro.getAngle() - 180) / 360) * 360);
    	}
    }
    
    public static double min(double a, double b){
    	if (a < b) {
    		return a;
    	} else {
    		return b;
    	}
    }
    
    public static double max(double a, double b){
    	if (a > b) {
    		return a;
    	} else {
    		return b;
    	}
    }
}