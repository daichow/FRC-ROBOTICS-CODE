package org.usfirst.frc.team772.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoOuttakeCommand extends CommandGroup {
    
    public  AutoOuttakeCommand() {
       addParallel(new ConveyerCommand(false));
       addParallel(new IntakeCommand(false), 2);
       
    }
}
