package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LiftCommand extends Command {

    public LiftCommand() {

    }

    protected void initialize() {
//    	Robot.liftingSubsystem.move(SmartDashboard.getNumber("DB/Slider 1"));
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
//    	Robot.liftingSubsystem.move(0);
    }

    protected void interrupted() {
    	end();
    }
}