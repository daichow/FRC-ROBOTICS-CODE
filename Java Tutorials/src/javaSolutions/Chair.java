package javaSolutions;

public class Chair {

	double height = 0.0;
	String name = null;
	String colour = null;
	
	public Chair(double height, String name, String colour) {
		this.height = height;
		this.name = name;
		this.colour = colour;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}
}