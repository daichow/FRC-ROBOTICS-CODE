package org.usfirst.frc.team772.robot.subsystems;

import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class EnvelopeSubsystem extends Subsystem {

   //allows the gear envelopes to open and close 
	DoubleSolenoid envelope = new DoubleSolenoid(RobotMap.PCM, RobotMap.envelopeOut, RobotMap.envelopeIn);
    AnalogInput lazerBeam = new AnalogInput(3);
    
    public void extendEnvelope(){
    	envelope.set(DoubleSolenoid.Value.kForward);
    }
    
    public void retractEnvelope(){
    	envelope.set(DoubleSolenoid.Value.kReverse);
    }
    
    
    public boolean gearCheck(){
    	return lazerBeam.getVoltage() > 3;
    }
    
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

