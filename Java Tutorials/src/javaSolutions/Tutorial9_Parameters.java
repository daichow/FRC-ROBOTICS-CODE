package javaSolutions;

public class Tutorial9_Parameters {
	
	public static void main(String[] args) {
		firstMethod("This is printing from another method using a parameter");
		secondMethod(23, 42.0);
	}
	
	static void firstMethod(String stringVar){
		System.out.println(stringVar);
	}
	
	static void secondMethod(int intVar, double doubleVar){
		System.out.println(intVar + doubleVar);
	}
}