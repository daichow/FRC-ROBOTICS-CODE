package org.usfirst.frc.team772.robot.vision;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

public class Particle implements Comparable<Particle> {

	public final double percentArea;
	public final double area;
	public final double convexHullArea;
	public final double perimeter;
	public final double convexHullPerimeter;
	public final double boundingArea;

	public final double boundingRectLeft;
	public final double boundingRectTop;
	public final double boundingRectRight;
	public final double boundingRectBottom;
	
	public final double centerX;
	public final double centerY;

	public Particle(Image binaryFrame, int particleIndex) {
		percentArea = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0,
				NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA);
		area = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_AREA);
		convexHullArea = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0,
				NIVision.MeasurementType.MT_CONVEX_HULL_AREA);
		perimeter = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_PERIMETER);
		convexHullPerimeter = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0,
				NIVision.MeasurementType.MT_CONVEX_HULL_PERIMETER);
		boundingArea = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0,
				NIVision.MeasurementType.MT_BOUNDING_RECT_WIDTH)
				* NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0,
						NIVision.MeasurementType.MT_BOUNDING_RECT_HEIGHT);
		centerX = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0,
				NIVision.MeasurementType.MT_CENTER_OF_MASS_X);
		centerY = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0,
				NIVision.MeasurementType.MT_CENTER_OF_MASS_Y);
		boundingRectTop = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_TOP);
		boundingRectLeft = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_LEFT);
		boundingRectBottom = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_BOTTOM);
		boundingRectRight = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_RIGHT);
	}

	@Override
	public int compareTo(Particle o) {
		if (o.percentArea > percentArea) {
			return 1;
		}
		return 0;
	}
}