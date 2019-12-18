package org.targetScans;

import java.util.ArrayList;

import junit.framework.TestCase;

public class MyAllTargetsTest extends TestCase {

	
	// test Constructor
	
	public void testMyAllTarget() {
		
		myAllTargets mat = new myAllTargets();
		
		assertTrue(mat.targets instanceof ArrayList);
		
	}
	
	public void testAddTarget() {
		
		Target t1 = new Target();
		myAllTargets mat = new myAllTargets();
		
		assertEquals(0,mat.getTarget(mat).size());
		
		mat.addTarget(t1);
		
		assertEquals(1,mat.getTarget(mat).size());
	}
	
	public void testRemoveTarget() {
	
		Target t1 = new Target();
		myAllTargets mat = new myAllTargets();
		
		assertEquals(0,mat.getTarget(mat).size());
		
		mat.addTarget(t1);
		
		assertEquals(1,mat.getTarget(mat).size());
		
		mat.removeTarget(t1);
		
		assertEquals(0,mat.getTarget(mat).size());
		
		
	}
	public void testKillTargets() {
		Target t1 = new Target();
		Target t2 = new Target();
		myAllTargets mat = new myAllTargets();
		
		assertEquals(0,mat.getTarget(mat).size());
		
		mat.addTarget(t1);
		
		assertEquals(1,mat.getTarget(mat).size());
		
		mat.addTarget(t2);
		
		assertEquals(2,mat.getTarget(mat).size());
		
		mat.removeAllTargets();
		
		assertEquals(0,mat.getTarget(mat).size());
	}
}
