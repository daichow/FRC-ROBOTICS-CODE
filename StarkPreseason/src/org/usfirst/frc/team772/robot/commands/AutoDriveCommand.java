package org.usfirst.frc.team772.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoDriveCommand extends CommandGroup {
    
    public  AutoDriveCommand() {
       addSequential(new DriveCommand("Auto", -1), 2);
       addSequential(new AutoIntakeCommand());
       addSequential(new DriveCommand("Auto", 1), 2);
       addSequential(new ShootCommand());
    }
}
