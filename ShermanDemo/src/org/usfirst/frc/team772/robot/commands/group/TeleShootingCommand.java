package org.usfirst.frc.team772.robot.commands.group;

import org.usfirst.frc.team772.robot.Robot.Direction;
import org.usfirst.frc.team772.robot.Robot.Mode;
import org.usfirst.frc.team772.robot.commands.CheckShootCommand;
import org.usfirst.frc.team772.robot.commands.FlywheelCommand;
import org.usfirst.frc.team772.robot.commands.TurretCommand;
import org.usfirst.frc.team772.robot.commands.WaitAimCommand;
import org.usfirst.frc.team772.robot.commands.WaitCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TeleShootingCommand extends CommandGroup {
    
	// Aim shoot
	
    public  TeleShootingCommand() {
    	addSequential(new FlywheelCommand(Direction.Forward)); // fly on 0s
    	addSequential(new TurretCommand(20000, Mode.Auto, 1.5)); // turret aim 1.5s
//    	addParallel(new WaitAimCommand());
//    	addSequential(new AutoTurretFixCommand(false), 2);
    	addSequential(new WaitAimCommand()); // wait for conditions 2s
		addSequential(new CheckShootCommand()); // check conditions then shoot 0.5s
		addSequential(new WaitCommand(), 1); // wait 1 second 1s
		addSequential(new FlywheelCommand(Direction.Stop)); // fly stop 0s
    }
}