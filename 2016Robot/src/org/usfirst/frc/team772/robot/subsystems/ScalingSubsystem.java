package org.usfirst.frc.team772.robot.subsystems;

import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ScalingSubsystem extends Subsystem {

	DoubleSolenoid scalingArms = new DoubleSolenoid(RobotMap.scalingArms1, RobotMap.scalingArms2); // creates scaling arm solenoid

	public ScalingSubsystem(){
		
	}
	
	public void scaleUp(){ // sets solenoid to reverse
		scalingArms.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void scaleDown(){ // sets solenoid to forward
		scalingArms.set(DoubleSolenoid.Value.kForward);
	}
	
    public void initDefaultCommand() {
    	
    }
}