package org.usfirst.frc.team772.robot.subsystems;

import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class TurretSubsystem extends Subsystem {
    
	CANTalon turretMotor = new CANTalon(RobotMap.turretMotor);

	public void move(double speed){
		turretMotor.set(speed);
	}
	
	public double encPos(){
		return turretMotor.getEncPosition();
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

