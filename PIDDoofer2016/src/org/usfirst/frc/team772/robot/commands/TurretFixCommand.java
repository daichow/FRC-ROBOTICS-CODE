package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurretFixCommand extends Command {
	double setpoint;
    public TurretFixCommand(double setpoint) {
    	this.setpoint = setpoint;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(2);
    	Robot.turretSubsystem.setSetpoint(setpoint);
    	Robot.turretSubsystem.enable(); // start pid controller
    	switch((int)setpoint){ // move in direction
		case -15989: Robot.turretSubsystem.move(-0.2);
			break;
		case 16018: Robot.turretSubsystem.move(0.2);
			break;
		default:
			break;
    	}
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
    	return (Math.abs(Robot.turretSubsystem.getSetpoint() - Robot.turretSubsystem.getEncPosition()) < 50) || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.turretSubsystem.disable(); // stop pid controller
    	Robot.turretSubsystem.move(0); // stop motor
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
