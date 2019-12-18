package org.usfirst.frc.team772.robot.subsystems;

import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeSubsystem extends Subsystem {
    
	com.ctre.CANTalon intakeMotor = new com.ctre.CANTalon (7);
	com.ctre.CANTalon intakeMotor2 = new com.ctre.CANTalon (8); 
	DoubleSolenoid arm = new DoubleSolenoid (RobotMap.pcm,0, 1);
	Relay armlight = new Relay (0);
	
	public void move(double speed){
		intakeMotor.set(speed);
		intakeMotor2.set(speed);
	}
	public void armIn(){
		arm.set(DoubleSolenoid.Value.kForward);
	}
	public void armOut(){
		arm.set(DoubleSolenoid.Value.kReverse);
	}
	public void armlightOn(){
		armlight.set(Relay.Value.kForward);
	}
	public void armlightOff(){
		armlight.set(Relay.Value.kOff);
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

