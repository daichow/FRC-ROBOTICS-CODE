package org.usfirst.frc.team772.robot.subsystems;

import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LiftingSubsystem extends Subsystem {
    
	DoubleSolenoid backLifter = new DoubleSolenoid(RobotMap.PCM, 0, 1);
	DoubleSolenoid frontLifter = new DoubleSolenoid(RobotMap.PCM, 4, 5);
	
    public void initDefaultCommand() {

    }
    
    public void backUp(){
    	backLifter.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void backDown(){
    	backLifter.set(DoubleSolenoid.Value.kForward);
    }
    
    public void frontUp(){
    	frontLifter.set(DoubleSolenoid.Value.kForward);
    }
    
    public void frontDown(){
    	frontLifter.set(DoubleSolenoid.Value.kReverse);
    }
}