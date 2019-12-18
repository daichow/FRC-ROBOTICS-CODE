package org.targetScans;

import junit.framework.TestCase;

public class TargetTest extends TestCase {

	public void testTarget() {
		Target t1 = new Target();
		assertEquals(-1,t1.getCentreX());
		
	}

	public void testSetCentreX() {
		Target t1 = new Target();
		t1.setCentreX(150);
		assertEquals(150,t1.getCentreX());
	}

	public void testSetCentreY() {
		Target t1 = new Target();
		t1.setCentreY(100);
		assertEquals(100,t1.getCentreY());
	}

	public void testSetArea() {
		Target t1 = new Target();
		t1.setArea(100);
		assertEquals(100,t1.getArea());
	}

	public void testSetSolidity() {
		Target t1 = new Target();
		t1.setSolidity(100);
		assertEquals(100,t1.getSolidity());
	}

	public void testSetWidth() {
		Target t1 = new Target();
		t1.setWidth(100);
		assertEquals(100,t1.getWidth());
	}

	public void testSetHeight() {
		Target t1 = new Target();
		t1.setHeight(100);
		assertEquals(100,t1.getHeight());
	}

	public void testToString() {
		Target t1 = new Target();
		
		String testString = "CentreX(-1)";
		assertEquals(testString, t1.toString());
				
	
	}
	public void testIsValidTarget() {
		
		Target t1 = new Target();
		
		t1.setArea(100);
		t1.setCentreX(50);
		t1.setCentreY(50);
		t1.setWidth(10);
		t1.setHeight(10);
		t1.setSolidity(20);
		
		assertTrue("Target is not Valid",t1.checkifValidTarget(t1));
		
	}
}
	
	
