package org.echoClasses;
import java.io.Serializable;

public class Target  implements Serializable{
	
	private static final long serialVersionUID = 1;
// fields
	private double centreX;
	private double centreY;
	private double contourArea;
	private double rectArea;
	private double aspectRatio;
	private double extentRatio;
	private double equivDiam;
	private double degreeOffset;
	private long setmstime;
	private double solidity;
	public double getSolidity() {
		return solidity;
	}

	public void setSolidity(double solidity) {
		this.solidity = solidity;
	}

	private String targetMode;
	
	// Constructors initialize fields.
	
	public Target() {
		centreX = -1;
		centreY = -1;
		contourArea = -1;
		rectArea = -1;
		aspectRatio = -1;
		extentRatio = -1;
		equivDiam = -1;
		degreeOffset = 0.0;
		setmstime = 0;
		targetMode = "unknown";
	}
	
	//Methods. 
	
	public String getTargetMode() {
		return targetMode;
	}

	public void setTargetMode(String targetMode) {
		this.targetMode = targetMode;
	}

	public void setSetmstime(long setmstime) {
		this.setmstime = setmstime;
	}

	public double getDegreeOffset() {
		return degreeOffset;
	}

	public void setDegreeOffset(double degreeOffset) {
		this.degreeOffset = degreeOffset;
	}

	public double getContourArea() {
		return contourArea;
	}

	public void setContourArea(double contourArea) {
		this.contourArea = contourArea;
	}

	public double getRectArea() {
		return rectArea;
	}

	public void setRectArea(double rectArea) {
		this.rectArea = rectArea;
	}

	public double getAspectRatio() {
		return aspectRatio;
	}

	public void setAspectRatio(double aspectRatio) {
		this.aspectRatio = aspectRatio;
	}

	public double getExtentRatio() {
		return extentRatio;
	}

	public void setExtentRatio(double extentRatio) {
		this.extentRatio = extentRatio;
	}

	public double getEquivDiam() {
		return equivDiam;
	}

	public void setEquivDiam(double equivDiam) {
		this.equivDiam = equivDiam;
	}

	
	public double getCentreX() {
		return centreX;
	}
	
	public void setCentreX(double x) {
		centreX = x;
	}

	public double getCentreY() {
		return centreY;
	}

	public void setCentreY(double y) {
		this.centreY = y;
	}

	
	public String toString() {
		
		return "CentreX(" + this.getCentreX() + ")" ;
	}


	public void setAspect(double aspect_ratio) {
		// TODO Auto-generated method stub
		
	}

	public boolean checkifValidTarget(Target target) {
		// TODO Auto-generated method stub
		return true;
	}
	
	public void setmstime(long delta) {
		this.setmstime = delta;
		
	}

	public long getSetmstime() {
		return setmstime;
	}
	
	
}