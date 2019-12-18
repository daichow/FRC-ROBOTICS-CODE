package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

//used to retract and extend intakes

public class IntakePneumaticCommand extends Command {
	boolean toggle = true; //used to toggle command
    public IntakePneumaticCommand() {
    }

    protected void initialize() {
    	/*
    	 * check for variable toggle declared before to identify which state the arms
    	 * are at
    	 * if toggle is true that means arms are in therefore turn on leds on intake and then extend it 
    	 * if toggle is false then leds are turned off and intake arms are retracted
    	 */
    	if (toggle){
    		Robot.intakeSubsystem.intakeOff();
    		Robot.intakeSubsystem.intakeIn();
    		toggle = false;
    	}else{
    		Robot.intakeSubsystem.intakeOn();
    		Robot.intakeSubsystem.intakeOut();
    		toggle = true;
    	}
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true; //command only needs to run once
    }

    protected void end() {
    }

    protected void interrupted() {
    	end();
    }
}
