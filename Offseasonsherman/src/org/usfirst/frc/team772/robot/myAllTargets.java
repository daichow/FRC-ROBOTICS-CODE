package org.usfirst.frc.team772.robot;

import java.util.ArrayList;
import java.util.Random;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class myAllTargets {

	ArrayList<Target> targets;
	public static NetworkTable network;
	
	public myAllTargets() {
		targets = new ArrayList<Target>();
		network = NetworkTable.getTable("GRIP/myContoursReport");
	}

	public void addTarget(Target t1) {
		this.targets.add(t1);
	}

	public ArrayList<Target> getTarget(myAllTargets targetList) {
		
		return targets;
	}

	public void removeAllTargets() {
		
		this.targets.clear();
		
	}

	public void removeTarget(Target t1) {
		this.targets.remove(t1);
		
	}

	public static int randInt (int lowerbound, int upperbound) {
		
		Random rand = new Random();
		// nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((upperbound - lowerbound) + 1) + lowerbound;

	    return randomNum;
		
	}
	
	
	public static void main(String[] args) {
	
		myAllTargets mat = new myAllTargets();
		
		
		double[] defaultValue = new double[0];
        double[] area = network.getNumberArray("area", defaultValue);
        double[] centerY =  network.getNumberArray("centerY", defaultValue);
        double[] centerX = network.getNumberArray("centerX", defaultValue);
        double[] width =  network.getNumberArray("centerY", defaultValue);
        double[] height = network.getNumberArray("area", defaultValue);
        double[] solidity =  network.getNumberArray("centerY", defaultValue);
        
        int numTargets = (area.length-1);
		System.out.println("Create " + numTargets + " Targets");
		
		// Create a random number of targets and add to myAllTargets nat Arraylist
		for (int x = 0; x < numTargets; x++) {
			Target aTarget = new Target();
	       
			aTarget.setCentreX(centerX[x]);
			aTarget.setCentreY(centerY[x]);
			aTarget.setWidth(width[x]);
			aTarget.setHeight(height[x]);
			aTarget.setArea(area[x]);;
			aTarget.setSolidity(solidity[x]);
			
			mat.addTarget(aTarget);
			
		}
		
		// print out stats on Targets created
		
		ArrayList<Target> currentTargets = mat.getTarget(mat);
		
		for (int x = 0; x < mat.getTarget(mat).size(); x++) {
			
			System.out.println("Target # " + x);
			
			printTargetInfo(currentTargets, x);
			
			System.out.println("*************************");
		}
		
		int smallestCX = findSmallestCXInTargets(currentTargets);
		
		System.out.println("Target with smallest CX is: "+ smallestCX);
		System.out.println("Target # " + smallestCX);
		printTargetInfo(currentTargets, smallestCX);
		
		int largestCX = findLargestCXInTargets(currentTargets);
		
		System.out.println("Target with largest CX is: "+ largestCX);
		System.out.println("Target # " + largestCX);
		printTargetInfo(currentTargets, largestCX);
		
	}

	private static void printTargetInfo(ArrayList<Target> currentTargets, int x ) {
		double centreX = currentTargets.get(x).getCentreX();
		double centreY = currentTargets.get(x).getCentreY();
		double width = currentTargets.get(x).getWidth();
		double height = currentTargets.get(x).getHeight();
		double area = currentTargets.get(x).getArea();
		double solidity = currentTargets.get(x).getSolidity();
		
		System.out.println("centreX : " + centreX);
		System.out.println("centreY : " + centreY);
		System.out.println("width : " + width);
		System.out.println("height : " + height);
		System.out.println("area : " + area);
		System.out.println("solidity : " + solidity);
	}
	
	public static int findSmallestCXInTargets(ArrayList<Target> currentTargets) {
		
		//iterate over current targets, and return index of target with smallest CentreX
		
		double smallestCX;
		int interestingtarget;
		
		smallestCX = 1000;
		interestingtarget = -1;
		
		for (Target target : currentTargets) {
			
			if (target.checkifValidTarget(target) && 
					target.getCentreX() < smallestCX ) 
			{
				smallestCX = target.getCentreX();
				interestingtarget = currentTargets.indexOf(target);
			}
			
			}
			
		return interestingtarget;
		
	}
	
	public static int findLargestCXInTargets(ArrayList<Target> currentTargets) {
		
		//iterate over current targets, and return index of target with smallest CentreX
		
		double largestCX;
		int interestingtarget;
		
		largestCX = -1;
		interestingtarget = -1;
		
		for (Target target : currentTargets) {
			
			if (target.checkifValidTarget(target) && 
					target.getCentreX() > largestCX ) 
			{
				largestCX = target.getCentreX();
				interestingtarget = currentTargets.indexOf(target);
			}
			
			}
			
		return interestingtarget;
	
	}
	
	
}
	
	
