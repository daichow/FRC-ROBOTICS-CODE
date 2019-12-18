package frcTutorial;

public class Tutorial4_CommandGroups {
	
	// A CommandGroup is a class file that can run many commands in a sequence
	
	// addSequential adds a command that runs on its own
	// addParallel adds a command that runs with the sequential below it
	
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