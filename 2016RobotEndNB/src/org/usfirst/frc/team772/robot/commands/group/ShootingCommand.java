package org.usfirst.frc.team772.robot.commands.group;

import org.usfirst.frc.team772.robot.Robot.Direction;
import org.usfirst.frc.team772.robot.Robot.Mode;
import org.usfirst.frc.team772.robot.commands.CheckShootCommand;
import org.usfirst.frc.team772.robot.commands.FlywheelCommand;
import org.usfirst.frc.team772.robot.commands.TurretCommand;
import org.usfirst.frc.team772.robot.subsystems.TurretSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootingCommand extends CommandGroup {
    
    public  ShootingCommand() {
    	addSequential(new FlywheelCommand(Direction.Forward));
    	addSequential(new AutoVisionTurretCommand()); // aim
    	addSequential(new CheckShootCommand(), 0.5);
    	addSequential(new FlywheelCommand(Direction.Stop)); // stops flywheel
		addSequential(new TurretCommand(TurretSubsystem.CENTER, Mode.Auto)); // turns turret to center
    }
}