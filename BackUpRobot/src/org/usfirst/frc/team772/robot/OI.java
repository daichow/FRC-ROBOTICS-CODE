package org.usfirst.frc.team772.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	// Driver 1 joystick
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

		// Driver 2 joystick
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
			
		// Test joystick
		Joystick joystick3 = new Joystick(2);
		Button j3button1 = new JoystickButton(joystick3, 1);
		Button j3button2 = new JoystickButton(joystick3, 2);
		Button j3button3 = new JoystickButton(joystick3, 3);
		Button j3button4 = new JoystickButton(joystick3, 4);
		Button j3button5 = new JoystickButton(joystick3, 5);
		Button j3button6 = new JoystickButton(joystick3, 6);
		Button j3button7 = new JoystickButton(joystick3, 7);
		Button j3button8 = new JoystickButton(joystick3, 8);
		Button j3button9 = new JoystickButton(joystick3, 9);
		Button j3button10 = new JoystickButton(joystick3, 10);
		
		public OI(){
			
		}

		public Joystick getJoystick1() {
			return joystick1;
		}
		
		public Joystick getJoystick2() {
			return joystick2;
		}
		
		public Joystick getJoystick3() {
			return joystick3;
		}
}

