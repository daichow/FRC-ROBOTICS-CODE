package org.usfirst.frc.team772.robot.subsystems;

import org.usfirst.frc.team772.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class WinchSubsystem extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	CANTalon winchMotor = new CANTalon(RobotMap.winchMotor);
	
	public void liftBot(double liftSpeed) {
		winchMotor.set(liftSpeed);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
