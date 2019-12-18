package org.usfirst.frc.team772.robot.commands.group;

import org.usfirst.frc.team772.robot.Robot.Direction;
import org.usfirst.frc.team772.robot.commands.LiftCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoLiftCommand extends CommandGroup {
    
    public  AutoLiftCommand() {
    	addSequential(new LiftCommand(Direction.Forward, false), 2);
//    	addSequential(new DriveSpeedCommand(0.5), 1);
    	addParallel(new LiftCommand(Direction.Reverse, false), 2);
//    	addSequential(new WaitCommand(), 0.25);
    	addSequential(new LiftCommand(Direction.Forward, true), 2);
    }
}