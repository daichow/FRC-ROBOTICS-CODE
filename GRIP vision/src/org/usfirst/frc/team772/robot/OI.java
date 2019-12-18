package org.usfirst.frc.team772.robot;

import org.usfirst.frc.team772.robot.commands.PIDFlywheelCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	
	// Joysticks
	Joystick joystick1 = new Joystick(0);
	Joystick joystick2 = new Joystick(1);
	
	// Joystick1 Buttons
	Button j1button1 = new JoystickButton (joystick1, 1); // A
	Button j1button2 = new JoystickButton (joystick1, 2); // B
	Button j1button3 = new JoystickButton (joystick1, 3); // X
	Button j1button4 = new JoystickButton (joystick1, 4); // Y
	Button j1button5 = new JoystickButton (joystick1, 5); // LB
	Button j1button6 = new JoystickButton (joystick1, 6); // RB

	public OI(){
		j1button1.whenPressed(new PIDFlywheelCommand(160.0));
	}
	public Joystick getJoystick1(){
		return joystick1; // returns joystick1 values
	}
	public Joystick getJoystick2(){
		return joystick2; // returns joystick1 values
	}
}

