package org.usfirst.frc.team772.robot.commands.group;

import org.usfirst.frc.team772.robot.Robot;
import org.usfirst.frc.team772.robot.Robot.Direction;
import org.usfirst.frc.team772.robot.commands.FlywheelCommand;
import org.usfirst.frc.team772.robot.commands.IntakeCommand;
import org.usfirst.frc.team772.robot.commands.ShooterCommand;
import org.usfirst.frc.team772.robot.commands.TipperCommand;
import org.usfirst.frc.team772.robot.commands.TurretCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BarfCommand extends CommandGroup {
    public  BarfCommand() {
    	addSequential(new TurretCommand(Robot.TurretPos.CENTER, Robot.Mode.Auto)); // center turret
    	if (Robot.tipperSubsystem.getEncPosition() < -500) { // if tipper not up, bring tipper up
    		addSequential(new TipperCommand(Direction.Forward, Robot.Mode.Auto));
    	}
    	addParallel(new FlywheelCommand(Direction.Forward)); // flywheel forward
    	addSequential(new IntakeCommand(Direction.Reverse, Robot.Mode.Auto)); // intake reverse
    	addSequential(new ShooterCommand(), 0.25); // shoot
    }
}