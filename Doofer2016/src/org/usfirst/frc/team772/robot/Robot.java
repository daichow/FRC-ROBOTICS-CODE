
package org.usfirst.frc.team772.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team772.robot.commands.AutoCommand;
import org.usfirst.frc.team772.robot.commands.DriveCommand;
import org.usfirst.frc.team772.robot.commands.ExampleCommand;
import org.usfirst.frc.team772.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team772.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team772.robot.subsystems.FlywheelSubsystem;
import org.usfirst.frc.team772.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team772.robot.subsystems.ShooterSubsystem;
import org.usfirst.frc.team772.robot.subsystems.TipperSubsystem;
import org.usfirst.frc.team772.robot.subsystems.TurretSubsystem;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot {

	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static final DriveSubsystem driveSubsystem = new DriveSubsystem();
	public static final DriveCommand drive = new DriveCommand();
	public static final TipperSubsystem tipperSubsystem = new TipperSubsystem();
	public static final FlywheelSubsystem flywheelSubsystem = new FlywheelSubsystem();
	public static final TurretSubsystem turretSubsystem = new TurretSubsystem();
	public static final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
	public static final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
	public static OI oi;
	public static Compressor compressor = new Compressor(RobotMap.PCM);
	public static final Ultrasonic ultrasonic = new Ultrasonic(0,1);

    Command autonomousCommand;
    SendableChooser chooser;

    public void robotInit() {
		oi = new OI();
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", new ExampleCommand());
//        chooser.addObject("My Auto", new MyAutoCommand());
        SmartDashboard.putData("Auto mode", chooser);
        ultrasonic.setEnabled(true);
        ultrasonic.setAutomaticMode(true);
       turretSubsystem.encoderReset();
       tipperSubsystem.resetEncoder();
    }
	

    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}


    public void autonomousInit() {
        autonomousCommand = (Command) chooser.getSelected();
        
		String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new AutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new AutoCommand();
			break;
		} 
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }


    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
    	drive.start(); 
    	compressor.start();
        if (autonomousCommand != null) autonomousCommand.cancel();
    }


    public void teleopPeriodic() {
        Scheduler.getInstance().run();
//        ledSubsystem.hoodOff();
//        ledSubsystem.intakeOff();
        SmartDashboard.putString("DB/String 0", ""+ultrasonic.getRangeInches());
        SmartDashboard.putString("DB/String 1", "turret: "+turretSubsystem.encoder());
        SmartDashboard.putString("DB/String 2", "tipper: "+tipperSubsystem.encoder());
    }
    

    public void testPeriodic() {
        LiveWindow.run();
    }
}
