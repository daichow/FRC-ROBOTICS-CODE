package org.usfirst.frc.team772.robot.subsystems;

import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class FlywheelSubsystem extends Subsystem {
    
	CANTalon flywheelMotor = new CANTalon(RobotMap.flywheelMotor); 
	
	public void reverse(){ // sets flywheel motor reverse 
		flywheelMotor.set(RobotMap.flywheelRSpeed);
	}
	
	public void forward(){ // sets flywheel motor forward
		flywheelMotor.set(RobotMap.flywheelFSpeed);
	}
	
	public void flywheelSpit(){
		flywheelMotor.set(RobotMap.flywheelSpit);
	}
	
	public void move(double speed){ // sets flywheel motor to specified speed
		flywheelMotor.set(speed);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

