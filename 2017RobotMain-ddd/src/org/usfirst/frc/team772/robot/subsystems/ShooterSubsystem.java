package org.usfirst.frc.team772.robot.subsystems;

import org.usfirst.frc.team772.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterSubsystem extends Subsystem {
	
	//declares the motors for the flywheel and window motors (which feeds the balls into the flywheel motors which shoots the balls)
	CANTalon flywheelLeft = new CANTalon(RobotMap.flywheelLeft);
	CANTalon flywheelRight = new CANTalon(RobotMap.flywheelRight);
	CANTalon feeder = new CANTalon(RobotMap.feeder);
	CANTalon windowMotor1 = new CANTalon(RobotMap.windowMotor1);
	CANTalon windowMotor2 = new CANTalon(RobotMap.windowMotor2);
	
	public ShooterSubsystem(){
		flywheelRight.changeControlMode(TalonControlMode.Follower);
		flywheelRight.set(5);
		flywheelLeft.setStatusFrameRateMs(CANTalon.StatusFrameRate.Feedback, 1);
		flywheelLeft.setProfile(0);
		flywheelLeft.setF(0.0319);//0.0319
		flywheelLeft.setP(0.05115);//12, 0.05115
//		flywheelLeft.setI(20);
		flywheelLeft.setD(1.5);//1.5, 2
//		flywheelLeft.setMotionMagicCruiseVelocity(5057);//90
//		flywheelLeft.setMotionMagicCruiseVelocity((18750/3)*.68);//0.8
//		flywheelLeft.setMotionMagicAcceleration((18750/3)*0.60);//0.60
	}
//sets the directions of the flywheel motors
	public void flywheel(double speed){
		flywheelLeft.setMotionMagicCruiseVelocity(4300*speed);//0.8
		flywheelLeft.setMotionMagicAcceleration(4300*(speed));//*speed-8
		flywheelLeft.set(speed);
//		flywheelLeft.setMotionMagicCruiseVelocity((18750/3)*speed);//0.8
//		flywheelLeft.setMotionMagicAcceleration((18750/3)*speed);//0.60
//		flywheelRight.set(speed);
//		flywheelRight.set(flywheelLeft.getDeviceID());
	}

//sets the direction of the feeder motors
	public void feeder(double speed){
		feeder.set(speed);
	}

	public void windowMotor(double speed){
		windowMotor1.set(speed);
		windowMotor2.set(-speed);
	}
	
	public double flywheelEnc(){
		return flywheelLeft.getSpeed();
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

