package org.usfirst.frc.team772.robot.commands.visionGear;

import java.util.Arrays;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TargetGearSortCommand extends Command {

    public TargetGearSortCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
 
    protected void initialize() {
		SmartDashboard.putString("DB/String 5", "init");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.haveCurrentTargetsGear){
    	if(Robot.currentTargets.size() > 1){
			double curr = 0;
			double near = 1;
			int target1 = 0;
			int target2 = 0;
			double asd = 0;
    		for(int j = 0; j < Robot.currentTargets.size()-1; j++){
    			for(int i = j+1; i < Robot.currentTargets.size(); i++ ){
    				curr = Robot.currentTargets.get(j).getCentreY()/Robot.currentTargets.get(i).getCentreY();
    				asd = Math.abs(curr-1);
    				if(asd < near){
    					near = asd;
    					target1 = j;
    					target2 = i;
    				}
    			}
    		}
    		SmartDashboard.putString("DB/String 5", "targetSorted");
    		Robot.DegreeOffsettarget1 = Robot.currentTargets.get(target1).getDegreeOffset();
        	Robot.DegreeOffsettarget2 = Robot.currentTargets.get(target2).getDegreeOffset();
        	Robot.haveCurrentTargetsGear = true;
        	Robot.sortCurrentTargets = true;
        	RobotMap.averageDegreeOffset = (Robot.currentTargets.get(target1).getDegreeOffset()+ Robot.currentTargets.get(target2).getDegreeOffset())/2;
    	}else{
    		Robot.haveCurrentTargetsGear = false;
    		Robot.sortCurrentTargets = false;
    		RobotMap.averageDegreeOffset = 0;
    	}
    }
    }


    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
