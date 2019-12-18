package org.usfirst.frc.team772.robot;


import org.usfirst.frc.team772.robot.commands.PIDCenterCommand;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	
	Joystick joystick1 = new Joystick(0);
	Button j1button1 = new JoystickButton(joystick1, 1);

	public OI(){
		j1button1.whenPressed(new PIDCenterCommand(new CANTalon(8)));
	}
	
	public Joystick getJoystick1(){
		return joystick1; // returns joystick1 values
	}
}