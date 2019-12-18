package org.usfirst.frc.team772.robot.commands.group;

import org.usfirst.frc.team772.robot.commands.VisionCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoVisionTurretCommand extends CommandGroup {
    
	// turns on vision then tracks
	
    public  AutoVisionTurretCommand() {
    	addParallel(new VisionCommand(), 5);
    	addSequential(new DelayedAimCommand());
    }
}