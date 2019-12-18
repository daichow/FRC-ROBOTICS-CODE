package org.usfirst.frc.team772.robot.subsystems;

import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class EnvelopeSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	//Solenoids use a relay input in order to function
	DoubleSolenoid envelope = new DoubleSolenoid(RobotMap.PCM, RobotMap.envelopeIn, RobotMap.envelopeOut );
	AnalogInput lazerBeam = new AnalogInput(3);
	
	//This method will extend the solenoid pistons outwards
	//by giving the breakout board a value
	public void envelopeOut() {
		envelope.set(DoubleSolenoid.Value.kForward);//forward channel enabled
	}
	
	//This method will bring the Solenoid piston inwards
	//by giving the breakout board a value
	public void envelopeIn() {
		envelope.set(DoubleSolenoid.Value.kReverse);//reverse channel enabled
	}
	
	//This will return true if the lazerBeam voltage is less than 3
	public boolean gearCheck() {
		return lazerBeam.getVoltage()<3;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

