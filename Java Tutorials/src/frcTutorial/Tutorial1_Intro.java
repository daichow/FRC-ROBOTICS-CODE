package frcTutorial;

public class Tutorial1_Intro {

	// Welcome 
	
	
	// Every FRC Java Project has OI, Robot, and RobotMap classes.
	
	// OI deals with Operator Interface
	// This is where you create your joystick and buttons
	// You can then assign these buttons to do certain commands
	
	// Robot is the equivalent of your java main method
	// This is where the robot is scheduled
	// It contains methods that are called at specific points during the match
	// This is where you should instantiate your subsystems, actuators, and sensors
	
	// RobotMap is a class where you store values in variables that may be used globally
	
	
	// Then, there are Commands, Command Groups and Subsystems
	
	// Subsystems should be created for each moving part on the robot
	// Subsystems hold methods that control or manipulate the moving part on the robot
	
	// Commands should be created for every action that the robot will do
	// Commands use the methods created in subsystems
	// Each command has 5 methods
	// These 5 methods are called in a sequence 
	// When the command is called, the initialize method is run
	// Then, while isFinished returns false, execute runs
	// When isFinished returns true, execute stops running and end runs once
	// Interrupted is called whenever the command is interrupted by something
	
	// Command Groups are grouped commands that run in a sequence
	// From top to bottom, it runs each command
	// addSequential commands runs by itself until it is finished
	// addParallel commands run with the sequential command below them
	// Ex.
	// addSequential
	// addSequential
	// These commands would run individually
	
	// addParallel
	// addSequential
	// These would run together
	
	// addParallel
	// addParallel
	// addSequential
	// These three would run together
}
