package org.usfirst.frc.team772.robot.commands.group;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class DriveDistanceCommand extends Command {

	// drives until close enough to shoot
	
    public DriveDistanceCommand() {
    	
    }

    protected void initialize() {
    	Robot.driveSubsystem.move(-0.6, 0); // drive forward at 60%
    }

    protected void execute() {
    	Robot.driveSubsystem.move(-0.6, 0); // drive forward at 60%
    }

    protected boolean isFinished() {
        return RobotMap.distance < 10; // stops when less than 10
    }

    protected void end() {
    	Robot.driveSubsystem.move(0, 0); // stop drive
    }

    protected void interrupted() {
    	end(); // go to end
    }
}