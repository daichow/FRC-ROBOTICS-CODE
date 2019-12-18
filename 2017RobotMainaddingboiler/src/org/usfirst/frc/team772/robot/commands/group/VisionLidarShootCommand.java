package org.usfirst.frc.team772.robot.commands.group;

import org.usfirst.frc.team772.robot.commands.*;
import org.usfirst.frc.team772.robot.commands.visionBoiler.TargetBoilerSortCommand;
import org.usfirst.frc.team772.robot.commands.visionBoiler.VisionBoilerTargetCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class VisionLidarShootCommand extends CommandGroup {
    public VisionLidarShootCommand() {
    	addSequential(new LightCommand(0.125));
		addSequential(new VisionBoilerTargetCommand("targets"));
		addSequential(new TargetBoilerSortCommand());
		addSequential(new AutoTurnTeleCommand());
		addSequential(new ShootCommand(true,0.805));
    }
}
