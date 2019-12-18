package org.usfirst.frc.team772.robot.subsystems;

import org.usfirst.frc.team772.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	//Two new motors are created 
	CANTalon ballIntakeMotor = new CANTalon(RobotMap.ballintakeMotors);
	CANTalon conveyorBeltMotor = new CANTalon(RobotMap.conveyorBeltMotors);
	
	//This method will do the ball intake when called
	//A speed parameter will be needed to give the motors a speed
	//change directions by adding a negative sign before the speed inside the method
	public void intake (double speed) {
		ballIntakeMotor.set(speed);
		conveyorBeltMotor.set(speed);
	}
	
	public void convayor(double speed) {
		conveyorBeltMotor.set(speed);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

