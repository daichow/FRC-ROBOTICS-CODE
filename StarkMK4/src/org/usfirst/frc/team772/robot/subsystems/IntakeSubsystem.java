package org.usfirst.frc.team772.robot.subsystems;

import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeSubsystem extends Subsystem {
    
	com.ctre.CANTalon intakeWheel1 = new com.ctre.CANTalon(7);
	com.ctre.CANTalon intakeWheel2 = new com.ctre.CANTalon(8);
	DoubleSolenoid intakeLeft = new DoubleSolenoid(RobotMap.PCM,0,1);
	Relay intakeLed = new Relay(0);
	
	public void intakeWheel(double speed){
		intakeWheel1.set(-speed);
		intakeWheel2.set(speed);
	}
	
	public void intakeOut(){
		intakeLeft.set(DoubleSolenoid.Value.kForward);
	}
	
	public void intakeIn(){
		intakeLeft.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void intakeOn(){
		intakeLed.set(Relay.Value.kForward);
	}
	
	public void intakeOff(){
		intakeLed.set(Relay.Value.kOff);
	}
	
    public void initDefaultCommand() {
    }
}

