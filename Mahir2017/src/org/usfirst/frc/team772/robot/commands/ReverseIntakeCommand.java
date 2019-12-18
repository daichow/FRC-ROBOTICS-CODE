package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ReverseIntakeCommand extends Command {

    public ReverseIntakeCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Before this command is called, the speed of the reverse intake motors are zero
    	
    	Robot.intakeSubsytem.intake(0);
    	Robot.shootingSubsystem.agitate(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	//The intake motors [intake and convayor motors] are turning in the opposite way to spit out balls
    	Robot.intakeSubsytem.intake(-1);
    	//The agitator motors are also used to move the balls toward the intake motors
    	Robot.shootingSubsystem.agitate(-0.2);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    //Since isFinished returns false, it goes to the interrupted method
    protected void end() {
    	Robot.intakeSubsytem.intake(0);
    	Robot.shootingSubsystem.agitate(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
