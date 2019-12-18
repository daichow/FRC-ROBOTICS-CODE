package org.usfirst.frc.team772.robot.subsystems;

import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class FlywheelSubsystem extends Subsystem {
	
	public CANTalon flywheel = new CANTalon(5);

    public void initDefaultCommand() {

    }
    
    public void forward(){
    	flywheel.set(RobotMap.flywheelSpeed);
    }
    
    public void backward(){
    	flywheel.set(-RobotMap.flywheelSpeed);
    }
    
    public void stop(){
    	flywheel.set(0);
    }
    
    public void move(double speed){
    	flywheel.set(speed);
    }
}