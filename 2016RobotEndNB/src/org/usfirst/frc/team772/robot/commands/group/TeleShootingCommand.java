package org.usfirst.frc.team772.robot.commands.group;

import org.usfirst.frc.team772.robot.Robot.Direction;
import org.usfirst.frc.team772.robot.commands.CheckShootCommand;
import org.usfirst.frc.team772.robot.commands.FlywheelCommand;
import org.usfirst.frc.team772.robot.commands.VisionCommand;
import org.usfirst.frc.team772.robot.commands.WaitFlyCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TeleShootingCommand extends CommandGroup {
    
    public  TeleShootingCommand() {
    	addSequential(new FlywheelCommand(Direction.Forward));
    	addParallel(new VisionCommand(), 5);
		addSequential(new AutoTurretFixCommand(false), 5);
		addSequential(new WaitFlyCommand(), 2);
		addSequential(new CheckShootCommand(), 0.5);
		addSequential(new FlywheelCommand(Direction.Stop));
    }
}