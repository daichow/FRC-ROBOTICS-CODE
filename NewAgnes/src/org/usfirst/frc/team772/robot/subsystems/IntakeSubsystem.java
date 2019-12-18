package org.usfirst.frc.team772.robot.subsystems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeSubsystem extends Subsystem {
    
	Relay intake1 = new Relay(1);
	Relay intake2 = new Relay(2);

    public void initDefaultCommand() {
    
    }
    
    public void BallIn(){
    	intake1.set(Relay.Value.kForward);
    	intake2.set(Relay.Value.kForward);
    }
    
    public void BallOut(){
    	intake1.set(Relay.Value.kReverse);
    	intake2.set(Relay.Value.kReverse);
    }
    
    public void Stop(){
    	intake1.set(Relay.Value.kOff);
    	intake2.set(Relay.Value.kOff);
    }
}