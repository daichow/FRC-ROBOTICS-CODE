
public class Robot {

	String size;
	int voltageBeingFed =0;
	
	int x=1;
	int y=3;
	int z;
	
	public int add() {
	
		return z =x+y;
	}
	
	public static void main(String[] args) {
		Robot robot = new Robot();
		
		robot.add();
		
		System.out.println(robot.add());
	}
	
	
}
