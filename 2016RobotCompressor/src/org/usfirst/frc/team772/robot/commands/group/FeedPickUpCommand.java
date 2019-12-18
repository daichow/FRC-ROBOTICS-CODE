package org.usfirst.frc.team772.robot.commands.group;

import org.usfirst.frc.team772.robot.Robot.Direction;
import org.usfirst.frc.team772.robot.Robot.Mode;
import org.usfirst.frc.team772.robot.Robot.TurretPos;
import org.usfirst.frc.team772.robot.commands.FlywheelCommand;
import org.usfirst.frc.team772.robot.commands.IntakeCommand;
import org.usfirst.frc.team772.robot.commands.TipperCommand;
import org.usfirst.frc.team772.robot.commands.TurretCommand;
import org.usfirst.frc.team772.robot.commands.WaitCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FeedPickUpCommand extends CommandGroup {    
	
	// auto pickup
	
    public  FeedPickUpCommand() {
    	addParallel(new TurretCommand(TurretPos.CENTER, Mode.Auto)); // tipper centres
    	addSequential(new IntakeCommand(Direction.Forward, Mode.Auto)); // intakes ball
    	addParallel(new IntakeCommand(Direction.Forward, Mode.Auto), 0.2);
    	addParallel(new TipperCommand(Direction.Forward, Mode.Auto)); // tipper goes up
    	addSequential(new FlywheelCommand(Direction.Reverse));
    	addSequential(new WaitCommand(), 1.5);
		addSequential(new BallInTeleRumbleCommand()); // puts ball into shooter then rumbles when in
    }
}