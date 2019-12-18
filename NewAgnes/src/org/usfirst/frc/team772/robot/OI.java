package org.usfirst.frc.team772.robot;

import org.usfirst.frc.team772.robot.commands.FlywheelCommand;
import org.usfirst.frc.team772.robot.commands.IntakeInCommad;
import org.usfirst.frc.team772.robot.commands.IntakeOutCommand;
import org.usfirst.frc.team772.robot.commands.ShooterCommand;
import org.usfirst.frc.team772.robot.commands.ShooterRetract;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
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
	
	// drive, intakes, shooter & flywheel
//	j1button1.whileHeld(new FlywheelCommand(true));
//	j1button2.whileHeld(new FlywheelCommand(false));
//	j1button3.whenPressed(new ShooterCommand());
//	j1button5.whileHeld(new IntakeCommand(true));
//	j1button6.whileHeld(new IntakeCommand(false));
	j1button1.whileHeld(new ShooterCommand());
	j1button2.whileHeld(new ShooterRetract());
	j1button3.whileHeld(new IntakeInCommad());
	j1button4.whileHeld(new IntakeOutCommand());
	j1button5.whileHeld(new FlywheelCommand());
}
public Joystick getJoystick1(){
	return joystick1; // returns joystick1 values
}
public Joystick getJoystick2(){
	return joystick2; // returns joystick1 values
}
}

