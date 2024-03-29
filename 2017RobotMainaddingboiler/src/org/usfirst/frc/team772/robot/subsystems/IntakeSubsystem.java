package org.usfirst.frc.team772.robot.subsystems;

import org.usfirst.frc.team772.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeSubsystem extends Subsystem {
	
//declares the ball intake motors
    CANTalon intake = new CANTalon(RobotMap.intake);
    CANTalon intakeConvayor = new CANTalon(RobotMap.intakeConvayor);
    
//sets the direction of the ball intake 
    public void intake(double speed){
    	intake.set(-speed);
    	intakeConvayor.set(speed);
    }

    //sets the motor speed
    public void convayor(double speed){
    	intakeConvayor.set(speed);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

