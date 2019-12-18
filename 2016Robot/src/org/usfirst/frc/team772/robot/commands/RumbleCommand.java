package org.usfirst.frc.team772.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.command.Command;

public class RumbleCommand extends Command {

	// Rumble joystick
	
	Joystick joystick;
	boolean condition = true;
	
	 public RumbleCommand(Joystick joystick) {
	    	this.joystick = joystick;
	    }
	
    public RumbleCommand(Joystick joystick, boolean condition) {
    	this.joystick = joystick;
    	this.condition = condition;
    }

    protected void initialize() {
    	if (condition) {
	    	joystick.setRumble(RumbleType.kLeftRumble, 1);
	    	joystick.setRumble(RumbleType.kRightRumble, 1);
    	}
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	joystick.setRumble(RumbleType.kLeftRumble, 0); // stop rumble
    	joystick.setRumble(RumbleType.kRightRumble, 0); // stop rumble
    }

    protected void interrupted() {
    	end();
    }
}