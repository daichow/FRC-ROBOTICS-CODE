package org.usfirst.frc.team772.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team772.robot.Robot;


public class DriveCommand extends Command {

    public DriveCommand() {
    	
    }

    protected void initialize() {
    	Robot.driveSub.ArcadeDriveStop();
    }

    protected void execute() {
    	Robot.driveSub.ArcadeDrive(Robot.oi.getJoystick1(), 0.5);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.driveSub.ArcadeDriveStop();
    }

    protected void interrupted() {
    	end();
    }
}