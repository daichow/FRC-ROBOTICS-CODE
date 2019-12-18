package org.usfirst.frc.team772.robot;

import org.usfirst.frc.team772.robot.commands.DriveCommand;
import org.usfirst.frc.team772.robot.commands.group.AutoCommand;
import org.usfirst.frc.team772.robot.subsystems.ArmSubsystem;
import org.usfirst.frc.team772.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team772.robot.subsystems.FlywheelSubsystem;
import org.usfirst.frc.team772.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team772.robot.subsystems.LidarSubsystem;
import org.usfirst.frc.team772.robot.subsystems.NavxSubsystem;
import org.usfirst.frc.team772.robot.subsystems.ScalingSubsystem;
import org.usfirst.frc.team772.robot.subsystems.ShooterSubsystem;
import org.usfirst.frc.team772.robot.subsystems.TipperSubsystem;
import org.usfirst.frc.team772.robot.subsystems.TurretSubsystem;
import org.usfirst.frc.team772.robot.subsystems.VisionTurretSubsystem;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	public static final ArmSubsystem armSubsystem = new ArmSubsystem();
	public static final DriveSubsystem driveSubsystem = new DriveSubsystem();
	public static final FlywheelSubsystem flywheelSubsystem = new FlywheelSubsystem();
	public static final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
	public static final LidarSubsystem lidarSubsystem = new LidarSubsystem(I2C.Port.kMXP);
	public static final NavxSubsystem navxSubsystem = new NavxSubsystem();
	public static final ScalingSubsystem scalingSubsystem = new ScalingSubsystem();
	public static final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
	public static final TipperSubsystem tipperSubsystem = new TipperSubsystem();
	public static final TurretSubsystem turretSubsystem = new TurretSubsystem();
	public static final VisionTurretSubsystem visionTurretSubsystem = new VisionTurretSubsystem();
	public static OI oi;
	public static final DriveCommand drive = new DriveCommand();
	
    Command autonomousCommand;

    Thread visionThread;
    
    Compressor compressor = new Compressor(RobotMap.PCM);
    
    public static AnalogInput colourS = new AnalogInput(0);
    public static AnalogInput reflect = new AnalogInput(3);
    
    double[] pitch = new double[50];
	public static String tilt = "neutral";
    
	public static enum TurretPos { //selects which turret position you want
    	LEFT, 
    	RIGHT,
    	CENTER,
    	LC,
    	RC,
    	CENTERX;
    }
	
	public static enum Direction { //selects which direction you want to move for certain command
		Forward,
		Reverse,
		Stop;
	}
	
	public static enum Mode { //selects which mode you want for certain command
	   	Auto,
	  	Manual,
	  	Variable;
	}
	
    public void robotInit() { //runs on startup
    	//encoder resets
    	navxSubsystem.reset();
    	turretSubsystem.encoderReset();
        tipperSubsystem.resetEncPosition();
        lidarSubsystem.start();
        tipperSubsystem.brakeOn();//turns on tipper break
        
		oi = new OI(); 

		SmartDashboard.putNumber("DB/Slider 0", 0); // set slider 0 to 0
		SmartDashboard.putNumber("DB/Slider 1", 0); // set slider 1 to 0
        SmartDashboard.putNumber("DB/Slider 2", 0); // set slider 2 to 0
        SmartDashboard.putNumber("DB/Slider 3", 2); // set slider 3 to 1
		
        RobotMap.automode = (int)SmartDashboard.getNumber("DB/Slider 3");
        
		for (int i = 0; i < 50; i++) { // resets pitch array
			pitch[i] = 0;
		}
		
		visionThread = new Thread() { // thread for vision
			public void run() {
				System.out.println("Starting vision");
				new Vision();
			}
		};
		visionThread.start(); //starts the vision thread
    }
	
    public void disabledInit() {
    	clearDashboard();
    	compressor.stop(); //stops compressor
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		
		RobotMap.automode = (int)SmartDashboard.getNumber("DB/Slider 3");
		
		readPitch(); 
		setTilt();
		printPeriodic();
	}
	
    public void autonomousInit() {
    	tipperSubsystem.brakeOn(); //turns break on
		autonomousCommand = new AutoCommand(RobotMap.automode); //starts autonomous mode
        if (autonomousCommand != null) autonomousCommand.start();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        
        readPitch();
		setTilt();
        printPeriodic();
    }

    public void teleopInit() {
    	drive.start(); //drive start; allows drivers to start driving
    	compressor.start(); //starts compressor if one is attached
    	
        if (autonomousCommand != null) autonomousCommand.cancel(); //stops auto mode
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        
        readPitch();
		setTilt();
        printPeriodic();
    }
    
    public void testPeriodic() {
        LiveWindow.run();
        
        printPeriodic();
    }
    
    public void readPitch(){
    	for (int i = 49; i > 0; i--) { // shifts array to the right
			pitch[i] = pitch[i - 1];
		}
		pitch[0] = round(navxSubsystem.getPitch(), 2); 
    }
    
    public void setTilt(){
    	if (pitch[49] - 10 > pitch[0]) {
			tilt = "up";
		} else if (pitch[49] < pitch[0] - 10) {
			tilt = "down";
		} else if (!(pitch[49] - 35 > pitch[0] && pitch[49] < pitch[0] - 35)) {
			tilt = "neutralAfter";
		} else {
			tilt = "neutral";
		}
    }
    
    public void printPeriodic(){ //prints on to dashboard
    	setDash(0, "Tipper: " + tipperSubsystem.getEncPosition()); // tipper position
    	setDash(1, "Turret: " + turretSubsystem.getEncPosition()); // turret position
    	setDash(2, "CenterX: " + round(RobotMap.centerX, 3)); // centerX
    	setDash(3, "Angle: " + round(navxSubsystem.getAngle(), 3)); // gyro angle
    	setDash(4, "ShooterUS: " + round(intakeSubsystem.getShooterUS(), 3)); // shooter US
    	setDash(5, "IntakeUS: " + round(intakeSubsystem.getIntakeUS(), 3)); // intake US
    	setDash(6, "Distance: " + round(RobotMap.distance, 3));
    	setDash(7, "FlyRate: " + round(Robot.flywheelSubsystem.getRate(), 3));
//    	setDash(8, "ColourS: " + round(colourS.getVoltage(), 3));
    	setDash(8, "temp: " + round((SmartDashboard.getNumber("DB/Slider 0") - 2.5) / 2.5 * 180, 3));
//    	setDash(9, "Automode: " + RobotMap.automode); // automode
    	setDash(9, "lidar: " + lidarSubsystem.getDistance());
    }
    
    public void setDash(int num, String value){
    	SmartDashboard.putString("DB/String " + num, value);
    }
    
    public void setDash(int num, double value){
    	SmartDashboard.putString("DB/String " + num, "" + round(value, 3));
    }
    
    public void setDash(int num, int value){
    	SmartDashboard.putString("DB/String " + num, "" + value);
    }
    
    public void clearDashboard(){ //clears dashboard prints
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
    
    public static double round(double num, int digits) {
    	if (digits > 0) {
    		return Math.round(num * Math.pow(10, digits)) / Math.pow(10, digits);
    	} else {
	 		return num;
    	}
    }
    
    public double getAngleConversion() {
    	double angle = Robot.navxSubsystem.getAngle() - 291;
    	while (angle < 0) {
    		angle += 360;
    	}
    	return angle;
    }
}