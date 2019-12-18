package org.usfirst.frc.team772.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class FlywheelSubsystem extends Subsystem {
    
    com.ctre.CANTalon flywheel1 = new com.ctre.CANTalon(5);
    com.ctre.CANTalon flywheel2 = new com.ctre.CANTalon(6);
    
    public void flywheel(double speed){
    	flywheel1.set(speed);
    	flywheel2.set(speed);
    }
    public void initDefaultCommand() {
    }
}

