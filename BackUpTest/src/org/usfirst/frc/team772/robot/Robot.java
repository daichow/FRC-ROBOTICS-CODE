package org.usfirst.frc.team772.robot;

import org.usfirst.frc.team772.robot.subsystems.LiftingSubsystem;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	public static final LiftingSubsystem liftingSubsystem = new LiftingSubsystem();
	public static OI oi;
	
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
        
		oi = new OI(); 

		SmartDashboard.putNumber("DB/Slider 0", 0); // set slider 0 to 0
		SmartDashboard.putNumber("DB/Slider 1", 0); // set slider 1 to 0
        SmartDashboard.putNumber("DB/Slider 2", 0); // set slider 2 to 0
        SmartDashboard.putNumber("DB/Slider 3", 1); // set slider 3 to 1
		
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
        if (autonomousCommand != null) autonomousCommand.start();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        
        readPitch();
		setTilt();
        printPeriodic();
    }

    public void teleopInit() {
    	compressor.start(); //starts compressor if one is attached
    	
        if (autonomousCommand != null) autonomousCommand.cancel(); //stops auto mode
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        
        readPitch();
		setTilt();
        printPeriodic();
    }
    
    public void testPeriodic() { //dont worry about it
        LiveWindow.run();
        
        printPeriodic();
    }
    
    
    public void readPitch(){
    	for (int i = 49; i > 0; i--) { // shifts array to the right
			pitch[i] = pitch[i - 1];
		}
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
    
}