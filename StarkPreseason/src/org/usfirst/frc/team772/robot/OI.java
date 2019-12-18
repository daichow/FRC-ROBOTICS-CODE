package org.usfirst.frc.team772.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team772.robot.commands.ArmCommand;
import org.usfirst.frc.team772.robot.commands.AutoIntakeCommand;
import org.usfirst.frc.team772.robot.commands.AutoOuttakeCommand;
import org.usfirst.frc.team772.robot.commands.ConveyerCommand;
import org.usfirst.frc.team772.robot.commands.ExampleCommand;
import org.usfirst.frc.team772.robot.commands.FlywheelCommand;
import org.usfirst.frc.team772.robot.commands.HoodCommand;

import org.usfirst.frc.team772.robot.commands.ShootCommand;
import org.usfirst.frc.team772.robot.commands.IntakeCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static Joystick joystick1 = new Joystick(0);
	Button j1button1 = new JoystickButton(joystick1, 1);
	Button j1button2 = new JoystickButton(joystick1, 2);
    Button j1button3 = new JoystickButton(joystick1, 3);
    Button j1button4 = new JoystickButton(joystick1,4);
    Button j1button5 = new JoystickButton (joystick1,5);
    Button j1button6 = new JoystickButton (joystick1,6);
	Button j1button7 = new JoystickButton (joystick1, 7);
	Button j1button8 = new JoystickButton (joystick1, 8);
	Button j1button10 = new JoystickButton (joystick1, 10);
	public OI(){
		j1button1.whenPressed(new ShootCommand());
		j1button2.whileHeld(new IntakeCommand(true));
		j1button3.whenPressed(new ArmCommand("Auto", true));
		j1button5.whenPressed(new ConveyerCommand(true));
		j1button6.whenPressed(new ConveyerCommand(false));
		j1button7.whenPressed(new FlywheelCommand(false));
		j1button8.whenPressed(new AutoOuttakeCommand());
		j1button10.whenPressed(new AutoIntakeCommand());
		
	}
	
	public Joystick getJoystick1() {
		return joystick1;
	}
}

