package org.usfirst.frc.team772.robot.subsystems;

import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class TurretSubsystem extends PIDSubsystem {

	CANTalon turretMotor = new CANTalon(8); // turret motor on port 8
	double speed;
	
	public static final double LEFT = -16016, // position constants
			CENTER = 0,
			RIGHT = 16087;
	
    // Initialize your subsystem here
    public TurretSubsystem() {
    	super("TurretSubsystem", RobotMap.kP, 0, 0/*RobotMap.kI, RobotMap.kD*/); 
    	getPIDController().setContinuous(false);
//    	setSetpoint(0);
//    	enable();
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	return turretMotor.getEncPosition();
    }
    
    protected void usePIDOutput(double output) {
//    	speed = output;
    	turretMotor.set(output);
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    }
    
    public double getEncPosition(){
    	return turretMotor.getEncPosition();
    }
    
    public void encoderReset(){
    	turretMotor.setEncPosition(0);
    }
    
    public void move(double speed){
    	turretMotor.set(speed);
    }
    
    public double getPIDSpeed(){
    	return speed;
    }
}
