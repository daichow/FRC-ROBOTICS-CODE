package org.usfirst.frc.team772.robot.subsystems;

import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class TipperSubsystem extends Subsystem {
    
	CANTalon tipperMotor = new CANTalon(6);
	DoubleSolenoid tipbreak = new DoubleSolenoid(RobotMap.PCM,2,1);
	
	public void tipperMotor(double speed){
		tipperMotor.set(speed);
	}
	
	public void breakOn(){
		tipbreak.set(DoubleSolenoid.Value.kForward);
	}
	
	public void breakOff(){
		tipbreak.set(DoubleSolenoid.Value.kReverse);
	}
	
	public double encoder(){
		return tipperMotor.getEncPosition();
	}
	
	public void resetEncoder(){
		tipperMotor.setEncPosition(0);
	}
    public void initDefaultCommand() {
    }
}

