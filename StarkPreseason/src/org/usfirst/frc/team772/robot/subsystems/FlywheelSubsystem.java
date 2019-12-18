package org.usfirst.frc.team772.robot.subsystems;

import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class FlywheelSubsystem extends Subsystem {
    
    com.ctre.CANTalon flywheel1 = new com.ctre.CANTalon (5);
    com.ctre.CANTalon flywheel2 = new com.ctre.CANTalon (6);
    DoubleSolenoid hood = new DoubleSolenoid (RobotMap.pcm, 2,3);
    public void move (double fwspeed){
    	flywheel1.set(fwspeed);
    	flywheel2.set(fwspeed);
    }
    public void hoodUp (){
    	hood.set(DoubleSolenoid.Value.kForward);
    }public void hoodDown (){
    	hood.set(DoubleSolenoid.Value.kReverse);
    }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

