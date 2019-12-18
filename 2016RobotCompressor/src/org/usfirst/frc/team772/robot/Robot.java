package org.usfirst.frc.team772.robot;

import org.usfirst.frc.team772.robot.commands.DriveCommand;
import org.usfirst.frc.team772.robot.commands.group.AutoCommand;
import org.usfirst.frc.team772.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team772.robot.subsystems.FlywheelSubsystem;
import org.usfirst.frc.team772.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team772.robot.subsystems.LidarSubsystem;
import org.usfirst.frc.team772.robot.subsystems.ScalingSubsystem;
import org.usfirst.frc.team772.robot.subsystems.ShooterSubsystem;
import org.usfirst.frc.team772.robot.subsystems.TipperSubsystem;
import org.usfirst.frc.team772.robot.subsystems.TurretSubsystem;
import org.usfirst.frc.team772.robot.subsystems.UltrasonicSubsystem;
import org.usfirst.frc.team772.robot.subsystems.VisionTurretSubsystem;
import org.usfirst.frc.team772.robot.subsystems.WinchSubsystem;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	public static final DriveSubsystem driveSubsystem = new DriveSubsystem();
	public static final FlywheelSubsystem flywheelSubsystem = new FlywheelSubsystem();
	public static final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
	public static final LidarSubsystem lidarSubsystem = new LidarSubsystem(I2C.Port.kMXP);
//	public static final NavxSubsystem navxSubsystem = new NavxSubsystem();
	public static final ScalingSubsystem scalingSubsystem = new ScalingSubsystem();
	public static final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
	public static final TipperSubsystem tipperSubsystem = new TipperSubsystem();
	public static final TurretSubsystem turretSubsystem = new TurretSubsystem();
	public static final UltrasonicSubsystem ultrasonicSubsystem = new UltrasonicSubsystem();
	public static final VisionTurretSubsystem visionTurretSubsystem = new VisionTurretSubsystem();
	public static final WinchSubsystem winchSubsystem = new WinchSubsystem();
	public static OI oi;
	public static final DriveCommand drive = new DriveCommand();
	
    Command autonomousCommand;
    public static Gyro gyro;
    Compressor compressor = new Compressor(RobotMap.PCM);
    
    public static double conversion = 113;
	
    public static AnalogInput colourS = new AnalogInput(0);
//    public static AnalogInput reflect = new AnalogInput(1);
    
    double[] pitch = new double[50];
	public static String tilt = "neutral";
    
	double[] flySpeed = new double[50];
	
	public static enum TurretPos{
    	LEFT, 
    	RIGHT,
    	CENTER,
    	LC,
    	RC,
    	CENTERX;
    }
	
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
//    	navxSubsystem.reset();
    	gyro.reset();
    	turretSubsystem.encoderReset();
        tipperSubsystem.resetEncPosition();
        tipperSubsystem.brakeOn();
        driveSubsystem.reset();
        lidarSubsystem.start();
        
		oi = new OI();
		
		SmartDashboard.putNumber("DB/Slider 0", 0); // set slider 0 to 0
		SmartDashboard.putNumber("DB/Slider 1", 2); // set slider 1 to 0
        SmartDashboard.putNumber("DB/Slider 2", 4); // set slider 2 to 0
        SmartDashboard.putNumber("DB/Slider 3", 2); // set slider 3 to 1
		
        RobotMap.automode = (int)SmartDashboard.getNumber("DB/Slider 3");
        
		for(int i = 0; i < 50; i++){ // resets pitch array
			pitch[i] = 0;
		}
		for(int i = 0; i < 50; i++){ // resets flySpeed array
			flySpeed[i] = 0;
		}
    }
	
    public void disabledInit(){
    	clearDashboard();
    	compressor.stop();
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		
		RobotMap.automode = (int)SmartDashboard.getNumber("DB/Slider 3");
		
//		readPitch();
//		setTilt();
		printPeriodic();
	}
    public void autonomousInit() {
    	tipperSubsystem.brakeOn();
    	gyro.reset();
		autonomousCommand = new AutoCommand(RobotMap.automode, (int)SmartDashboard.getNumber("DB/Slider 2"), (int)SmartDashboard.getNumber("DB/Slider 1"));
        if (autonomousCommand != null) autonomousCommand.start();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        
//        readPitch();
//		setTilt();
        printPeriodic();
        
    }

    public void teleopInit() {
    	drive.start();
    	compressor.start();
    	
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        readFlySpeed();
//        readPitch();
//		setTilt();
        printPeriodic();
    }
    
    public void testPeriodic() {
        LiveWindow.run();
        
        printPeriodic();
    }
    
//    public void readPitch(){
//    	for(int i = 49; i > 0; i--){ // shifts array to the right
//			pitch[i] = pitch[i - 1];
//		}
//		pitch[0] = round(navxSubsystem.getPitch(), 2);
//    }
    
//    public void setTilt(){
//    	if (pitch[49] - 10 > pitch[0]) {
//			tilt = "up";
//		} else if (pitch[49] < pitch[0] - 10) {
//			tilt = "down";
//		} else if (!(pitch[49] - 35 > pitch[0] && pitch[49] < pitch[0] - 35)) {
//			tilt = "neutralAfter";
//		} else {
//			tilt = "neutral";
//		}
//    }
    
    public void readFlySpeed(){
    	for(int i = 49; i > 0; i--){ // shifts array to the right
    		flySpeed[i] = flySpeed[i - 1];
    	}
    	flySpeed[0] = round(flywheelSubsystem.getFlyEnc(), 2);
    }
    
    public double averageFlySpeed(){
    	double average = 0;
    	for (int i = 0; i < 50; i++) {
    		average += flySpeed[i];
    	}
    	return average / 50;
    }
    
    public void printPeriodic() {
    	setDash(0, "Tipper: " + tipperSubsystem.getEncPosition()); // tipper position
    	setDash(1, "Turret: " + turretSubsystem.getEncPosition()); // turret position
    	setDash(2, "ShooterUS: " + round(ultrasonicSubsystem.getShooterUS(), 3)); // shooter US
    	setDash(3, "IntakeUS: " + round(ultrasonicSubsystem.getIntakeUS(), 3)); // intake US
    	setDash(4, "DistanceUS: " + round(ultrasonicSubsystem.getDistanceUS(), 3));
		setDash(5, "Gyro: " + round(getGoodAngle(), 3));
    	setDash(6, "Lidar: " + round(lidarSubsystem.getDistance(), 3));
//    	setDash(7, "Distance: " + round(RobotMap.distance, 3));
    	setDash(7, "AvgFlySpeed: " + round(averageFlySpeed(), 2));
    	setDash(8, "CenterX: " + round(RobotMap.centerX, 3)); // centerX
    	setDash(9, "Auto: " + RobotMap.automode + " " + (int)SmartDashboard.getNumber("DB/Slider 2") + " " + (int)SmartDashboard.getNumber("DB/Slider 1")); // automode
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
}