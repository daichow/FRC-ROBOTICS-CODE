package org.echoClasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class myAllTargets  {

ArrayList<Target> targets;
	
	public myAllTargets() {
		targets = new ArrayList<Target>();
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
	
	
//	public static void main(String[] args) {
//	
//		myAllTargets mat = new myAllTargets();
//	    
//		int numTargets = randInt (1,5);
//		System.out.println("Create " + numTargets + " Targets");
//		
//		// Create a random number of targets and add to myAllTargets nat Arraylist
//		for (int x = 0; x < numTargets; x++) {
//			Target aTarget = new Target();
//			
//			aTarget.setCentreX(randInt(1,160));
//			aTarget.setCentreY(randInt(1,120));
//			aTarget.setWidth(randInt(0,50));
//			aTarget.setHeight(randInt(0,50));
//			aTarget.setArea(aTarget.getWidth() * aTarget.getHeight());;
//			aTarget.setSolidity(randInt(1,100));
//			
//			mat.addTarget(aTarget);
//			
//		}
//		
//		// print out stats on Targets created
//		
//		ArrayList<Target> currentTargets = mat.getTarget(mat);
//		
//		for (int x = 0; x < mat.getTarget(mat).size(); x++) {
//			
//			System.out.println("Target # " + x);
//			
//			printTargetInfo(currentTargets, x);
//			
//			System.out.println("*************************");
//		}
//		
//		int smallestCX = findSmallestCXInTargets(currentTargets);
//		
//		System.out.println("Target with smallest CX is: "+ smallestCX);
//		System.out.println("Target # " + smallestCX);
//		printTargetInfo(currentTargets, smallestCX);
//		
//		int largestCX = findLargestCXInTargets(currentTargets);
//		
//		System.out.println("Target with largest CX is: "+ largestCX);
//		System.out.println("Target # " + largestCX);
//		printTargetInfo(currentTargets, largestCX);
//		
//	}
 public static void printSingleTargetInfo(Target atarget) {
	 	System.out.println("centreX : " + atarget.getCentreX());
		System.out.println("centreY : " + atarget.getCentreY());
		
	 
 }
	
	public static void printTargetInfo(ArrayList<Target> targets, int x ) {
		double centreX = targets.get(x).getCentreX();
		double centreY = targets.get(x).getCentreY();
		double degreeOffset = targets.get(x).getDegreeOffset();
		
		
		System.out.println("centreX : " + centreX);
		System.out.println("centreY : " + centreY);
		System.out.println("degreeOffset: " + degreeOffset);
		
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
	
	
