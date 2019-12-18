package org.usfirst.frc.team772.robot.commands.group;

import org.usfirst.frc.team772.robot.Robot.Direction;
import org.usfirst.frc.team772.robot.Robot.Mode;
import org.usfirst.frc.team772.robot.Robot.TurretPos;
import org.usfirst.frc.team772.robot.commands.RotateCommand;
import org.usfirst.frc.team772.robot.commands.TipperCommand;
import org.usfirst.frc.team772.robot.commands.TurretCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCommand extends CommandGroup {
	
	// selects automode
	
    public AutoCommand(int automode) {
    	switch (automode) {
	    	case 0: Auto0Command();
	    		break;
	    	case 1: Auto1Command();
	    		break;
	    	case 2: Auto2Command();
	    		break;
	    	case 3: Auto3Command();
	    		break;
	    	case 4: Auto4Command();
	    		break;
	    	case 5: Auto5Command();
	    		break;
	    	case 6: Auto6Command();
	    		break;
	    	case 7: Auto7Command();
	    		break;
	    	case 8: Auto8Command();
	    		break;
	    	case 9: Auto9Command();
	    		break;
	    	case 10: Auto10Command();
	    		break;
	    	default: Auto0Command();
	    		break;
    	}
    }
    
    ///////////////////////////////////////////
    /* Auto0Command
     * 
     * f(x) = Does Nothing
     * 
     * Made by Akshay Jacob
     */
    ///////////////////////////////////////////
    public void Auto0Command(){
    	
    }
    
    ///////////////////////////////////////////
    /* Auto1Command
     * 
     * f(x) = reach
     * 
     * Made by me
     */
    ///////////////////////////////////////////
    public void Auto1Command(){
    	addSequential(new ReachCommand());
    }
    
    ///////////////////////////////////////////
    /* Auto2Command
     * 
     * f(x) = Cross and shoot
     */
    ///////////////////////////////////////////
    public void Auto2Command(){
    	addSequential(new DriveOverCommand(-0.9));
        addSequential(new RotateCommand(0, false));
//      addSequential(new TipperCommand(-1500, Mode.Auto, true));
        addSequential(new TipperCommand(Direction.Reverse, Mode.Auto));
        addSequential(new DriveDistanceCommand(), 2.5);
    	addSequential(new TurretGyroCommand());
//    	addSequential(new VisionTurretCommand(), 2);
//    	addSequential(new ShooterCommand());
    	addSequential(new ShootingCommand());
    }
    
    ///////////////////////////////////////////
    /* Auto3Command
     * f(x) = cheval de frise
     */
    ///////////////////////////////////////////
    public void Auto3Command(){
    	addSequential(new ReachCommand());
    	addSequential(new TipperCommand(Direction.Reverse, Mode.Auto));
//    	addParallel(new DriveSpeedCommand(-0.5), 1);
    	addParallel(new DriveOverCommand(-0.5));
    	addSequential(new TipperCommand(Direction.Forward, Mode.Auto));
    	addSequential(new ShootingCommand());
    }
    
    ///////////////////////////////////////////
    /* Auto4Command
     * 
     * f(x) = Drive until in range then shoot
     */
    ///////////////////////////////////////////
    public void Auto4Command(){
//    	addSequential(new TipperCommand(-1500, Mode.Auto, true));
    	addSequential(new TipperCommand(Direction.Reverse, Mode.Auto));
//    	addSequential(new TurretCommand(TurretPos.RC, Mode.Auto));
    	addSequential(new TurretCommand(TurretPos.LC, Mode.Auto));
    	addSequential(new DriveDistanceCommand(), 2.5);
    	addSequential(new ShootingCommand());
//    	addSequential(new FlywheelCommand(Direction.Forward));
// 		addSequential(new EmptyCommand(), 2);
//    	addSequential(new JustShootCommand());
//    	addSequential(new FlywheelCommand(Direction.Stop));
    }
    
    ///////////////////////////////////////////
    /* Auto5Command
     * 
     * f(x) = Cross, shoot, cross
     */
    ///////////////////////////////////////////
    public void Auto5Command(){
    	addSequential(new DriveOverCommand(-0.9)); // drive over
        addSequential(new RotateCommand(0, false)); // rotate to 0
        addSequential(new TipperCommand(Direction.Reverse, Mode.Auto)); // tipper down
        addSequential(new DriveDistanceCommand(), 2.5); // drive until close
    	addSequential(new TurretGyroCommand()); // turret to gyro 0
    	addSequential(new ShootingCommand()); // shoot
    	
    	addParallel(new RotateCommand(180, false)); // rotate 180
    	addParallel(new TurretCommand(TurretPos.CENTER, Mode.Auto)); // center turret
    	addSequential(new TipperCommand(Direction.Reverse, Mode.Auto)); // tipper up
    	addSequential(new DriveOverCommand(-0.9), 3); // drive over
    }
    
    ///////////////////////////////////////////
    /* Auto6Command
     * 
     */
    ///////////////////////////////////////////
    public void Auto6Command(){
    	
    }
    
    ///////////////////////////////////////////
    /* Auto7Command
     * 
     */
    ///////////////////////////////////////////
    public void Auto7Command(){
    	
    }
    
    ///////////////////////////////////////////
    /* Auto8Command
     * 
     */
    ///////////////////////////////////////////
    public void Auto8Command(){
    	
    }
    
    ///////////////////////////////////////////
    /* Auto9Command
     * 
     */
    ///////////////////////////////////////////
    public void Auto9Command(){
    	
    }
    
    ///////////////////////////////////////////
    /* Auto10Command
     * 
     */
    ///////////////////////////////////////////
    public void Auto10Command(){
    	
    }
}