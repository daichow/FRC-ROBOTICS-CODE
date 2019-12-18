package org.usfirst.frc.team772.robot.commands.group;

import org.usfirst.frc.team772.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TurretGyroCommand extends Command {
	
	// Turns turret to gyro heading
	
    public TurretGyroCommand() {
    	
    }
    
    protected void initialize() {
    	setTimeout(1);
    	if (Robot.navxSubsystem.getAngle() <= 90) { // sets angle setpoint
    		Robot.turretSubsystem.setSetpoint(-Robot.navxSubsystem.getAngle() * 16018 / 90);
    	} else if (Robot.navxSubsystem.getAngle() >= 270) {
    		Robot.turretSubsystem.setSetpoint(-(Robot.navxSubsystem.getAngle() - 360) * 16018 / 90);
    	}
    	Robot.turretSubsystem.enable(); // starts pid controller
    }

    protected void execute() {

    }

    protected boolean isFinished() {
    	return Math.abs(Robot.turretSubsystem.getSetpoint() - Robot.turretSubsystem.getEncPosition()) < 100 || isTimedOut(); // stop when within 50 
    }

    protected void end() {
    	Robot.turretSubsystem.disable(); // stop pid controller
    }

    protected void interrupted() {
    	end(); // goto end
    }
}