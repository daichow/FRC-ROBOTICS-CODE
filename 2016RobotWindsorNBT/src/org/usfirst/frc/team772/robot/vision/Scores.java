package org.usfirst.frc.team772.robot.vision;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

public class Scores {

	double AREA_MINIMUM = 0.5; //Default Area minimum for particle as a percentage of total image area
	double LONG_RATIO = 20/14; //Tote long side = 26.9 / Tote height = 12.1 = 2.22
	double SHORT_RATIO = 20/14; //Tote short side = 16.9 / Tote height = 12.1 = 1.4
	double SCORE_MIN = 10.0;  //Minimum score to be considered a tote
	double VIEW_ANGLE = 49.4; //View angle fo camera, set to Axis m1011 by default, 64 for m1013, 51.7 for 206, 52 for HD3000 square, 60 for HD3000 640x480
	
	public final double Trapezoid;
	public final double LongAspect;
	public final double ShortAspect;
	public final double AreaToConvexHullArea;
	public final boolean isTarget;
	public final double distance;
	
    public final double areaToConvexArea;
    public final double plenimeter;
    public final double perimeterToConvexPerimeter;
    public final double rectangliness;

    public Scores(Particle p, Image binaryFrame) {
		this.areaToConvexArea = p.area / p.convexHullArea;
		this.plenimeter = (p.perimeter) * (p.perimeter) / p.area;
		this.perimeterToConvexPerimeter = p.perimeter / p.convexHullPerimeter;
		this.rectangliness = p.convexHullArea / p.boundingArea;
		this.Trapezoid = TrapezoidScore(p);
		this.LongAspect = LongSideScore(p);
		this.ShortAspect = ShortSideScore(p);
		this.AreaToConvexHullArea = ConvexHullAreaScore(p);
		this.isTarget = this.Trapezoid > SCORE_MIN && (this.LongAspect > SCORE_MIN || this.ShortAspect > SCORE_MIN) && this.AreaToConvexHullArea > SCORE_MIN;
		this.distance = computeDistance(binaryFrame, p, this.LongAspect > this.ShortAspect);
    }
    
    double ratioToScore(double ratio)
	{
		return (Math.max(0, Math.min(100*(1-Math.abs(1-ratio)), 100)));
	}
    
    double TrapezoidScore(Particle p)
	{
		return ratioToScore(p.convexHullArea/((p.boundingRectRight-p.boundingRectLeft)*(p.boundingRectBottom-p.boundingRectTop))); // *.954
	}
    
    double LongSideScore(Particle p)
	{
		return ratioToScore(((p.boundingRectRight-p.boundingRectLeft)/(p.boundingRectBottom-p.boundingRectTop))/LONG_RATIO);
	}
    
    double ShortSideScore(Particle p){
		return ratioToScore(((p.boundingRectRight-p.boundingRectLeft)/(p.boundingRectBottom-p.boundingRectTop))/SHORT_RATIO);
	}
    
    double ConvexHullAreaScore(Particle p)
	{
		//SmartDashboard.putNumber("Area?", p.area);
		return ratioToScore((p.area/p.convexHullArea)*1.18);
	}
    
    double computeDistance (Image image, Particle report, boolean isLong) {
		double normalizedWidth, targetWidth;
		NIVision.GetImageSizeResult size;

		size = NIVision.imaqGetImageSize(image);
		normalizedWidth = 2*(report.boundingRectRight - report.boundingRectLeft)/size.width;
		targetWidth = isLong ? 26.0 : 16.9;

		return  targetWidth/(normalizedWidth*12*Math.tan(VIEW_ANGLE*Math.PI/(180*2)));
	}
}