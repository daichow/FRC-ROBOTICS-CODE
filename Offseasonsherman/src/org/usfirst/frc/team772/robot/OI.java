package org.usfirst.frc.team772.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static Joystick joystick1 = new Joystick(0);
	
	public OI(){
		
		
	}
	public Joystick getJoystick1() {
		return joystick1;
	}
}

