
package org.usfirst.frc.team772.robot;

import org.usfirst.frc.team772.robot.commands.ExampleCommand;
import org.usfirst.frc.team772.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team772.robot.subsystems.NavXSubsystem;
import org.usfirst.frc.team772.robot.subsystems.UltrasonicsSubsystem;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static final NavXSubsystem navx = new NavXSubsystem();
	public static final Ultrasonic ultra1 = new Ultrasonic(1,0);
	public static final Ultrasonic ultra2 = new Ultrasonic(3,2);
	public static final UltrasonicsSubsystem ultrasonic = new UltrasonicsSubsystem();
	public static OI oi;
	double range1 = ultra1.getRangeInches();
	double range2 = ultra1.getRangeInches();

    Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
        // instantiate the command used for the autonomous period
        autonomousCommand = new ExampleCommand();
       ultra1.setAutomaticMode(true);
        ultra2.setAutomaticMode(true);
        
    }
    
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putString("DB/String 0", range1 +"");
		SmartDashboard.putString("DB/String 2", ultra1.getRangeInches() +"");
		SmartDashboard.putString("DB/String 1", ultra2.getRangeInches()+"");
		SmartDashboard.putString("DB/String 3", "Gyro X: " + navx.getGyroX());
		SmartDashboard.putString("DB/String 4", "Gyro Y: " + navx.getGyroY());
		SmartDashboard.putString("DB/String 5", "Gyro Z: " + navx.getGyroZ());

	}

    public void autonomousInit() {
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
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
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
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
