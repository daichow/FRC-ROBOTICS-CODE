
package org.usfirst.frc.team772.robot;

import org.usfirst.frc.team772.robot.commands.DriveCommand;
import org.usfirst.frc.team772.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team772.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team772.robot.subsystems.FlywheelSubsystem;
import org.usfirst.frc.team772.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team772.robot.subsystems.ShooterSubsystem;
import org.usfirst.frc.team772.robot.subsystems.TipperSubsystem;
import org.usfirst.frc.team772.robot.subsystems.TurretSubsystem;
import org.usfirst.frc.team772.robot.subsystems.UltrasonicSubsystem;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot {
	public static final DriveSubsystem driveSubsystem = new DriveSubsystem();
	public static final FlywheelSubsystem flywheelSubsystem = new FlywheelSubsystem();
	public static final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
	public static final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
	public static final TipperSubsystem tipperSubsystem = new TipperSubsystem();
	public static final TurretSubsystem turretSubsystem = new TurretSubsystem();
	public static final UltrasonicSubsystem ultrasonicSubsystem = new UltrasonicSubsystem();
	public static OI oi;
	public static final DriveCommand drive = new DriveCommand();
	
    final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    String autoSelected;
    SendableChooser chooser;
	

    public void robotInit() {
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
    }
    

    public void autonomousInit() {
    	autoSelected = (String) chooser.getSelected();
//		autoSelected = SmartDashboard.getString("Auto Sr", defaultAuto);
    }


    public void autonomousPeriodic() {
    	switch(autoSelected) {
    	case customAuto:
        //Put custom auto code here   
            break;
    	case defaultAuto:
    	default:
    	//Put default auto code here
            break;
    	}
    }


    public void teleopPeriodic() {
   
    }
    

    public void testPeriodic() {
    
    }
    
    public void setDash(int num, String value) {
    	SmartDashboard.putString("DB/String " + num, value);
    }
    
}
