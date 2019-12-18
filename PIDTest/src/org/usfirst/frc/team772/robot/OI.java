package org.usfirst.frc.team772.robot;

import org.usfirst.frc.team772.robot.commands.ManualTurretCommand;
import org.usfirst.frc.team772.robot.commands.TurretCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    
	Joystick joystick1 = new Joystick(0);
	JoystickButton j1button1 = new JoystickButton(joystick1, 1);
	JoystickButton j1button2 = new JoystickButton(joystick1, 2);
	JoystickButton j1button3 = new JoystickButton(joystick1, 3);
	JoystickButton j1button4 = new JoystickButton(joystick1, 4);
	JoystickButton j1button5 = new JoystickButton(joystick1, 5);
	JoystickButton j1button6 = new JoystickButton(joystick1, 6);
	
	public OI(){
		j1button3.whenPressed(new TurretCommand(Robot.TurretPos.LEFT));
		j1button4.whenPressed(new TurretCommand(Robot.TurretPos.CENTER));
		j1button2.whenPressed(new TurretCommand(Robot.TurretPos.RIGHT));
		j1button5.whileHeld(new ManualTurretCommand(false));
		j1button6.whileHeld(new ManualTurretCommand(true));
		
	}
	
}

