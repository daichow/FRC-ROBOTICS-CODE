package org.usfirst.frc.team772.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team772.robot.commands.EnvelopeCommand;
import org.usfirst.frc.team772.robot.commands.ExampleCommand;
import org.usfirst.frc.team772.robot.commands.IntakeCommand;
import org.usfirst.frc.team772.robot.commands.ShiftGearCommand;
import org.usfirst.frc.team772.robot.commands.WinchCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	Joystick xbox = new Joystick(0);
	Button a = new JoystickButton(xbox, 1);
	Button b = new JoystickButton(xbox, 2);
	Button x = new JoystickButton(xbox, 3);
	Button y = new JoystickButton(xbox, 4);
	Button RB = new JoystickButton(xbox, 5);
	Button LB = new JoystickButton (xbox, 6);
	Button back = new JoystickButton(xbox, 7);
	Button start = new JoystickButton(xbox, 8);
	Button toggle1 = new JoystickButton (xbox, 9);
	Button toggle2 = new JoystickButton(xbox, 10);
	
	public Joystick getJoystick1() {
		return xbox;
	}
	
	public OI() {
		RB.whenPressed(new EnvelopeCommand(true));
		LB.whenPressed(new EnvelopeCommand(false));
		x.whenPressed(new ShiftGearCommand(true));
		y.whenPressed(new ShiftGearCommand(false));
	}
}
