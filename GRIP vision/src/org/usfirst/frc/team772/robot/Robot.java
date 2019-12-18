
package org.usfirst.frc.team772.robot;

import org.usfirst.frc.team772.robot.commands.DriveCommand;
import org.usfirst.frc.team772.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team772.robot.subsystems.FlywheelSubsystem;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot {
	public static final DriveSubsystem driveSubsystem = new DriveSubsystem();
	public static final FlywheelSubsystem flywheelSubsystem = new FlywheelSubsystem();
	public static OI oi;
	
	DriveCommand drive = new DriveCommand();

    Command autonomousCommand;
    ///home/lvuser/project.grip
    ////C:Users/2015p/Desktop/darren - Copy.grip
    
//    private final static String[] GRIP_ARGS = new String[] {
//        "/usr/local/frc/JRE/bin/java", "-jar",
//        "/home/lvuser/grip.jar", "/home/lvuser/orange.grip"};
    
    private NetworkTable grip = NetworkTable.getTable("GRIP/myContoursReport");
    
    public void robotInit() {
		oi = new OI();		
    }
	
	public void disabledPeriodic() {
		getNetValues();
		printNetValues();
		setDash(5, "kP: " + RobotMap.kP);
    	setDash(6, "kI: " + RobotMap.kI);
    	setDash(7, "kD: " + RobotMap.kD);
	}

    public void autonomousInit() {
        if (autonomousCommand != null) autonomousCommand.start();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
    	clearDash();
//    	try {
//			Runtime.getRuntime().exec(GRIP_ARGS);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}	
    	
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    public void disabledInit(){
    	//clearDash();
    }

    public void teleopPeriodic() {
    	getNetValues();
    	printNetValues();
    	
    	RobotMap.kP = SmartDashboard.getNumber("DB/Slider 0", 0);
    	RobotMap.kI = SmartDashboard.getNumber("DB/Slider 1", 0);
    	RobotMap.kD = SmartDashboard.getNumber("DB/Slider 2", 0);
    	
    	setDash(5, "kP: " + RobotMap.kP);
    	setDash(6, "kI: " + RobotMap.kI);
    	setDash(7, "kD: " + RobotMap.kD);
        Scheduler.getInstance().run();
    }
    
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    private void clearDash(){ // clears dashboard
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
    public static void setDash(int form, String msg){
    	switch(form){ // prints to different DB/String depending on form
    		case 0: SmartDashboard.putString("DB/String 0", msg); break;
    		case 1: SmartDashboard.putString("DB/String 1", msg); break;
    		case 2: SmartDashboard.putString("DB/String 2", msg); break;
    		case 3: SmartDashboard.putString("DB/String 3", msg); break;
    		case 4: SmartDashboard.putString("DB/String 4", msg); break;
    		case 5: SmartDashboard.putString("DB/String 5", msg); break;
    		case 6: SmartDashboard.putString("DB/String 6", msg); break;
    		case 7: SmartDashboard.putString("DB/String 7", msg); break;
    		case 8: SmartDashboard.putString("DB/String 8", msg); break;
    		case 9: SmartDashboard.putString("DB/String 9", msg); break;
    		default: break;
    	}
    }
    
    public void getNetValues(){
    	for (double area : grip.getNumberArray("area", new double[0])) {
        	RobotMap.area = area;
        }
    	
    	for (double centerX : grip.getNumberArray("centerX", new double[0])) {
        	RobotMap.centerX = centerX;
        }
    	
    	for (double centerY : grip.getNumberArray("centerY", new double[0])) {
        	RobotMap.centerY = centerY;
        }
    	
    	for (double width : grip.getNumberArray("width", new double[0])) {
        	RobotMap.width = width;
        }
    	
    	for (double height : grip.getNumberArray("height", new double[0])) {
        	RobotMap.height = height;
        }
    }
    
    public void printNetValues(){
    	SmartDashboard.putString("DB/String 0", "area: " + RobotMap.area); 
    	SmartDashboard.putString("DB/String 1", "centerX: " + RobotMap.centerX); 
    	SmartDashboard.putString("DB/String 2", "centerY: " + RobotMap.centerY); 
    	SmartDashboard.putString("DB/String 3", "width: " + RobotMap.width); 
    	SmartDashboard.putString("DB/String 4", "height: " + RobotMap.height); 
    }
}