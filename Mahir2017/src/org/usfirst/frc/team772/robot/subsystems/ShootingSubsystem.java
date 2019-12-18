package org.usfirst.frc.team772.robot.subsystems;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShootingSubsystem extends Subsystem {

	// Here we create all of the motor objects required in this subsystem
	CANTalon shootingFeederMotor = new CANTalon(RobotMap.shootingFeeder);
	CANTalon agitatorRightMotor = new CANTalon(RobotMap.agitatorRight);
	CANTalon agitatorLeftMotor = new CANTalon(RobotMap.agitatorLeft);
	CANTalon flyWheelLeftMotor = new CANTalon(RobotMap.flyWheelLeft);
	CANTalon flyWheelRightMotor = new CANTalon(RobotMap.flyWheelRight);

	//This constructor sets the inversion of all of the motors in this subsystem
	public ShootingSubsystem() {
		agitatorRightMotor.setInverted(false);
		agitatorLeftMotor.setInverted(true);
		shootingFeederMotor.setInverted(true);
	}

	//This method will agitate the balls when called
	public void agitate(double speed) {
		agitatorRightMotor.set(speed);
		agitatorLeftMotor.set(speed);
	}

	//This shoot method will shoot the balls by using the flywheels, agitators, and the convayor belt
	public void shoot(double convayorSpeed, double agitatorSpeed, double feederSpeed, double flyWheelSpeed) {
		Robot.intakeSubsytem.intakeConvayorMotor.set(convayorSpeed);
		agitate(agitatorSpeed);
		shootingFeederMotor.set(feederSpeed);
		flyWheel(flyWheelSpeed);
	}

	//This is the method that will control the flyWheel motors
	public void flyWheel(double speed) {
		flyWheelLeftMotor.set(speed);
		flyWheelRightMotor.set(speed);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
