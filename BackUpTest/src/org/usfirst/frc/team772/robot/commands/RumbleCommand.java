package org.usfirst.frc.team772.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.command.Command;

public class RumbleCommand extends Command {

	Joystick joystick;
	RumbleType type;
	
    public RumbleCommand(Joystick joystick, RumbleType type) {
    	this.joystick = joystick;
    	this.type = type;
    }

    protected void initialize() {
    	joystick.setRumble(type, 1); // rumble joystick side
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	joystick.setRumble(type, 0); // stop rumble
    }

    protected void interrupted() {
    	end();
    }
}