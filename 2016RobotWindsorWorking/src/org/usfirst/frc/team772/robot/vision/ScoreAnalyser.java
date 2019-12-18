package org.usfirst.frc.team772.robot.vision;


public class ScoreAnalyser {
	public static final double areaOverConvexAreaIdeal = 76.0 / 240;
	public static final double perimeterOverConvexPerimeterIdeal = 84.5 / 64;
	public static final double plenimeterIdeal = 84.5 * 84.5 / 76;

	public static final double errorRange = .2;
	public static double areaRange = .5;

	public static double periRange = .3;
	public static double pleniRange = .2;
	public static double rectRange = .3;

	public static boolean isAcceptable(Scores s) {
		boolean areaAcceptable = Maths.percentError(areaOverConvexAreaIdeal, s.areaToConvexArea) < areaRange;
//		boolean perimeterAcceptable = Maths.percentError(perimeterOverConvexPerimeterIdeal,
//				s.perimeterToConvexPerimeter) < periRange;
//		boolean plenimeterAcceptable = Maths.percentError(plenimeterIdeal, s.plenimeter) < pleniRange;
//		boolean rectanglinessAcceptable = Maths.percentError(s.rectangliness, 1) < rectRange;
		
		//SmartDashboard.putString("DB/String 2", "area: " + Robot.round(s.areaToConvexArea, 3));
		//SmartDashboard.putString("DB/String 3", "%Err: " + Robot.round(Maths.percentError(areaOverConvexAreaIdeal, s.areaToConvexArea), 3));
		
		return areaAcceptable /*&& perimeterAcceptable && plenimeterAcceptable && rectanglinessAcceptable*/;
	}
}