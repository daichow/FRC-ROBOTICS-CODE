package org.usfirst.frc.team772.robot.commands.group;

import org.usfirst.frc.team772.robot.commands.VisionTurretCommand;
import org.usfirst.frc.team772.robot.commands.WaitCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DelayedAimCommand extends CommandGroup {
    
    public  DelayedAimCommand() {
    	addSequential(new WaitCommand(), 1);
    	addSequential(new VisionTurretCommand(), 4);
    }
}