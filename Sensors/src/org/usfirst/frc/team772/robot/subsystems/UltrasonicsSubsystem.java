package org.usfirst.frc.team772.robot.subsystems;

import org.usfirst.frc.team772.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Ultrasonic;


/**
 *
 */
public class UltrasonicsSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public void ultrasonicSample() {
    	double range1 = Robot.ultra1.getRangeInches(); // reads the range on the ultrasonic sensor
    	double range2 = Robot.ultra2.getRangeInches(); // reads the range on the ultrasonic sensor
    }


    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

