package org.usfirst.frc.team772.robot.subsystems;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class VisionTurretSubsystem extends PIDSubsystem {

    public VisionTurretSubsystem() {
    	// near: 0.00288 / 1.5, (10/11) * 0.54, (10/11) / 2
    	// far: 0.00288 / 2.5, (10/11) * 0.54, (10/11) / 3
    	// where everrrrrr you areeeeee: 
    	//RobotMap.visionKu/1.7, RobotMap.visionPu/2 , RobotMap.visionPu/8--------- 0.00288 / 2.5, (10/11) * 0.54, (10/11) / 3
    	// pu = 1, ku = 0.0029
    	super("VisionTurretSubsystem", 0.00288 / 1.5, (10/11) * 0.54, (10/11) / 2); // sets pid values, p /2.85, i *.6, d /3
    	getPIDController().setContinuous(false); // sets continuous to false
    }
    
    protected double returnPIDInput() { // pid input
    	return RobotMap.centerX;
    }
    
    protected void usePIDOutput(double output) { // pid output
       Robot.turretSubsystem.move(-output);
    }
    
    public void initDefaultCommand() {

    }
}