package org.usfirst.frc.team772.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team772.robot.commands.AutoDriveCommand;
import org.usfirst.frc.team772.robot.commands.AutoDriveUltraSonicForwardCommand;
import org.usfirst.frc.team772.robot.commands.AutoDriveStraightCommand;
import org.usfirst.frc.team772.robot.commands.AutoTurnCommand;
import org.usfirst.frc.team772.robot.commands.EnvelopeCommand;
import org.usfirst.frc.team772.robot.commands.GearShiftCommand;
import org.usfirst.frc.team772.robot.commands.RestCommand;
import org.usfirst.frc.team772.robot.commands.WaitCommand;
import org.usfirst.frc.team772.robot.commands.IntakeCommand;
import org.usfirst.frc.team772.robot.commands.LifterCommand;
import org.usfirst.frc.team772.robot.commands.ShootCommand;
import org.usfirst.frc.team772.robot.commands.TestCommand;
import org.usfirst.frc.team772.robot.commands.TurnWithPIDCommand;
import org.usfirst.frc.team772.robot.commands.group.AutonomousCommand;
import org.usfirst.frc.team772.robot.commands.group.TrackCommand;
import org.usfirst.frc.team772.robot.commands.vision.TargetSortCommand;
import org.usfirst.frc.team772.robot.commands.vision.VisionFunctionCommand;
import org.usfirst.frc.team772.robot.commands.vision.VisionTargetCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

public class OI {
	Joystick joystick1 = new Joystick(0);
	Button j1button1 = new JoystickButton(joystick1, 1);
	Button j1button2 = new JoystickButton(joystick1, 2);
	Button j1button3 = new JoystickButton(joystick1, 3);
	Button j1button4 = new JoystickButton(joystick1, 4);
	Button j1button5 = new JoystickButton(joystick1, 5);
	Button j1button6 = new JoystickButton(joystick1, 6);
	Button j1button7 = new JoystickButton(joystick1, 7);
	Button j1button8 = new JoystickButton(joystick1, 8);
	
	Joystick joystick2 = new Joystick(1);
	Button j2button1 = new JoystickButton(joystick2, 1);
	Button j2button2 = new JoystickButton(joystick2, 2);
	Button j2button3 = new JoystickButton(joystick2, 3);
	Button j2button4 = new JoystickButton(joystick2, 4);
	Button j2button5 = new JoystickButton(joystick2, 5);
	Button j2button6 = new JoystickButton(joystick2, 6);
	Button j2button7 = new JoystickButton(joystick2, 7);
	
	public OI(){

//		j1button1.whenPressed(new VisionFunctionCommand("snapshot"));
//		j1button2.whenPressed(new VisionFunctionCommand("fuelmode"));
//		j1button3.whenPressed(new VisionFunctionCommand("gearmode"));
//		j1button4.whenPressed(new TrackCommand());
//		j1button5.whenPressed(new IntakeCommand(false));
//		j1button6.whileHeld(new AutoDriveStraightCommand());
//		j1button7.whenPressed(new EnvelopeCommand(true));
//		j1button8.whenPressed(new EnvelopeCommand(false));

		j1button6.whileHeld(new ShootCommand(true,0.95));//72
		j1button5.whileHeld(new ShootCommand(true,0.72));//72
		j1button1.whenPressed(new EnvelopeCommand(true));
		j1button2.whenPressed (new EnvelopeCommand(false));
		j1button3.whenPressed(new GearShiftCommand(true));//lowgear
		j1button4.whenPressed(new GearShiftCommand(false));//highgear
		j1button7.whenPressed(new RestCommand());//highgear
		j1button8.whenPressed(new AutoDriveCommand(5));
//		
		j2button1.whileHeld(new IntakeCommand(true));
		j2button5.whileHeld(new LifterCommand(.3));
		j2button6.whileHeld(new LifterCommand(1));
		j2button7.whileHeld(new IntakeCommand(false));
		
	}
	
	public Joystick getJoystick1(){
		return joystick1; // returns joystick1 values
	}
}
