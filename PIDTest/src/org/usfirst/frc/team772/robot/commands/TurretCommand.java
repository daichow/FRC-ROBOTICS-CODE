package org.usfirst.frc.team772.robot.commands;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.subsystems.TurretSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurretCommand extends Command {
	
	Robot.TurretPos pos;
	
    public TurretCommand(Robot.TurretPos pos) {
    	this.pos = pos;
    }

    protected void initialize() {
    	switch(pos){
    		case LEFT: Robot.turretSubsystem.setSetpoint(TurretSubsystem.LEFT);
    			break;
    		case CENTER: Robot.turretSubsystem.setSetpoint(TurretSubsystem.CENTER);
    			break;
    		case RIGHT: Robot.turretSubsystem.setSetpoint(TurretSubsystem.RIGHT);
    			break;
    		default: Robot.turretSubsystem.setSetpoint(TurretSubsystem.CENTER);
    	}
    	SmartDashboard.putString("DB/String 0", "Setpoint: " + Robot.turretSubsystem.getSetpoint());
    	Robot.turretSubsystem.enable();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return Math.abs(Robot.turretSubsystem.getSetpoint() - Robot.turretSubsystem.getEncPosition()) < 50;
    }

    protected void end() {
    	Robot.turretSubsystem.disable();
//    	Robot.turretSubsystem.move(0);
    }

    protected void interrupted() {
    	end();
    }
}