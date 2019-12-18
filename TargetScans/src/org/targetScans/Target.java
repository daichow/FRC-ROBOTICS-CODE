package org.targetScans;

public class Target {
// fields
	private int centreX;
	private int centreY;
	private int area;
	private int solidity;
	private int width;
	private int height;
	
	// Constructors initialize fields.
	
	public Target() {
		centreX = -1;
		centreY = -1;
		area = -1;
		solidity = -1;
		width = -1;
		height = -1;
		
	}
	
	//Methods. 
	
	public int getCentreX() {
		return centreX;
	}
	
	public void setCentreX(int anyCentreX) {
		centreX = anyCentreX;
	}

	public int getCentreY() {
		return centreY;
	}

	public void setCentreY(int centreY) {
		this.centreY = centreY;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public int getSolidity() {
		return solidity;
	}

	public void setSolidity(int solidity) {
		this.solidity = solidity;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public String toString() {
		
		return "CentreX(" + this.getCentreX() + ")" ;
	}

	public boolean checkifValidTarget(Target t1) {
		
		if (t1.getArea() < 300) { 
			System.out.println("Threw target away");
			return false ;
		}
		else return true;
		
	}
	
	
	
}
