package org.usfirst.frc.team772.robot;

public class Target {
// fields
	private double centreX;
	private double centreY;
	private double area;
	private double solidity;
	private double width;
	private double height;
	
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
	
	public double getCentreX() {
		return centreX;
	}
	
	public void setCentreX(double centerX) {
		centreX = centerX;
	}

	public double getCentreY() {
		return centreY;
	}

	public void setCentreY(double centerY) {
		this.centreY = centerY;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area2) {
		this.area = area2;
	}

	public double getSolidity() {
		return solidity;
	}

	public void setSolidity(double solidity2) {
		this.solidity = solidity2;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double height2) {
		this.width = height2;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double width2) {
		this.height = width2;
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
