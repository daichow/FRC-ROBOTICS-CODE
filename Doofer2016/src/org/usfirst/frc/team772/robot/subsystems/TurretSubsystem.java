package org.usfirst.frc.team772.robot.subsystems;

import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class TurretSubsystem extends Subsystem {
    
	CANTalon turretMotor = new CANTalon(8);

    public void turn(double speed){
    	turretMotor.set(speed);
    }
    public double encoder(){
    	return turretMotor.getEncPosition();
    }
    public void encoderReset(){ // resets encoder
    	turretMotor.setEncPosition(0);
    }
    public void initDefaultCommand() {
    }
}

