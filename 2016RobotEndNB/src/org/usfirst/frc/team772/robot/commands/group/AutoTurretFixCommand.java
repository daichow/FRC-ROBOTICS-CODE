package org.usfirst.frc.team772.robot.commands.group;

import org.usfirst.frc.team772.robot.commands.TurretFixCommand;
import org.usfirst.frc.team772.robot.commands.WaitCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoTurretFixCommand extends CommandGroup {
    
	boolean slow = true;
	
    public  AutoTurretFixCommand(boolean slow) {
    	addSequential(new WaitCommand(), 0.5);
    	addSequential(new TurretFixCommand(slow));
    	addSequential(new TurretFixCommand(slow));
    	addSequential(new TurretFixCommand(slow));
    	addSequential(new TurretFixCommand(slow));
    	addSequential(new TurretFixCommand(slow));
    	addSequential(new TurretFixCommand(slow));
    	addSequential(new TurretFixCommand(slow));
    }
}