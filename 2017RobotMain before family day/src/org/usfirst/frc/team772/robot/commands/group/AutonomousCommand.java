package org.usfirst.frc.team772.robot.commands.group;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.commands.AutoDriveCommand;
import org.usfirst.frc.team772.robot.commands.AutoDriveUltraSonicBackwardCommand;
import org.usfirst.frc.team772.robot.commands.AutoDriveUltraSonicForwardCommand;
import org.usfirst.frc.team772.robot.commands.AutoTurnCommand;
import org.usfirst.frc.team772.robot.commands.LightCommand;
import org.usfirst.frc.team772.robot.commands.ShootCommand;
import org.usfirst.frc.team772.robot.commands.TurnWithPIDCommand;
import org.usfirst.frc.team772.robot.commands.TurnWithoutPIDCommand;
import org.usfirst.frc.team772.robot.commands.WaitCommand;
import org.usfirst.frc.team772.robot.commands.vision.TargetSortCommand;
import org.usfirst.frc.team772.robot.commands.vision.VisionTargetCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutonomousCommand extends CommandGroup {

    public AutonomousCommand(int side, int autoMode, int delay) {
    	switch(side){
    	case 0: redSide(autoMode, delay);
    		break;
    	case 1: blueSide(autoMode, delay);
    		break;
    	default: defult();
			break;
    	}
    }
    
    /*
     * 
     * 
     * 
     * 
     */
    public void redSide(int autoMode, int delay){
    	switch(autoMode){
    	case 0: defult();
    		break;
    	case 1: autoMode1Red(delay);
    		break;
    	case 2: autoMode2Red(delay);
			break;
    	case 3: autoMode3Red(delay);
			break;
    	case 4: autoMode4Red(delay);
			break;
    	case 5: autoMode5Red(delay);
			break;
    	case 6: autoMode6Red(delay);
			break;
    	default: defult();
			break;
    	}
    }
    
    /*
     * 
     * 
     * 
     */
    public void blueSide(int autoMode, int delay){
    	switch(autoMode){
    	case 0: defult();
    		break;
    	case 1: autoMode1Blue(delay);
    		break;
    	case 2: autoMode2Blue(delay);
			break;
    	case 3: autoMode3Blue(delay);
			break;
    	case 4: autoMode4Blue(delay);
			break;
    	case 5: autoMode5Blue(delay);
			break;
    	case 6: autoMode6Blue(delay);
			break;
    	default: defult();
			break;
    	}
    }
    
    /*
     * red side auto modes
     * 
     * Mode1:Use for middle gear. Hangs gear then shoot. Human player has 1.5 seconds to get gear out
     * Mode2:Use for side gear(
     * 
     */
    public void autoMode1Red(int delay){//bread and butter
    	SmartDashboard.putString("DB/String 3", "Red, bread and butter");
    	addParallel(new LightCommand(0.25));
    	addSequential(new AutoDriveUltraSonicForwardCommand(.6, 15));
    	addSequential(new WaitCommand(1.5));
    	if(Robot.envelopeSubsystem.gearCheck()){
    		addSequential(new AutoDriveUltraSonicBackwardCommand(-.7, 35));
    		addSequential(new VisionTargetCommand("targets"));
    		addSequential(new TargetSortCommand());
    		addSequential(new AutoTurnCommand());
    		addSequential(new AutoDriveUltraSonicForwardCommand(.5, 10));
    		addSequential(new WaitCommand(1.5));
    	}
    	addSequential(new AutoDriveCommand(-1));
    	addSequential(new TurnWithPIDCommand(-100));
    	addSequential(new AutoDriveUltraSonicForwardCommand(1, 45));
    	addSequential(new ShootCommand(true,0.72));
    	
    }
    public void autoMode2Red(int delay){
    	SmartDashboard.putString("DB/String 3", "Red, Drive to hopper");
    	addSequential(new AutoDriveCommand(-7));
    	addSequential(new TurnWithPIDCommand(90));
    	addSequential(new AutoDriveUltraSonicForwardCommand(.7, 15));
    	addSequential(new AutoDriveUltraSonicBackwardCommand(-.5,2));
    	addSequential(new TurnWithPIDCommand(-90));
    	addParallel(new AutoDriveCommand(1));
    	addSequential(new ShootCommand(true,95));
    	
    	
    }
    public void autoMode3Red(int delay){
    	SmartDashboard.putString("DB/String 3", "Red, Right Hook");
    	addParallel(new LightCommand(0.45));
    	addSequential(new AutoDriveCommand(5));
    	addSequential(new TurnWithPIDCommand(60));    	
    	addSequential(new VisionTargetCommand("targets"),0.5);
    	addSequential(new TargetSortCommand(),0.5);
    	addSequential(new AutoTurnCommand());
    	addSequential(new AutoDriveUltraSonicForwardCommand(.5, 10));
    	addSequential(new WaitCommand(1.5));
    	if(Robot.envelopeSubsystem.gearCheck()){
    	addSequential(new AutoDriveUltraSonicBackwardCommand(-.7, 35));
    	addSequential(new VisionTargetCommand("targets"),0.5);
    	addSequential(new TargetSortCommand(),0.5);
    	addSequential(new AutoTurnCommand());
    	addSequential(new AutoDriveUltraSonicForwardCommand(.5, 10));
    	addSequential(new WaitCommand(1.5));
    	}else{
    	
    	}
    	
    }
    public void autoMode4Red(int delay){
    	SmartDashboard.putString("DB/String 3", "Red, Left Hook");
    	addParallel(new LightCommand(0.45));
    	addSequential(new AutoDriveCommand(5));
    	addSequential(new TurnWithPIDCommand(-45));    	
    	addSequential(new VisionTargetCommand("targets"),0.5);
    	addSequential(new TargetSortCommand(),0.5);
    	addSequential(new AutoTurnCommand());
    	addSequential(new AutoDriveUltraSonicForwardCommand(.5, 10));
    	addSequential(new WaitCommand(1.5));
    	if(Robot.envelopeSubsystem.gearCheck()){
    	addSequential(new AutoDriveUltraSonicBackwardCommand(-.7, 35));
    	addSequential(new VisionTargetCommand("targets"),0.5);
    	addSequential(new TargetSortCommand(),0.5);
    	addSequential(new AutoTurnCommand());
    	addSequential(new AutoDriveUltraSonicForwardCommand(.5, 10));
    	addSequential(new WaitCommand(1.5));
    	}else{
    	
    	}
    }
    public void autoMode5Red(int delay){
    	
    }
    public void autoMode6Red(int delay){
    	
    }
    
    /*
     * blue side auto modes
     * 
     * 
     */
    public void autoMode1Blue(int delay){
    	SmartDashboard.putString("DB/String 3", "Blue, Mode1");
    	addParallel(new LightCommand(0.25));
    	addSequential(new AutoDriveUltraSonicForwardCommand(.6, 15));
    	addSequential(new WaitCommand(1.5));
    	if(Robot.envelopeSubsystem.gearCheck()){
    		addSequential(new AutoDriveUltraSonicBackwardCommand(-.7, 35));
    		addSequential(new VisionTargetCommand("targets"));
    		addSequential(new TargetSortCommand());
    		addSequential(new AutoTurnCommand());
    		addSequential(new AutoDriveUltraSonicForwardCommand(.5, 10));
    		addSequential(new WaitCommand(1.5));
    	}
    	addSequential(new AutoDriveCommand(-1));
    	addSequential(new TurnWithPIDCommand(100));
    	addSequential(new AutoDriveUltraSonicForwardCommand(1, 45));
    	addSequential(new ShootCommand(true,1));
    	
    }
    public void autoMode2Blue(int delay){
    	SmartDashboard.putString("DB/String 3", "Blue, Drive to hopper");
    	addSequential(new AutoDriveCommand(-7));
    	addSequential(new TurnWithPIDCommand(-90));
    	addSequential(new AutoDriveUltraSonicForwardCommand(.7, 15));
    	addSequential(new AutoDriveUltraSonicBackwardCommand(-.5,2));
    	addSequential(new TurnWithPIDCommand(90));
    	addParallel(new AutoDriveCommand(1));
    	addSequential(new ShootCommand(true,95));
    	
    }
    public void autoMode3Blue(int delay){
    	SmartDashboard.putString("DB/String 3", "Blue, Right Hook");
    	addParallel(new LightCommand(0.45));
    	addSequential(new AutoDriveCommand(5));
    	addSequential(new TurnWithPIDCommand(-60));    	
    	addSequential(new VisionTargetCommand("targets"),0.5);
    	addSequential(new TargetSortCommand(),0.5);
    	addSequential(new AutoTurnCommand());
    	addSequential(new AutoDriveUltraSonicForwardCommand(.5, 10));
    	addSequential(new WaitCommand(1.5));
    	if(Robot.envelopeSubsystem.gearCheck()){
    	addSequential(new AutoDriveUltraSonicBackwardCommand(-.7, 35));
    	addSequential(new VisionTargetCommand("targets"),0.5);
    	addSequential(new TargetSortCommand(),0.5);
    	addSequential(new AutoTurnCommand());
    	addSequential(new AutoDriveUltraSonicForwardCommand(.5, 10));
    	addSequential(new WaitCommand(1.5));
    	}else{
    	
    	}
    	
    }
    public void autoMode4Blue(int delay){
    	SmartDashboard.putString("DB/String 3", "Blue, Left Hook");
    	addParallel(new LightCommand(0.45));
    	addSequential(new AutoDriveCommand(5));
    	addSequential(new TurnWithPIDCommand(45));    	
    	addSequential(new VisionTargetCommand("targets"),0.5);
    	addSequential(new TargetSortCommand(),0.5);
    	addSequential(new AutoTurnCommand());
    	addSequential(new AutoDriveUltraSonicForwardCommand(.5, 10));
    	addSequential(new WaitCommand(1.5));
    	if(Robot.envelopeSubsystem.gearCheck()){
    	addSequential(new AutoDriveUltraSonicBackwardCommand(-.7, 35));
    	addSequential(new VisionTargetCommand("targets"),0.5);
    	addSequential(new TargetSortCommand(),0.5);
    	addSequential(new AutoTurnCommand());
    	addSequential(new AutoDriveUltraSonicForwardCommand(.5, 10));
    	addSequential(new WaitCommand(1.5));
    	}else{
    	
    	}
    	
    }
    public void autoMode5Blue(int delay){
    	
    }
    public void autoMode6Blue(int delay){
    	
    }
    
    /*
     * 
     * defult
     * 
     * 
     */
    public void defult(){
    	
    }
}
