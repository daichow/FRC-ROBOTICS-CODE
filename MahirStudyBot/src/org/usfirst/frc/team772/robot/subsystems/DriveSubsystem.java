package org.usfirst.frc.team772.robot.subsystems;

import org.usfirst.frc.team772.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
//Here we declare the different motor objects as well as declare a robotDrive object from the RobotDrive class
RobotDrive robotDrive;
CANTalon frontLeft = new CANTalon (RobotMap.frontLeftDrive);
CANTalon frontRight = new CANTalon (RobotMap.frontRightDrive);
CANTalon rearLeft = new CANTalon (RobotMap.rearLeftDrive);
CANTalon rearRight = new CANTalon (RobotMap.rearRightDrive);

//This is the solenoid object that will allow us to switch gears on the robot
DoubleSolenoid shiftGear = new DoubleSolenoid (RobotMap.PCM, RobotMap.shotPinForward, RobotMap.shotPinReverse);

//Here, we initialize our robotDrive object and set its parameters
//By the way, when an object of this subsystem is made in Robot class, all of this gets initialized
//This is a must for all Drive Subsystems
public DriveSubsystem (){
	RobotDrive robotDrive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);//These are all of the Port locations 
	
	robotDrive.setInvertedMotor(MotorType.kFrontLeft, true);
	robotDrive.setInvertedMotor(MotorType.kRearLeft, true);
	robotDrive.setInvertedMotor(MotorType.kFrontRight, true);
	robotDrive.setInvertedMotor(MotorType.kRearRight, true);
	robotDrive.setSafetyEnabled(true);
	
}

//This gives input to the move method so that it could move in teleop
//the move method should always have the instance of the Joystick class that was made in robot OI
//so it can receive values and invoke methods
//a new instance cannot be made because we are using the specific joystick from OI

//This method will allow the robot to move in teleop
//This requires the created joystick from robot OI
public void move(Joystick xboxController1, double speed) {
	robotDrive.arcadeDrive(xboxController1.getY()*speed, xboxController1.getX()*speed);
}



//This gives input to auto method so that it can be used in autonomous
public void auto (double moveForwardsVal, double rotateVal) {
	robotDrive.arcadeDrive(moveForwardsVal, rotateVal);
}

//This will allow the robot to shift gears

public void shiftGear(boolean toggle) {
	if (toggle ==true) {
		shiftGear.set(DoubleSolenoid.Value.kForward);
	}else {
		shiftGear.set(DoubleSolenoid.Value.kReverse);
	}
}


    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

