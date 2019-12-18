package org.usfirst.frc.team772.robot.commands.group;

import org.usfirst.frc.team772.robot.commands.ShootCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class JustShootCommand extends CommandGroup {
    
    public  JustShootCommand() {
    	addSequential(new ShootCommand(), 0.5);
    }
}