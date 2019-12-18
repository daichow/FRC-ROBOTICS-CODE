package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class TurretGyroCommand extends Command {
	
	// Turns turret to gyro heading
	
	Timer timer = new Timer();
	
    public TurretGyroCommand() {
    	
    }
    
    protected void initialize() {
    	setTimeout(1);
    	if (Robot.getGoodAngle() <= 90) {
    		Robot.turretSubsystem.setSetpoint(-Robot.getGoodAngle() * 16018 / 90);
    	} else if (Robot.getGoodAngle() >= 270) {
    		Robot.turretSubsystem.setSetpoint(-(Robot.getGoodAngle() - 360) * 16018 / 90);
    	}
    	Robot.turretSubsystem.enable();
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