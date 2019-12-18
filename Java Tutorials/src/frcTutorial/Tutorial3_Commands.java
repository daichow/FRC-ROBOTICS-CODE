package frcTutorial;

public class Tutorial3_Commands {
	
	// Commands are a sequence of subsystem methods being used in a scheduled loop
	
	// There are 5 main methods that are run through scheduling
	// initialize, execute, isFinished, end, and interrupted
	
	// initialize is run when the command is first called
	// execute is looped while isFinished is returning false
	// isFinished returns a boolean value depending on the expression that it returns
	// end is run when isFinished returns true
	// interrupted is run when the command is interrupted at any time it is running
	
	// Commands that are created can then be called in OI through buttons or command groups that use them
}
