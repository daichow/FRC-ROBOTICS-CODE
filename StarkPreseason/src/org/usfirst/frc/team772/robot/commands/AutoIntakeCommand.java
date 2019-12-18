package org.usfirst.frc.team772.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoIntakeCommand extends CommandGroup {
    
    public  AutoIntakeCommand() {
       addSequential (new ArmCommand("Manual", true));
       addParallel(new IntakeCommand(true), 2);
       addSequential(new ConveyerCommand(true));
       addSequential(new ArmCommand("Manual", false));
       
       

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
