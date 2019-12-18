package org.usfirst.frc.team772.robot;


import org.usfirst.frc.team772.robot.commands.ResetEncoder;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	Joystick joystick1 = new Joystick(0);
	
	Button j1button1 = new JoystickButton (joystick1, 1);
	Button j1button2 = new JoystickButton (joystick1, 2);
	Button j1button3 = new JoystickButton (joystick1, 3);
	Button j1button4 = new JoystickButton (joystick1, 4);
	Button j1button5 = new JoystickButton (joystick1, 5);
	
	public OI(){
		j1button4.whenPressed(new ResetEncoder());

	}
	public Joystick getJoystick1(){
		return joystick1; // returns joystick1 values
	}
}