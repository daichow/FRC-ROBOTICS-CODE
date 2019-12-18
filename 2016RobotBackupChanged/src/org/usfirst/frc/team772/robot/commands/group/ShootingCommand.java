package org.usfirst.frc.team772.robot.commands.group;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.Robot.Mode;
import org.usfirst.frc.team772.robot.Robot.TurretPos;
import org.usfirst.frc.team772.robot.commands.TurretCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootingCommand extends CommandGroup {
    
	// aims then shoots if ball in shooter
	
    public  ShootingCommand() {
    	if(Robot.intakeSubsystem.isBallInShooter()){ // only shoots when ball in shooter
//    		addSequential(new VisionTurretCommand(), 2.5);
    		addSequential(new ShooterCommand());
    		addSequential(new TurretCommand(TurretPos.CENTER, Mode.Auto));
       }    
    }
}