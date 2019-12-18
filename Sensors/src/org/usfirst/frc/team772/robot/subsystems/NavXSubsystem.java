package org.usfirst.frc.team772.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;

public class NavXSubsystem extends Subsystem {
	  AHRS ahrs;
	  
    public NavXSubsystem(){
    	ahrs = new AHRS(SPI.Port.kMXP);
    }
    
    // Gyro Values
    public float getGyroX(){
    	return ahrs.getRawGyroX();
    }
    public float getGyroY(){
    	return ahrs.getRawGyroY();
    }
    public float getGyroZ(){
    	return ahrs.getRawGyroZ();
    }
    
    // Acceleration Values
    public float getAccelX(){
    	return ahrs.getRawAccelX();
    }
    public float getAccelY(){
    	return ahrs.getRawAccelY();
    }
    public float getAccelZ(){
    	return ahrs.getRawAccelZ();
    }
    
    // Displacement Values
    public float getDisplacementX(){
    	return ahrs.getDisplacementX();
    }
    public float getDisplacementY(){
    	return ahrs.getDisplacementY();
    }
    public float getDisplacementZ(){
    	return ahrs.getDisplacementZ();
    }
    
    //
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

