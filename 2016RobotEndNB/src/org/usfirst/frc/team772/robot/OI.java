package org.usfirst.frc.team772.robot;

import org.usfirst.frc.team772.robot.Robot.Direction;
import org.usfirst.frc.team772.robot.Robot.Mode;
import org.usfirst.frc.team772.robot.commands.CancelCommand;
import org.usfirst.frc.team772.robot.commands.CheckShootCommand;
import org.usfirst.frc.team772.robot.commands.FlywheelCommand;
import org.usfirst.frc.team772.robot.commands.IntakeCommand;
import org.usfirst.frc.team772.robot.commands.TipperCommand;
import org.usfirst.frc.team772.robot.commands.TurretCommand;
import org.usfirst.frc.team772.robot.commands.TurretFixCommand;
import org.usfirst.frc.team772.robot.commands.VisionCommand;
import org.usfirst.frc.team772.robot.commands.group.BallInTeleRumbleCommand;
import org.usfirst.frc.team772.robot.commands.group.BallOutCommand;
import org.usfirst.frc.team772.robot.commands.group.FeedPickUpCommand;
import org.usfirst.frc.team772.robot.commands.group.JustShootCommand;
import org.usfirst.frc.team772.robot.commands.group.TeleShootingCommand;
import org.usfirst.frc.team772.robot.subsystems.TurretSubsystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	// Driver 1 joystick
	public static Joystick joystick1 = new Joystick(0);
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
		
	public OI(){
		j1button1.whenPressed(new FeedPickUpCommand()); // auto pick up
		j1button2.whenPressed(new TipperCommand(Direction.Reverse, Mode.Auto)); // tipper down auto
		j1button3.whenPressed(new TipperCommand(Direction.Forward, Mode.Auto)); // tipper up auto
		j1button4.whenPressed(new BallInTeleRumbleCommand()); // ball intake - shooter with rumble
		j1button5.whileHeld(new IntakeCommand(Direction.Reverse, Mode.Manual)); // intake reverse
		j1button6.whileHeld(new IntakeCommand(Direction.Forward, Mode.Manual)); // intake forward
		j1button7.whileHeld(new TipperCommand(Direction.Reverse, Mode.Manual)); // tipper up manual
		j1button8.whileHeld(new TipperCommand(Direction.Forward, Mode.Manual)); // tipper down manual
		j1button9.whileHeld(new CancelCommand());
		j1button10.whenPressed(new BallOutCommand()); // ball shooter - intake
		
		j2button1.whenPressed(new TeleShootingCommand()); // aim shoot
		j2button2.whileHeld(new VisionCommand()); // turns on vision
		j2button3.whenPressed(new TurretCommand(TurretSubsystem.CENTER, Mode.Auto));
//		j2button4.whileHeld(new VisionTurretCommand());
		j2button4.whileHeld(new TurretFixCommand(true));
		j2button4.whenReleased(new CheckShootCommand());
		j2button5.whileHeld(new TurretCommand(TurretSubsystem.LEFT, Mode.Manual));
		j2button6.whileHeld(new TurretCommand(TurretSubsystem.RIGHT, Mode.Manual));
		j2button7.whenPressed(new FlywheelCommand(Direction.Forward)); // fly forward
		j2button8.whenPressed(new JustShootCommand());
		j2button9.whenPressed(new FlywheelCommand(Direction.Stop));
		j2button10.whenPressed(new FlywheelCommand(Direction.Reverse));
		
//		j3button1.whenPressed(new AutoVisionTurretCommand()); // aims
////		j3button1.whenPressed(new DriveEncoderCommand());
////		j3button2.whileHeld(new VisionCommand()); // turns on vision
//		j3button2.whenPressed(new DriveUltraCommand(40));
////		j3button3.whenPressed(new DriveUltraCommand(80));
//		j3button3.whenPressed(new RotateCommand(0));
//		j3button4.whileHeld(new VisionTurretCommand());
//		j3button5.whileHeld(new TurretCommand(TurretSubsystem.LEFT, Mode.Manual));
//		j3button6.whileHeld(new TurretCommand(TurretSubsystem.RIGHT, Mode.Manual));
//		j3button7.whenPressed(new FlywheelCommand(Direction.Forward)); // fly forward
//		j3button8.whenPressed(new JustShootCommand()); // shoot
//		j3button9.whenPressed(new FlywheelCommand(Direction.Stop)); // fly stop
//		j3button10.whenPressed(new FlywheelCommand(Direction.Reverse));
	}

	public Joystick getJoystick1() {
		return joystick1;
	}
	
	public Joystick getJoystick2() {
		return joystick2;
	}
	
//	public Joystick getJoystick3() {
//		return joystick3;
//	}
}