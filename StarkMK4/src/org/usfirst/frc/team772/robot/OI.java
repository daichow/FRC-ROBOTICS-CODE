package org.usfirst.frc.team772.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team772.robot.commands.AutoShootCommand;
import org.usfirst.frc.team772.robot.commands.CancelCommand;
import org.usfirst.frc.team772.robot.commands.ConveyorShootCommand;
import org.usfirst.frc.team772.robot.commands.FlywheelCommand;
import org.usfirst.frc.team772.robot.commands.IntakePneumaticCommand;
import org.usfirst.frc.team772.robot.commands.IntakeWheelCommand;
import org.usfirst.frc.team772.robot.commands.ManualConveyorCommand;
import org.usfirst.frc.team772.robot.commands.TurretCommand;
import org.usfirst.frc.team772.robot.commands.UltrasonicTestCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//joystick 1 -> main joystick
	Joystick joystick1 = new Joystick(0);
	Button j1button1 = new JoystickButton(joystick1, 1);
	Button j1button2 = new JoystickButton(joystick1, 2);
	Button j1button3 = new JoystickButton(joystick1, 3);
	Button j1button4 = new JoystickButton(joystick1, 4);
	Button j1button5 = new JoystickButton(joystick1, 5);
	Button j1button6 = new JoystickButton(joystick1, 6);
	Button j1button7 = new JoystickButton(joystick1, 7);
	Button j1button8 = new JoystickButton(joystick1, 8);
	Button j1button9 = new JoystickButton(joystick1, 9);
	Button j1button10 = new JoystickButton(joystick1, 10);
	
	//joystick 2 -> for testing purposes
	Joystick joystick2 = new Joystick(1);
	Button j2button1 = new JoystickButton(joystick2, 1);
	Button j2button2 = new JoystickButton(joystick2, 2);
	Button j2button3 = new JoystickButton(joystick2, 3);
	Button j2button4 = new JoystickButton(joystick2, 4);
	Button j2button5 = new JoystickButton(joystick2, 5);
	Button j2button6 = new JoystickButton(joystick2, 6);
	Button j2button7 = new JoystickButton(joystick2, 7);
	Button j2button8 = new JoystickButton(joystick2, 8);
	Button j2button9 = new JoystickButton(joystick2, 9);
	Button j2button10 = new JoystickButton(joystick2, 10);
	
	public OI(){
		j1button1.whenPressed(new FlywheelCommand());//toggles flywheel on and off
		j1button2.whenPressed(new AutoShootCommand());//activates flywheel and fires ball when flywheel is ramped up
		j1button3.whenPressed(new IntakePneumaticCommand());//toggles pneumatics on intake
		j1button4.whenPressed(new TurretCommand());//toggles shooterhood up and down
		j1button5.whenPressed(new IntakeWheelCommand(true));//ball in	
		j1button6.whenPressed(new IntakeWheelCommand(false));//ball out
		j1button7.whileHeld(new ManualConveyorCommand(true));//manually moves convayor forward
		j1button8.whileHeld(new ManualConveyorCommand(false));//manually moves convayor backwards
	}
	
	public Joystick getJoystick1(){
		return joystick1;
	}
}

