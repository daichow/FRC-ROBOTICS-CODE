package org.usfirst.frc.team772.robot.commands.group;

import java.time.Duration;
import java.time.Instant;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.RobotMap;
import org.usfirst.frc.team772.robot.commands.AutoDriveCommand;
import org.usfirst.frc.team772.robot.commands.AutoDriveUltraSonicBackwardCommand;
import org.usfirst.frc.team772.robot.commands.AutoDriveUltraSonicForwardCommand;
import org.usfirst.frc.team772.robot.commands.AutoTurnCommand;
import org.usfirst.frc.team772.robot.commands.WaitCommand;
import org.usfirst.frc.team772.robot.commands.LightCommand;
import org.usfirst.frc.team772.robot.commands.ShootCommand;
import org.usfirst.frc.team772.robot.commands.TestCommand;
import org.usfirst.frc.team772.robot.commands.TurnWithoutPIDCommand;
import org.usfirst.frc.team772.robot.commands.TurnWithPIDCommand;
import org.usfirst.frc.team772.robot.commands.vision.TargetSortCommand;
import org.usfirst.frc.team772.robot.commands.vision.VisionFunctionCommand;
import org.usfirst.frc.team772.robot.commands.vision.VisionTargetCommand;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TrackCommand extends CommandGroup {

    public TrackCommand() {
    	SmartDashboard.putString("DB/String 3", "bread and butter");
    	addParallel(new LightCommand(0.25));
    	addSequential(new AutoDriveUltraSonicForwardCommand(.6, 15));
    	addSequential(new WaitCommand(1.5));
    	if(Robot.envelopeSubsystem.gearCheck()){
    		addSequential(new AutoDriveUltraSonicBackwardCommand(-.7, 35));
//    		addSequential(new WaitCommand(.5));
    		addSequential(new VisionTargetCommand("targets"));
    		addSequential(new TargetSortCommand());
    		addSequential(new AutoTurnCommand());
    		addSequential(new AutoDriveUltraSonicForwardCommand(.5, 10));
    		addSequential(new WaitCommand(1.5));
    	}
    	addSequential(new AutoDriveCommand(-1));
    	addSequential(new TurnWithPIDCommand(-100));
    	addSequential(new AutoDriveUltraSonicForwardCommand(1, 45));
    	addSequential(new ShootCommand(true,.95));
    	
    }
}
//works bread and butter
//SmartDashboard.putString("DB/String 3", "bread and butter");
//addParallel(new LightCommand(0.25));
//addSequential(new AutoDriveUltraSonicForwardCommand(.6, 15));
//addSequential(new WaitCommand(1.5));
//if(Robot.envelopeSubsystem.gearCheck()){
//	addSequential(new AutoDriveUltraSonicBackwardCommand(-.7, 35));
////	addSequential(new WaitCommand(.5));
//	addSequential(new VisionTargetCommand("targets"));
//	addSequential(new TargetSortCommand());
//	addSequential(new AutoTurnCommand());
//	addSequential(new AutoDriveUltraSonicForwardCommand(.5, 10));
//	addSequential(new WaitCommand(1.5));
//}
//addSequential(new AutoDriveCommand(-1));
//addSequential(new TurnWithPIDCommand(-100));
//addSequential(new AutoDriveUltraSonicForwardCommand(1, 45));
//addSequential(new ShootCommand(true));

//works get balls and shoot 
//addSequential(new AutoDriveCommand(-7));
//addSequential(new TurnWithPIDCommand(90));
//addSequential(new AutoDriveUltraSonicForwardCommand(.7, 15));
//addSequential(new AutoDriveUltraSonicBackwardCommand(-.5,2));
//addSequential(new TurnWithPIDCommand(-90));
//addParallel(new AutoDriveCommand(1));
//addSequential(new ShootCommand(true));

//works side gear
//addParallel(new LightCommand(0.45));
//addSequential(new AutoDriveCommand(5));
//addSequential(new TurnWithPIDCommand(60));    	
//addSequential(new VisionTargetCommand("targets"),0.5);
//addSequential(new TargetSortCommand(),0.5);
//addSequential(new AutoTurnCommand());
//addSequential(new AutoDriveUltraSonicForwardCommand(.5, 10));
//addSequential(new WaitCommand(1.5));
//if(Robot.envelopeSubsystem.gearCheck()){
//addSequential(new AutoDriveUltraSonicBackwardCommand(-.7, 35));
//Instant before2 = Instant.now();
//addSequential(new VisionTargetCommand("targets"),0.5);
//addSequential(new TargetSortCommand(),0.5);
//addSequential(new AutoTurnCommand());
//addSequential(new AutoDriveUltraSonicForwardCommand(.5, 10));
//addSequential(new WaitCommand(1.5));
//}else{
//
//}


