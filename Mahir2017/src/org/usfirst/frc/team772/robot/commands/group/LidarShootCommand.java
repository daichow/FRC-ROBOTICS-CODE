package org.usfirst.frc.team772.robot.commands.group;

import org.usfirst.frc.team772.robot.commands.AutoDriveUltraSonicBackwardCommand;
import org.usfirst.frc.team772.robot.commands.AutoTurnCommand;
import org.usfirst.frc.team772.robot.commands.AutoTurnCommand;
import org.usfirst.frc.team772.robot.commands.LightCommand;
import org.usfirst.frc.team772.robot.commands.ShootCommand;
import org.usfirst.frc.team772.robot.commands.TurnWithPIDCommand;
import org.usfirst.frc.team772.robot.commands.visionBoiler.TargetBoilerSortCommand;
import org.usfirst.frc.team772.robot.commands.visionBoiler.VisionBoilerTargetCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LidarShootCommand extends CommandGroup {

    public LidarShootCommand() {
    	addParallel(new LightCommand(0.25));
//		addSequential(new VisionBoilerTargetCommand("targets"));
//		addSequential(new TargetBoilerSortCommand());
//		addSequential(new AutoTurnCommand());
    	addSequential(new AutoDriveUltraSonicBackwardCommand(-0.8, 12, "front"));
    	addSequential(new TurnWithPIDCommand(-5));
		addSequential(new ShootCommand(true,0.805));
    }
}
