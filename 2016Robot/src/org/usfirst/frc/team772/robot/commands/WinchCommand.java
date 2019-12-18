package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class WinchCommand extends Command {

	// Wind winch while held
	
    public WinchCommand() {
       
    }

    protected void initialize() {
//    	Robot.winchSubsystem.move(SmartDashboard.getNumber("DB/Slider 0"));
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false; // while held
    }

    protected void end() {
//    	Robot.winchSubsystem.move(0); // stop
    }

    protected void interrupted() {
    	end(); // goto end
    }
}