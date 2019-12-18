package org.usfirst.frc.team772.robot.subsystems;



import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterSubsystem extends Subsystem {
	public static DoubleSolenoid shooterMotor = new DoubleSolenoid(RobotMap.PCM, 2, 3); // dummy numbers for the ports



    public void initDefaultCommand() {
    	
    }
    
    public void shootOut(){
    	shooterMotor.set(Value.kForward);
    }
    
    public void backToPosition(){
    	shooterMotor.set(Value.kReverse);
    }
}

