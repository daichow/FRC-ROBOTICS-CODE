package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterCommand extends Command {

	//Since this command runs while held, it needs to execute and it gets interrupted when the button is released
    public ShooterCommand() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//This initializes the motor speed values to zero
    	Robot.shootingSubsystem.shoot(0,0,0,0);
    	Robot.oi.xbox.setRumble(edu.wpi.first.wpilibj.Joystick.RumbleType.kRightRumble, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//When this command is later called by a button, the shoot method from the shootingSubsystem gets called 
    	Robot.shootingSubsystem.shoot(0.3, 0.3, 0.3, 0.3);
    	Robot.oi.xbox.setRumble(edu.wpi.first.wpilibj.Joystick.RumbleType.kRightRumble, 1);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//isFinished is returning false because the motor values have to be set to zero after this command ends
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shootingSubsystem.shoot(0,0,0,0);
    	Robot.oi.xbox.setRumble(edu.wpi.first.wpilibj.Joystick.RumbleType.kRightRumble, 0);
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
