package org.usfirst.frc.team772.robot;

import org.usfirst.frc.team772.robot.Robot.Direction;
import org.usfirst.frc.team772.robot.commands.LiftCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

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
	
	public OI() {		
//		j1button1.whenPressed(new FeedPickUpCommand()); // auto pickup
//		j1button2.whenPressed(new TipperCommand(Direction.Reverse, Mode.Auto)); // tipper down auto
//		j1button3.whenPressed(new TipperCommand(Direction.Forward, Mode.Auto)); // tipper up auto
//		j1button4.whenPressed(new BallInTeleCommand()); // ball in
//		j1button5.whileHeld(new IntakeCommand(Direction.Reverse, Mode.Manual)); // intake out
//		j1button6.whileHeld(new IntakeCommand(Direction.Forward, Mode.Manual)); // intake in
//		j1button7.whileHeld(new TipperCommand(Direction.Reverse, Mode.Manual)); // manual tipper down
//		j1button8.whileHeld(new TipperCommand(Direction.Forward, Mode.Manual)); // manual tipper up
//		j1button10.whenPressed(new BallOutCommand()); // ball out
//		
//		j2button1.whenPressed(new ShootingCommand()); // aim shoot
//		j2button2.whenPressed(new TurretCommand(TurretPos.RIGHT, Mode.Auto)); // turret right
//		j2button3.whenPressed(new TurretCommand(TurretPos.LEFT, Mode.Auto)); // turret left
//		j2button4.whenPressed(new TurretCommand(TurretPos.CENTER, Mode.Auto)); // turret center
//		j2button5.whileHeld(new TurretCommand(TurretPos.LEFT, Mode.Manual)); // manual turret left
//		j2button6.whileHeld(new TurretCommand(TurretPos.RIGHT, Mode.Manual)); // manual turret right
//		j2button7.whenPressed(new FlywheelCommand(Direction.Forward)); // flywheel forward
//		j2button8.whenPressed(new VisionTurretCommand()); // aim
//		j2button9.whenPressed(new FlywheelCommand(Direction.Stop)); // flywheel stop
//		j2button10.whenPressed(new FlywheelCommand(Direction.Reverse)); // flywheel reverse
//		
		j3button1.whenPressed(new LiftCommand(Direction.Forward, true)); // front up
		j3button2.whenPressed(new LiftCommand(Direction.Reverse, true)); // front down
		j3button3.whenPressed(new LiftCommand(Direction.Forward, false)); // back up
		j3button4.whenPressed(new LiftCommand(Direction.Reverse, false)); // back down
//		j3button1.whenPressed(new VisionTurretCommand());
//		j3button2.whenPressed(new FlywheelCommand(Direction.Forward));
//		j3button3.whenPressed(new JustShootCommand());
//		j3button4.whenPressed(new FlywheelCommand(Direction.Stop));
//		j3button5.whileHeld(new TurretCommand(TurretPos.LEFT, Mode.Manual));
//		j3button6.whileHeld(new TurretCommand(TurretPos.RIGHT, Mode.Manual));
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