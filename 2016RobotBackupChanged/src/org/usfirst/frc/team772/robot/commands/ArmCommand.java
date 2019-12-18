package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ArmCommand extends Command {

	// Wind winch while held
	
    public ArmCommand() {
       
    }

    protected void initialize() {
    	Robot.armSubsystem.move(0.7); // move 70%
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false; // while held
    }

    protected void end() {
    	Robot.flywheelSubsystem.move(0); // stop
    }

    protected void interrupted() {
    	end(); // goto end
    }
}