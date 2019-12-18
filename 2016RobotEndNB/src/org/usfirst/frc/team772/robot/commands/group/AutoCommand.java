package org.usfirst.frc.team772.robot.commands.group;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.Robot.Direction;
import org.usfirst.frc.team772.robot.Robot.Mode;
import org.usfirst.frc.team772.robot.RobotMap;
import org.usfirst.frc.team772.robot.commands.CheckShootCommand;
import org.usfirst.frc.team772.robot.commands.FlywheelCommand;
import org.usfirst.frc.team772.robot.commands.RotateCommand;
import org.usfirst.frc.team772.robot.commands.ShootCommand;
import org.usfirst.frc.team772.robot.commands.TipperCommand;
import org.usfirst.frc.team772.robot.commands.TurretCommand;
import org.usfirst.frc.team772.robot.commands.TurretGyroCommand;
import org.usfirst.frc.team772.robot.commands.VisionCommand;
import org.usfirst.frc.team772.robot.commands.WaitCommand;
import org.usfirst.frc.team772.robot.subsystems.TurretSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCommand extends CommandGroup {
	
	// selects automode
	
    public AutoCommand(int automode, int position, int speed) {
    	switch (automode) {
	    	case 0: Auto0Command();
	    		break;
	    	case 1: Auto1Command();
	    		break;
	    	case 2: Auto2Command(position, speed);
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
	    	case 11: Auto11Command();
	    		break;
	    	case 12: Auto12Command();
    			break;
	    	case 13: Auto13Command();
	    		break;
	    	default: Auto0Command();
	    		break;
    	}
    }
    
    ///////////////////////////////////////////
    /* Auto0Command
     * 
     * f(x) = Does Nothing
     */
    ///////////////////////////////////////////
    public void Auto0Command(){
    	
    }
    
    ///////////////////////////////////////////
    /* Auto1Command
     * 
     * f(x) = Reach
     */
    ///////////////////////////////////////////
    public void Auto1Command(){
    	addSequential(new ReachCommand());
    }
    
    ///////////////////////////////////////////
    /* Auto2Command
     * Used when in position 4
     * f(x) = Cross and shoot
     * 
     * Num1: Speed
     * 
     * Num2: Position
     * 
     */
    ///////////////////////////////////////////
    public void Auto2Command(int position, int speed){
    	switch(speed){
	    	case 1: addSequential(new DriveOverCommand(-0.9), 3); // drives over
	    		break;
	    	case 2: addSequential(new DriveOverCommand(-0.8), 2.5); // drives over
	    		break;
    		default: addSequential(new DriveOverCommand(-0.9), 3); // drives over
    	}
        switch(position){ // drive forward and turn turret depending on position
	        case 2: addSequential(new RotateCommand(10)); // back to 0 degrees
			        addSequential(new DriveLidarCommand(40), 3);
			        addSequential(new TipperCommand(Direction.Reverse, Mode.Auto)); // tipper down
	        		addParallel(new TurretCommand(8500, Mode.Auto));
	        	break;
	        case 3: addSequential(new RotateCommand(10)); // back to 0 degrees
			        addSequential(new DriveLidarCommand(40), 3);
			        addParallel(new TipperCommand(Direction.Reverse, Mode.Auto)); // tipper down
	        	break;
	        case 4: addSequential(new RotateCommand(0)); // back to 0 degrees
	        		addSequential(new DriveLidarCommand(40), 3);
	        		addParallel(new TipperCommand(Direction.Reverse, Mode.Auto)); // tipper down
	        	break;
	        case 5: addSequential(new RotateCommand(-10)); // back to 0 degrees
	        		addSequential(new DriveLidarCommand(40), 3);
	        		addSequential(new TipperCommand(Direction.Reverse, Mode.Auto)); // tipper down
	        		addParallel(new TurretCommand(-3561, Mode.Auto));
	        	break;
	       default: addParallel(new TipperCommand(Direction.Reverse, Mode.Auto)); // tipper down
	        	break;
        }
        addSequential(new TeleShootingCommand());
		addSequential(new TurretCommand(TurretSubsystem.CENTER, Mode.Auto)); // turns turret to center
    }
    
    ///////////////////////////////////////////
    /* Auto3Command
     * f(x) = Cheval de frise
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
//    	addSequential(new TurretCommand(TurretSubsystem.RC, Mode.Auto));
    	addSequential(new TurretCommand(TurretSubsystem.LC, Mode.Auto));
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
        addSequential(new RotateCommand(0)); // rotate to 0
        addSequential(new TipperCommand(Direction.Reverse, Mode.Auto)); // tipper down
        addSequential(new DriveDistanceCommand(), 2.5); // drive until close
    	addSequential(new TurretGyroCommand()); // turret to gyro 0
    	addSequential(new ShootingCommand()); // shoot
    	
    	addParallel(new RotateCommand(180)); // rotate 180
    	addParallel(new TurretCommand(TurretSubsystem.CENTER, Mode.Auto)); // center turret
    	addSequential(new TipperCommand(Direction.Reverse, Mode.Auto)); // tipper up
    	addSequential(new DriveOverCommand(-0.9), 3); // drive over
    }
    
    ///////////////////////////////////////////
    /* Auto6Command
     * 
     * f(x) = Just drive over
     */
    ///////////////////////////////////////////
    public void Auto6Command(){
    	addSequential(new DriveOverCommand(-1));
    }
    
    ///////////////////////////////////////////
    /* Auto7Command
     * f(x) = Just drive over and a bit more
     */
    ///////////////////////////////////////////
    public void Auto7Command(){
    	addSequential(new DriveOverCommand(-1));
    	addSequential(new DriveSpeedCommand(-1, 0), 0.5);
    }
    
    ///////////////////////////////////////////
    /* Auto8Command
     * f(x) = Drive until close and shoot
     */
    ///////////////////////////////////////////
    public void Auto8Command(){
    	addSequential(new DriveDistanceCommand(), 2.5);
    	addSequential(new ShootingCommand());
    }
    
    ///////////////////////////////////////////
    /* Auto9Command
     * Used when in position 2
     * e(x) = cross, drive up to a lidar distance, rotate turret right and shoot
     */
    ///////////////////////////////////////////
    public void Auto9Command(){
    	addSequential(new DriveOverCommand(-0.9));
//        addSequential(new RotateCommand(0, false));
        addSequential(new TipperCommand(Direction.Reverse, Mode.Auto));
//        addSequential(new DriveLidarCommand(150), 2.5);
        addSequential(new TurretCommand(TurretSubsystem.RC, Mode.Auto));
        addSequential(new AutoVisionTurretCommand());
    	addSequential(new ShootingCommand());
    }
    
    ///////////////////////////////////////////
    /* Auto10Command
     * Used when in position 3 or 4
     * e(x) = cross, drive up to a lidar distance and shoot
     */
    ///////////////////////////////////////////
    public void Auto10Command(){
    	addSequential(new DriveOverCommand(-0.9));
//        addSequential(new RotateCommand(0, false));
        addSequential(new TipperCommand(Direction.Reverse, Mode.Auto));
//        addSequential(new DriveLidarCommand(200), 2.5);
        addSequential(new AutoVisionTurretCommand());
    	addSequential(new ShootingCommand());
    }
    
    ///////////////////////////////////////////
    /* Auto11Command
     * Used when in position 5
     * e(x) = cross,drive up to a lidar distance, rotate turret to the left and shoot
     */
    ///////////////////////////////////////////
    public void Auto11Command(){
    	addSequential(new DriveOverCommand(-0.9));
//        addSequential(new RotateCommand(0, false));
        addSequential(new TipperCommand(Direction.Reverse, Mode.Auto));
//        addSequential(new DriveLidarCommand(150), 2.5);
        addSequential(new TurretCommand(TurretSubsystem.LC, Mode.Auto));
        addSequential(new AutoVisionTurretCommand());
    	addSequential(new ShootingCommand());
    }
    
    ///////////////////////////////////////////
    /* Auto11Command
     * Used when in spy bot
     * e(x) = drive up to tower, rotate turret and poop at it
     */
    ///////////////////////////////////////////
    public void Auto12Command(){
    	addParallel(new TipperCommand(-1000, Mode.Auto, true));
    	addParallel(new TurretCommand(TurretSubsystem.LC, Mode.Auto));
    	addSequential(new DriveEncoderCommand());
    	addSequential(new FlywheelCommand(Direction.Forward)); // stops flywheel
//    	addSequential(new AutoVisionTurretCommand()); // aim
    	addParallel(new VisionCommand(), 5);
     	addSequential(new AutoTurretFixCommand(false));
        addParallel(new VisionCommand(), 5);
     	addSequential(new AutoTurretFixCommand(true));
    	addSequential(new CheckShootCommand(), 0.5);
     	addSequential(new FlywheelCommand(Direction.Stop)); // stops flywheel
 		addSequential(new TurretCommand(TurretSubsystem.CENTER, Mode.Auto)); // turns turret to center
 		addSequential(new TipperCommand(Direction.Forward, Mode.Auto));
    }
    
    //rough terrain auto
    //e(x) = do it
    public void Auto13Command(){
    	addSequential(new DriveOverCommand(-0.8), 3); // drives over
    	addSequential(new WaitCommand(), 0.5); // waits instead of gyro fix
    	addSequential(new DriveLidarCommand(70)); // drive to correct distance
        addParallel(new TipperCommand(Direction.Reverse, Mode.Auto)); // tipper down
        addSequential(new FlywheelCommand(Direction.Forward)); // flywheel on
        addSequential(new AutoVisionTurretCommand()); // aim
        if(Robot.ultrasonicSubsystem.isBallInShooter() && Math.abs(RobotMap.centerX) < 30){ // only shoots when ball in shooter and aimed
        	addSequential(new ShootCommand(), 0.5);
    		addSequential(new WaitCommand(), 1); // waits
    	}
    	addSequential(new FlywheelCommand(Direction.Stop)); // stops flywheel
		addSequential(new TurretCommand(TurretSubsystem.CENTER, Mode.Auto)); // turns turret to center
    }
}