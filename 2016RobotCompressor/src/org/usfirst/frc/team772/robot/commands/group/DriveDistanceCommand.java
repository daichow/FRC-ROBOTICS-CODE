package org.usfirst.frc.team772.robot.commands.group;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class DriveDistanceCommand extends Command {

    public DriveDistanceCommand() {
    	
    }

    protected void initialize() {
//    	Robot.drive.cancel();
    	Robot.driveSubsystem.move(-0.6, 0);
    }

    protected void execute() {
    	Robot.driveSubsystem.move(-0.6, 0);
    }

    protected boolean isFinished() {
        return RobotMap.distance < 8;
    }

    protected void end() {
    	Robot.driveSubsystem.move(0, 0);
//    	Robot.drive.start();
    }

    protected void interrupted() {
    	end();
    }
}