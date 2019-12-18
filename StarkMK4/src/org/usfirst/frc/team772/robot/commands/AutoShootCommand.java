package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoShootCommand extends Command {
	Timer timer = new Timer();
    public AutoShootCommand() {
    }

    protected void initialize() {
    	//turns on flywheel, waits 2 seconds, moves the ball along the conveyer
    	//to launch said ball, small .5sec delay to guarantee the ball launches
    	//before the command finishes
    	Robot.flywheelSubsystem.flywheel(RobotMap.flywheelSpeed);
    	Timer.delay(2);
    	Robot.conveyorSubsystem.conveyor(RobotMap.convayorInSpeed);
    	Timer.delay(.5);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    	Robot.flywheelSubsystem.flywheel(0);
    	Robot.conveyorSubsystem.conveyor(0);
    }

    protected void interrupted() {
    	end();
    }
}
