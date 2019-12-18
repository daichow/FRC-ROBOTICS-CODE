package org.usfirst.frc.team772.robot.commands.group;

import org.usfirst.frc.team772.robot.OI;
import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.commands.RumbleCommand;
import org.usfirst.frc.team772.robot.commands.WaitCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BallInTeleRumbleCommand extends CommandGroup {
    
    public  BallInTeleRumbleCommand() {
    	addSequential(new BallInTeleCommand());
    	addSequential(new WaitCommand(), 0.75);
		addSequential(new RumbleCommand(OI.joystick1, Robot.ultrasonicSubsystem.isBallInShooter()), 0.75);
    }
}