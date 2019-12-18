package org.usfirst.frc.team772.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShootCycleCommand extends CommandGroup {
    
    public  ShootCycleCommand() {
    	addParallel(new FlywheelCommand());
        addSequential(new IntakePneumaticCommand());
        addSequential(new IntakeWheelCommand(true));
        addSequential(new ConveyorShootCommand());
        addSequential(new FlywheelCommand());
    }
}
