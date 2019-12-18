package org.usfirst.frc.team772.robot.commands.group;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoVisionTurretCommand extends CommandGroup {
    
	// turns on vision then tracks
	
    public  AutoVisionTurretCommand() {
    	addSequential(new DelayedAimCommand());
    }
}