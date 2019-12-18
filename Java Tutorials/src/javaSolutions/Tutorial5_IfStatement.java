package javaSolutions;

public class Tutorial5_IfStatement {
	
	public static void main(String[] args) {
		
		boolean variableName = false; // boolean variable that is set to false
		
		if(variableName){ // only runs code inside if variableName is true
			System.out.println("If Statement 1"); // prints "If Statement 1"
		}
		
		if(!variableName){ // only runs code inside if variableName is false
			System.out.println("If Statement 2"); // prints "If Statement 2"
		}
	}
}