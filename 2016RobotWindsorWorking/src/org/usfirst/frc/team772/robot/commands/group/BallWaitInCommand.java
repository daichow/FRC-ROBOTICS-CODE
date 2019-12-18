package org.usfirst.frc.team772.robot.commands.group;

import org.usfirst.frc.team772.robot.Robot.Direction;
import org.usfirst.frc.team772.robot.Robot.Mode;
import org.usfirst.frc.team772.robot.commands.FlywheelCommand;
import org.usfirst.frc.team772.robot.commands.IntakeCommand;
import org.usfirst.frc.team772.robot.commands.WaitCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BallWaitInCommand extends CommandGroup {
    
    public  BallWaitInCommand() {
    	addSequential(new FlywheelCommand(Direction.Reverse));
    	addSequential(new WaitCommand(), 1);
    	addSequential(new IntakeCommand(Direction.Forward, Mode.Manual), 1);
    	addSequential(new FlywheelCommand(Direction.Stop));
    	addSequential(new IntakeCommand(Direction.Stop, Mode.Auto));	
    }
}