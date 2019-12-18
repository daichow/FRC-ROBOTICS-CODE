package org.usfirst.frc.team772.robot.commands.group;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.Robot.Direction;
import org.usfirst.frc.team772.robot.Robot.Mode;
import org.usfirst.frc.team772.robot.Robot.TurretPos;
import org.usfirst.frc.team772.robot.commands.FlywheelCommand;
import org.usfirst.frc.team772.robot.commands.TurretCommand;
import org.usfirst.frc.team772.robot.commands.VisionTurretCommand;
import org.usfirst.frc.team772.robot.commands.WaitCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootingCommand extends CommandGroup {
    
    public  ShootingCommand() {
    	if(Robot.intakeSubsystem.isBallInShooter()){ // only shoots when ball in shooter
    		addSequential(new VisionTurretCommand(), 2.5);
//    		addSequential(new ShooterCommand());
    		addSequential(new FlywheelCommand(Direction.Forward));
    		addSequential(new WaitCommand(), 3);
    		addSequential(new JustShootCommand());
    		addSequential(new WaitCommand(), 2.5);
    		addSequential(new FlywheelCommand(Direction.Stop));
    		addSequential(new TurretCommand(TurretPos.CENTER, Mode.Auto));
    	}    
    }
} 
