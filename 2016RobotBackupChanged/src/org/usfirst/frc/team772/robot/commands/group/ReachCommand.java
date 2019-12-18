package org.usfirst.frc.team772.robot.commands.group;

import org.usfirst.frc.team772.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ReachCommand extends Command {

	// reach
	
    public ReachCommand() {
       
    }

    protected void initialize() {

    }

    protected void execute() {
    	Robot.driveSubsystem.move(-0.45, 0); // move at 45%
    }

    protected boolean isFinished() {
    	if (Robot.colourS.getVoltage() > 0.5) { // finish when sees outer work
    		return true;
    	} else {
    		return false;
    	}
    }	

    protected void end() {
    	Robot.driveSubsystem.move(0, 0); // stops drive
    }
    
    protected void interrupted() {
    	end(); // go to end
    }
}