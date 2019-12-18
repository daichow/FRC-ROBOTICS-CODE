package org.usfirst.frc.team772.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team772.robot.commands.AgitatorCommand;
//import org.usfirst.frc.team772.robot.commands.AgitatorCommand;
import org.usfirst.frc.team772.robot.commands.AutoDriveCommand;
import org.usfirst.frc.team772.robot.commands.AutoDriveUltraSonicForwardCommand;
import org.usfirst.frc.team772.robot.commands.AutoTurnCommand;
import org.usfirst.frc.team772.robot.commands.EnvelopeCommand;
import org.usfirst.frc.team772.robot.commands.GearShiftCommand;
import org.usfirst.frc.team772.robot.commands.LightCommand;
import org.usfirst.frc.team772.robot.commands.RestCommand;
import org.usfirst.frc.team772.robot.commands.WaitCommand;
import org.usfirst.frc.team772.robot.commands.IntakeCommand;
import org.usfirst.frc.team772.robot.commands.LifterCommand;
import org.usfirst.frc.team772.robot.commands.ShootCommand;
import org.usfirst.frc.team772.robot.commands.TestCommand;
import org.usfirst.frc.team772.robot.commands.TurnWithPIDCommand;
import org.usfirst.frc.team772.robot.commands.group.AutonomousCommand;
import org.usfirst.frc.team772.robot.commands.group.VisionLidarShootCommand;
import org.usfirst.frc.team772.robot.commands.group.LidarShootCommand;
import org.usfirst.frc.team772.robot.commands.visionBoiler.OpenBoilerSocketCommand;
import org.usfirst.frc.team772.robot.commands.visionBoiler.TargetBoilerSortCommand;
import org.usfirst.frc.team772.robot.commands.visionBoiler.VisionBoilerFunctionCommand;
import org.usfirst.frc.team772.robot.commands.visionBoiler.VisionBoilerTargetCommand;
import org.usfirst.frc.team772.robot.commands.visionGear.OpenGearSocketCommand;
import org.usfirst.frc.team772.robot.commands.visionGear.VisionGearFunctionCommand;
import org.usfirst.frc.team772.robot.commands.visionGear.VisionGearTargetCommand;

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
	Button j2button8 = new JoystickButton(joystick2, 8);
	
	Joystick joystick3 = new Joystick(1);
	Button j3button1 = new JoystickButton(joystick3, 1);
	Button j3button2 = new JoystickButton(joystick3, 2);
	Button j3button3 = new JoystickButton(joystick3, 3);
	Button j3button4 = new JoystickButton(joystick3, 4);
	Button j3button5 = new JoystickButton(joystick3, 5);
	Button j3button6 = new JoystickButton(joystick3, 6);
	Button j3button7 = new JoystickButton(joystick3, 7);
	
	public OI(){
		j1button1.whenPressed(new EnvelopeCommand(true));
		j1button2.whenPressed (new EnvelopeCommand(false));
		j1button3.whenPressed(new GearShiftCommand(true));//lowgear
		j1button4.whenPressed(new GearShiftCommand(false));//highgear
		j1button5.whileHeld(new VisionLidarShootCommand());//positive was 83//A
		j1button6.whileHeld(new LidarShootCommand());//A
		j1button7.whileHeld(new ShootCommand(true, .805));//positive//A
//		j1button7.whenPressed(new AutoDriveUltraSonicForwardCommand(.8, 6));
//		j1button8.whileHeld(new TestCommand());
//		j1button7.whenPressed(new OpenGearSocketCommand());
//		j1button8.whileHeld(new OpenBoilerSocketCommand());
			
		j2button1.whileHeld(new IntakeCommand(true));//A
		j2button2.whenPressed(new VisionBoilerTargetCommand("targets"));//A
//		j2button3.whileHeld(new VisionGearTargetCommand("targets"));
		j2button4.whileHeld(new LidarShootCommand());//A
		j2button5.whileHeld(new LifterCommand(.4));
		j2button6.whileHeld(new LifterCommand(1));
		j2button7.whileHeld(new IntakeCommand(false));
		j2button8.whileHeld(new AgitatorCommand());//A
//		j2button7.whileHeld(new VisionGearFunctionCommand("snapshot"));
//		j2button8.whileHeld(new VisionBoilerFunctionCommand("snapshot"));
//		j2button8.whileHeld(new SpitOutGearCommand());
		
//		j2button2.whenPressed(new VisionBoilerTargetCommand("targets"));
//		j2button3.whenPressed(new TargetBoilerSortCommand());
//		j2button4.whenPressed(new VisionBoilerFunctionCommand("snapshot"));
		

//		j3button1.whenPressed(new VisionTargetCommand("10.7.72.9", "targets"));
//		j3button2.whenPressed(new VisionTargetCommand("10.7.72.10", "targets"));
//		j3button3.whenPressed(new VisionFunctionCommand("10.7.72.9", "snapshot"));
//		j3button4.whenPressed(new VisionFunctionCommand("10.7.72.10", "snapshot"));
	}
	
	public Joystick getJoystick1(){
		return joystick1; // returns joystick1 values 
	}
}
