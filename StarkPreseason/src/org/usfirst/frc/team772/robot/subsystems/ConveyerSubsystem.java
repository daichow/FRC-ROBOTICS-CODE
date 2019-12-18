package org.usfirst.frc.team772.robot.subsystems;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ConveyerSubsystem extends Subsystem {
    
    com.ctre.CANTalon conveyer = new com.ctre.CANTalon(9);
    
    public void move(double speed) {
    	conveyer.set(speed);
    }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    
    }
}



