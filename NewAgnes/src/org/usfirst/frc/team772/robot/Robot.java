
package org.usfirst.frc.team772.robot;

//import org.usfirst.frc.team772.robot.commands.DriveCommand;
import org.usfirst.frc.team772.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team772.robot.subsystems.FlywheelSubsystem;
import org.usfirst.frc.team772.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team772.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot {
	public static OI oi;
	public static final DriveSubsystem driveSub = new DriveSubsystem();
	public static final IntakeSubsystem intakeSub = new IntakeSubsystem();
	//public static final NavXSubsystem navXSub = new NavXSubsystem();
	public static final ShooterSubsystem shooterSub = new ShooterSubsystem();
	public static final FlywheelSubsystem flywheelSub = new FlywheelSubsystem();
	public static final Compressor compressor = new Compressor();
	
    Command autonomousCommand;
//    DriveCommand drive = new DriveCommand();

    public void robotInit() {
		oi = new OI();
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	//	setDash(1,"Gyro X, Y, Z: " + Math.round(Robot.navXSub.getGyroX()*100 / 100) + ", " + Math.round(Robot.navXSub.getGyroY()*100 / 100)+ ", " + Math.round(Robot.navXSub.getGyroZ()*100 / 100));
	//	setDash(2,"Accel. X, Y, Z: " + Math.round(Robot.navXSub.getAccelX()*100/100) + ", " + Math.round(Robot.navXSub.getAccelY()*100/100)+ ", " + Math.round(Robot.navXSub.getAccelZ()*100 / 100));
	//setDash(3,"Displac. X, Y, Z: " + Math.round(Robot.navXSub.getDisplacementX()*100/100) + ", " + Math.round(Robot.navXSub.getDisplacementY()*100/100)+ ", " + Math.round(Robot.navXSub.getDisplacementZ()*100/100));
		//setDash(4,"Encoder: " + Math.round(Robot.driveSub.encoderLeft.getDistance()*100/100));

	}

    public void autonomousInit() {
        if (autonomousCommand != null) autonomousCommand.start();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
//    	drive.start();
    	compressor.start();
    	
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    public void disabledInit(){
    	clearDash();
    }

    public void teleopPeriodic() {
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
}