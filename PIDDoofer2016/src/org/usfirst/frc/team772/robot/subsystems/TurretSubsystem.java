package org.usfirst.frc.team772.robot.subsystems;

import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurretSubsystem extends PIDSubsystem {
	
	CANTalon turretMotor = new CANTalon(8);
	
	public static final double LEFT = -15989, // -90
			CENTER = 0, // 0
			RIGHT = 16018, // 90
			LC = -15989 / 2, // -45
			RC = 16018 / 2; // 45
		
    public TurretSubsystem() {
    	super("TurretSubsystem", RobotMap.kP, RobotMap.kI, RobotMap.kD); // sets pid values 
    	getPIDController().setContinuous(false); // sets continuous to false
    }
    
    public void initDefaultCommand() {

    }
    
    protected double returnPIDInput() { // pid input
    	return turretMotor.getEncPosition();
    }
    
    protected void usePIDOutput(double output) {
    	output *= 0.87;
    	if (output > 0.025) {
    		turretMotor.set(output + 0.07);
    	} else if (output < -0.025) {
    		turretMotor.set(output - 0.07);
    	} else {
    		turretMotor.set(0);
    	}
    		
    	SmartDashboard.putString("DB/String 0", "out: " + output);
    }
    
    public double getEncPosition(){ // returns turret encoder position
    	return turretMotor.getEncPosition();
    }
    
    public double getEncRate(){
    	return turretMotor.getEncVelocity();
    }
    
    public void encoderReset(){ // resets encoder
    	turretMotor.setEncPosition(0);
    }
    
    public void move(double speed){ // moves turret using specified speed value
    	turretMotor.set(speed);
    }
}