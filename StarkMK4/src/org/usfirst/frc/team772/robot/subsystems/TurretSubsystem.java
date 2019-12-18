package org.usfirst.frc.team772.robot.subsystems;

import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class TurretSubsystem extends Subsystem {
    
    DoubleSolenoid shooterHood = new DoubleSolenoid(RobotMap.PCM, 2,3);
    Relay hoodLed = new Relay(3);

    public void actuateHood(){
    	shooterHood.set(DoubleSolenoid.Value.kForward);
    }
    public void retractHood(){
    	shooterHood.set(DoubleSolenoid.Value.kReverse);
    }
    public void hoodOn(){
		hoodLed.set(Relay.Value.kForward);
	}
	public void hoodOff(){
		hoodLed.set(Relay.Value.kOff);
	}
    public void initDefaultCommand() {
    }
}

