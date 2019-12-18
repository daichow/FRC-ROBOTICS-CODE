package org.usfirst.frc.team772.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

public class NavxSubsystem extends Subsystem {
    
	AHRS navx = new AHRS(SerialPort.Port.kMXP); // creates navx object

    public void initDefaultCommand() {
    	
    }
    
    public double getAngle(){ // returns angle
    	return navx.getAngle();
    }
    
    public double getPitch(){ // returns pitch
    	return navx.getPitch();
    }
    
    public double getYaw(){ // returns yaw
    	return navx.getYaw();
    }
    
    public double getRoll(){ // returns roll
    	return navx.getRoll();
    }
    
    public void reset(){ // resets navx
		navx.reset();
		navx.zeroYaw();
	}
    
    public AHRS getNavx(){ // returns navxd
    	return navx;
    }
}