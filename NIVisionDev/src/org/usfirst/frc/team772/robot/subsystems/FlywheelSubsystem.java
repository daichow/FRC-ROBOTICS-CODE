package org.usfirst.frc.team772.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class FlywheelSubsystem extends Subsystem {
    
	CANTalon flywheel = new CANTalon(5);
	
    public void initDefaultCommand() {

    }
    
    public void set(double speed){
    	flywheel.set(speed);
    }
}