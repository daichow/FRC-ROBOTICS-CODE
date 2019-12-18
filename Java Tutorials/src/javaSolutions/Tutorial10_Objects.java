package javaSolutions;

public class Tutorial10_Objects {
	
	public static void main(String[] args) {
		Chair chair1 = new Chair(420.0, "Bob", "Black");
		
		System.out.println(chair1.getHeight());
		System.out.println(chair1.getName());
		System.out.println(chair1.getColour());
	}
}