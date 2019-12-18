package org.usfirst.frc.team772.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team772.robot.commands.AutoDriveLidarCommand;
import org.usfirst.frc.team772.robot.commands.ExampleCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	Joystick joystick1 = new Joystick(0);
	Button j1button1 = new JoystickButton(joystick1, 1);
	
	public OI(){
		j1button1.whenPressed(new AutoDriveLidarCommand(60));
	}
	
	public Joystick getJoystick1(){
		return joystick1; // returns joystick1 values
	}
}
