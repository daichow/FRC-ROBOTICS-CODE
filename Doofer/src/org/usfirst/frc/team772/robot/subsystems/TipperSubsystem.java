package org.usfirst.frc.team772.robot.subsystems;

import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class TipperSubsystem extends Subsystem {
    
	CANTalon tipperMotor = new CANTalon(RobotMap.tipperMotor); // creates tipper motor
	DoubleSolenoid brake = new DoubleSolenoid(RobotMap.PCM, RobotMap.brake1, RobotMap.brake2); // creates brake solenoid
	
	public TipperSubsystem(){
		
	}
	
	public void down(){ // tipper up
		tipperMotor.set(RobotMap.tipperDownSpeed);
	}
	
	public void up(){ // tipper down
		tipperMotor.set(RobotMap.tipperUpSpeed); 
	}
	
	public void move(double speed){ // moves tipper motor using speed parameter
		tipperMotor.set(speed);
	}
	
	public int getEncPosition(){ // returns encoder position
		return tipperMotor.getEncPosition();
	}
	
	public void resetEncPosition(){ // resets encoder
		tipperMotor.setEncPosition(0);
	}

	public CANTalon getTipperMotor(){ // returns tipper motor
		return tipperMotor;
	}

	public void brakeOn(){ // engage brake
		brake.set(DoubleSolenoid.Value.kForward);
	}
	
	public void brakeOff(){ // release brake
		brake.set(DoubleSolenoid.Value.kReverse);
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

