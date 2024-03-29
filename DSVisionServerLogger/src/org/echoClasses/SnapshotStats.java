package org.echoClasses;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;


public class SnapshotStats implements Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5528900184711272411L;
	
	public long pipeLineDelta;
	public ArrayList<Target> targets;

	
	public long getPipeLineDelta() {
		return pipeLineDelta;
	}
	public void setPipeLineDelta(long pipeLineDelta) {
		this.pipeLineDelta = pipeLineDelta;
	}
	public ArrayList<Target> getTargets() {
		return targets;
	}
	public void setTargets(ArrayList<Target> targets) {
		this.targets = targets;
	}
	
	public int size(ArrayList<Target> targets) {
		return targets.size();
	}
	
	
	
}
