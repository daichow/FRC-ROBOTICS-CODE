package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShootCommand extends Command {
	boolean toggle;
	double speed;
    public ShootCommand(boolean toggle, double speed) {
      this.toggle = toggle;
      this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(toggle){
    		Robot.shooterSubsystem.flywheel(speed);
        	Timer.delay(1);
        	Robot.shooterSubsystem.feeder(RobotMap.feederForwardSpeed);
        	Robot.shooterSubsystem.windowMotor(RobotMap.windowMotorSpeed);
        	Robot.intakeSubsystem.intake(RobotMap.intakeInSpeed);
    	}else{
    		Robot.intakeSubsystem.convayor(-RobotMap.convayorSpeed);
    		Timer.delay(0.5);
    		Robot.shooterSubsystem.flywheel(-RobotMap.flywheelSpeed);
        	Robot.shooterSubsystem.feeder(-RobotMap.feederForwardSpeed);
    	}
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooterSubsystem.windowMotor(0);
    	Robot.intakeSubsystem.convayor(0);
    	Robot.intakeSubsystem.intake(0);
    	Timer.delay(1);
    	Robot.shooterSubsystem.feeder(0);
    	Robot.shooterSubsystem.flywheel(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
