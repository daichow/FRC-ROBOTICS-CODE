package org.usfirst.frc.team772.robot.commands.group;

import org.usfirst.frc.team772.robot.commands.WaitCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class WaitShootCommand extends CommandGroup {
    
    public  WaitShootCommand() {
    	addSequential(new WaitCommand(), 1);
    	addSequential(new JustShootCommand());
    }
}
