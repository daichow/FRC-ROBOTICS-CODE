package org.usfirst.frc.team772.robot;

import org.usfirst.frc.team772.robot.commands.DriveCommand;
import org.usfirst.frc.team772.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team772.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team772.robot.subsystems.NavXSubsystem;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot {
	
	public static final DriveSubsystem driveSub = new DriveSubsystem();
	public static final IntakeSubsystem intakeSub = new IntakeSubsystem();
	public static final NavXSubsystem navXSub = new NavXSubsystem();
	public static OI oi;
	
	public static Compressor compressor = new Compressor (RobotMap.PCM);
	public static Encoder leftencoder = new Encoder(6, 7, true);
	public static Encoder rightencoder = new Encoder(8, 9, false);
	public static double leftrotdog = 1562.8636336;
	//public static double leftrotdogpi = (leftencoder.getDistance()/leftrotdog) * (8 * Math.PI);
	
    Command autonomousCommand;
    DriveCommand drive = new DriveCommand();
   
    public void robotInit() {
		oi = new OI();
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putString("DB/String 0", "Angle: " + Math.round(navXSub.getAngle()*1000)/1000);
		SmartDashboard.putString("DB/String 1", "Y: " + navXSub.getGyroY());
		SmartDashboard.putString("DB/String 2", "Z: " + navXSub.getGyroZ());
		SmartDashboard.putString("DB/String 4", "left:  " + ((leftencoder.getDistance()/leftrotdog) * (8 * Math.PI)));
		SmartDashboard.putString("DB/String 3", "right:  " + rightencoder.getDistance());
	}

    public void autonomousInit() {
       
        if (autonomousCommand != null) autonomousCommand.start();
    }

   
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
    	drive.start();
    	compressor.start();
        if (autonomousCommand != null) autonomousCommand.cancel();
        
    }
 
    public void disabledInit(){
    	
    }
   
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        SmartDashboard.putString("DB/String 0", "X: " + navXSub.getGyroX());
		SmartDashboard.putString("DB/String 1", "Y: " + navXSub.getGyroY());
		SmartDashboard.putString("DB/String 2", "Z: " + navXSub.getGyroZ());
		SmartDashboard.putString("DB/String 4", "left:  " + ((leftencoder.getDistance()/leftrotdog) * (8 * Math.PI)));
		SmartDashboard.putString("DB/String 3", "right:  " + rightencoder.getDistance());
		
    }
    
    public void testPeriodic() {
    	
        LiveWindow.run();
    }
//    private void clearDash(){ // clears dashboard
//    	SmartDashboard.putString("DB/String 0", ""); 
//		SmartDashboard.putString("DB/String 1", "");
//		SmartDashboard.putString("DB/String 2", "");
//		SmartDashboard.putString("DB/String 3", "");
//		SmartDashboard.putString("DB/String 4", "");
//		SmartDashboard.putString("DB/String 5", "");
//		SmartDashboard.putString("DB/String 6", "");
//		SmartDashboard.putString("DB/String 7", "");
//		SmartDashboard.putString("DB/String 8", "");
//		SmartDashboard.putString("DB/String 9", "");
//    }
//    
//    public static void setDash(int form, String msg){
//    	switch(form){ // prints to different DB/String depending on form
//    		case 0: SmartDashboard.putString("DB/String 0", msg); break;
//    		case 1: SmartDashboard.putString("DB/String 1", msg); break;
//    		case 2: SmartDashboard.putString("DB/String 2", msg); break;
//    		case 3: SmartDashboard.putString("DB/String 3", msg); break;
//    		case 4: SmartDashboard.putString("DB/String 4", msg); break;
//    		case 5: SmartDashboard.putString("DB/String 5", msg); break;
//    		case 6: SmartDashboard.putString("DB/String 6", msg); break;
//    		case 7: SmartDashboard.putString("DB/String 7", msg); break;
//    		case 8: SmartDashboard.putString("DB/String 8", msg); break;
//    		case 9: SmartDashboard.putString("DB/String 9", msg); break;
//    		default: break;
//    	}
//
//    }
}