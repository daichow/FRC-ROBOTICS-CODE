package org.usfirst.frc.team772.robot.commands.visionBoiler;

import java.util.Arrays;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TargetBoilerSortCommand extends Command {

    public TargetBoilerSortCommand() {
    }

    // Called just before this Command runs the first time
 
    protected void initialize() {
		SmartDashboard.putString("DB/String 5", "init");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.haveCurrentTargetsBoiler && RobotMap.connectedToBoilerPI){
    	if(Robot.currentTargets.size() > 1){
			double curr = 0;
			double near = 1;
			int target1 = 0;
			int target2 = 0;
			double asd = 0;
    		for(int j = 0; j <= Robot.currentTargets.size()-1; j++){
    			for(int i = j+1; i < Robot.currentTargets.size(); i++ ){
    				curr = Robot.currentTargets.get(j).getCentreX()/Robot.currentTargets.get(i).getCentreX();
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
        	Robot.haveCurrentTargetsBoiler = true;
        	Robot.sortCurrentTargets = true;
        	RobotMap.averageDegreeOffset = ((Robot.currentTargets.get(target1).getDegreeOffset()+ Robot.currentTargets.get(target2).getDegreeOffset())/2) - 3;
    	}else if(Robot.currentTargets.size() == 1){
    		Robot.DegreeOffsettarget1 = Robot.currentTargets.get(0).getDegreeOffset();
        	Robot.DegreeOffsettarget2 = 0;
    		RobotMap.averageDegreeOffset = Robot.currentTargets.get(0).getDegreeOffset() - 3;
    	}else{
    		Robot.haveCurrentTargetsBoiler = false;
    		Robot.sortCurrentTargets = false;
    		Robot.DegreeOffsettarget1 = 0;
        	Robot.DegreeOffsettarget2 = 0;
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
