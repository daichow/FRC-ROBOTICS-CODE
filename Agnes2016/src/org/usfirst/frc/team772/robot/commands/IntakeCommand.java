package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeCommand extends Command {

	boolean direction = true; // true - in, false out
	
    public IntakeCommand(boolean direction) {
    	this.direction = direction;
    }

    protected void initialize() {
    	Robot.intakeSub.Stop();
    }

    protected void execute() {
    	if(direction){
    		Robot.intakeSub.BallIn();
    	}else{
    		Robot.intakeSub.BallOut();
    	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.intakeSub.Stop();
    }

    protected void interrupted() {
    	end();
    }
}