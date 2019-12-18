package org.usfirst.frc.team772.robot.commands;

import java.util.Comparator;
import java.util.Vector;

import org.usfirst.frc.team772.robot.RobotMap;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.AxisCamera;

public class Vision {	
	AxisCamera camera;
	
	Image frame;
	Image binaryFrame = null;
	int imaqError;
	
	double centerX = 0;
	double centerY = 0;
	double width = 0;
	double height = 0;
	
	NIVision.Range TOTE_HUE_RANGE = new NIVision.Range(100, 255);	//Default hue range for yellow tote
	NIVision.Range TOTE_SAT_RANGE = new NIVision.Range(75, 255);	//Default saturation range for yellow tote
	NIVision.Range TOTE_LUM_RANGE = new NIVision.Range(100, 255);	//Default value range for yellow tote
	double AREA_MINIMUM = 0.5; //Default Area minimum for particle as a percentage of total image area
	double LONG_RATIO = 20/14; //Tote long side = 26.9 / Tote height = 12.1 = 2.22
	double SHORT_RATIO = 20/14; //Tote short side = 16.9 / Tote height = 12.1 = 1.4
	double SCORE_MIN = 10.0;  //Minimum score to be considered a tote
	double VIEW_ANGLE = 49.4; //View angle fo camera, set to Axis m1011 by default, 64 for m1013, 51.7 for 206, 52 for HD3000 square, 60 for HD3000 640x480
	NIVision.ParticleFilterCriteria2 criteria[] = new NIVision.ParticleFilterCriteria2[1];
	NIVision.ParticleFilterOptions2 filterOptions = new NIVision.ParticleFilterOptions2(0,0,1,1);
	Scores scores = new Scores();
	
	public Vision(){
		camera = new AxisCamera("10.7.72.11");
        frame = NIVision.imaqCreateImage(ImageType.IMAGE_HSL, 0);
        binaryFrame = NIVision.imaqCreateImage(ImageType.IMAGE_U8, 0);
        criteria[0] = new NIVision.ParticleFilterCriteria2(NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA, AREA_MINIMUM, 100.0, 0, 0);
		while(true){
			SmartDashboard.putString("DB/String 0", "While");
			try{
				camera.getImage(frame);
	    		TOTE_HUE_RANGE.minValue = (int)SmartDashboard.getNumber("Tote hue min", TOTE_HUE_RANGE.minValue);
				TOTE_HUE_RANGE.maxValue = (int)SmartDashboard.getNumber("Tote hue max", TOTE_HUE_RANGE.maxValue);
				TOTE_SAT_RANGE.minValue = (int)SmartDashboard.getNumber("Tote sat min", TOTE_SAT_RANGE.minValue);
				TOTE_SAT_RANGE.maxValue = (int)SmartDashboard.getNumber("Tote sat max", TOTE_SAT_RANGE.maxValue);
				TOTE_LUM_RANGE.minValue = (int)SmartDashboard.getNumber("Tote lum min", TOTE_LUM_RANGE.minValue);
				TOTE_LUM_RANGE.maxValue = (int)SmartDashboard.getNumber("Tote lum max", TOTE_LUM_RANGE.maxValue);
	    		NIVision.imaqColorThreshold(binaryFrame, frame, 255, NIVision.ColorMode.HSL, TOTE_HUE_RANGE, TOTE_SAT_RANGE, TOTE_LUM_RANGE);
	    		
	    		//Send particle count to dashboard
				int numParticles = NIVision.imaqCountParticles(binaryFrame, 1);
				SmartDashboard.putNumber("Masked particles", numParticles);
	    		
				CameraServer.getInstance().setImage(binaryFrame);
				
	    		//filter out small particles
				float areaMin = (float)SmartDashboard.getNumber("Area min %", AREA_MINIMUM);
				criteria[0].lower = areaMin;
				imaqError = NIVision.imaqParticleFilter4(binaryFrame, binaryFrame, criteria, filterOptions, null);
				
				//Send particle count after filtering to dashboard
				numParticles = NIVision.imaqCountParticles(binaryFrame, 1);
				SmartDashboard.putNumber("Filtered particles", numParticles);
				
				if(numParticles > 0)
				{
					//Measure particles and sort by particle size
					Vector<ParticleReport> particles = new Vector<ParticleReport>();
					for(int particleIndex = 0; particleIndex < numParticles; particleIndex++)
					{
						ParticleReport par = new ParticleReport();
						par.PercentAreaToImageArea = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA);
						par.Area = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_AREA);
						par.ConvexHullArea = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_CONVEX_HULL_AREA);
						par.BoundingRectTop = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_TOP);
						par.BoundingRectLeft = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_LEFT);
						par.BoundingRectBottom = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_BOTTOM);
						par.BoundingRectRight = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_RIGHT);
						par.CenterOfMassX = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_CENTER_OF_MASS_X);
						par.CenterOfMassY = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_CENTER_OF_MASS_Y);
						par.Width = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_AVERAGE_HORIZ_SEGMENT_LENGTH);
						par.Height = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_AVERAGE_VERT_SEGMENT_LENGTH);
						
						particles.add(par);
					}
					particles.sort(null);

					//This example only scores the largest particle. Extending to score all particles and choosing the desired one is left as an exercise
					//for the reader. Note that the long and short side scores expect a single tote and will not work for a stack of 2 or more totes.
					//Modification of the code to accommodate 2 or more stacked totes is left as an exercise for the reader.
					scores.Trapezoid = TrapezoidScore(particles.elementAt(0));
					SmartDashboard.putNumber("Trapezoid", scores.Trapezoid);
					scores.LongAspect = LongSideScore(particles.elementAt(0));
					SmartDashboard.putNumber("Long Aspect", scores.LongAspect);
					scores.ShortAspect = ShortSideScore(particles.elementAt(0));
					SmartDashboard.putNumber("Short Aspect", scores.ShortAspect);
					scores.AreaToConvexHullArea = ConvexHullAreaScore(particles.elementAt(0));
					SmartDashboard.putNumber("Convex Hull Area", scores.AreaToConvexHullArea);
					
					centerX = particles.elementAt(0).CenterOfMassX - 320;
					centerY = particles.elementAt(0).CenterOfMassY - 240;
					
					SmartDashboard.putString("DB/String 7", "VCenter X" + centerX);
					SmartDashboard.putString("DB/String 8", "VCenter Y" + centerY);

			
					RobotMap.centerX = centerX;
					RobotMap.centerY = centerY;
					
					
					SmartDashboard.putNumber("Center X", centerX);
					SmartDashboard.putNumber("Center Y", centerY);
					
					width = particles.elementAt(0).Width;
					height = particles.elementAt(0).Height;
					
					RobotMap.width = width;
					RobotMap.height = height;
					
					SmartDashboard.putNumber("Width", width);
					SmartDashboard.putNumber("Height", height);
					
					boolean isTote = scores.Trapezoid > SCORE_MIN && (scores.LongAspect > SCORE_MIN || scores.ShortAspect > SCORE_MIN) && scores.AreaToConvexHullArea > SCORE_MIN;
					boolean isLong = scores.LongAspect > scores.ShortAspect;
					
					//Send distance and tote status to dashboard. The bounding rect, particularly the horizontal center (left - right) may be useful for rotating/driving towards a tote
					SmartDashboard.putBoolean("IsTote", isTote);
					SmartDashboard.putNumber("Distance", computeDistance(binaryFrame, particles.elementAt(0), isLong));
				} else {
					SmartDashboard.putBoolean("IsTote", false);
					SmartDashboard.putNumber("Trapezoid", scores.Trapezoid);
					SmartDashboard.putNumber("Long Aspect", scores.LongAspect);
					SmartDashboard.putNumber("Short Aspect", scores.ShortAspect);
				}
				SmartDashboard.putString("DB/String 1", "Try");

	    	}catch(Exception e){
	    		e.printStackTrace();
				SmartDashboard.putString("DB/String 2", "Catch");
	    	}
			Timer.delay(0.001);
		}
	}
	
	public class ParticleReport implements Comparator<ParticleReport>, Comparable<ParticleReport>{
		double PercentAreaToImageArea;
		double Area;
		double ConvexHullArea;
		double BoundingRectLeft;
		double BoundingRectTop;
		double BoundingRectRight;
		double BoundingRectBottom;
		double CenterOfMassX;
		double CenterOfMassY;
		double Width;
		double Height;
		
		public int compareTo(ParticleReport r)
		{
			return (int)(r.Area - this.Area);
		}
		
		public int compare(ParticleReport r1, ParticleReport r2)
		{
			return (int)(r1.Area - r2.Area);
		}
	};
	
	public class Scores {
		double Trapezoid;
		double LongAspect;
		double ShortAspect;
		double AreaToConvexHullArea;
	};
	
	//Comparator function for sorting particles. Returns true if particle 1 is larger
		static boolean CompareParticleSizes(ParticleReport particle1, ParticleReport particle2)
		{
			//we want descending sort order
			return particle1.PercentAreaToImageArea > particle2.PercentAreaToImageArea;
		}

		/**
		 * Converts a ratio with ideal value of 1 to a score. The resulting function is piecewise
		 * linear going from (0,0) to (1,100) to (2,0) and is 0 for all inputs outside the range 0-2
		 */
		double ratioToScore(double ratio)
		{
			return (Math.max(0, Math.min(100*(1-Math.abs(1-ratio)), 100)));
		}

		/**
		 * Method to score convex hull area. This scores how "complete" the particle is. Particles with large holes will score worse than a filled in shape
		 */
		double ConvexHullAreaScore(ParticleReport report)
		{
			SmartDashboard.putNumber("Area?", report.Area);
			return ratioToScore((report.Area/report.ConvexHullArea)*1.18);
		}

		/**
		 * Method to score if the particle appears to be a trapezoid. Compares the convex hull (filled in) area to the area of the bounding box.
		 * The expectation is that the convex hull area is about 95.4% of the bounding box area for an ideal tote.
		 */
		double TrapezoidScore(ParticleReport report)
		{
			return ratioToScore(report.ConvexHullArea/((report.BoundingRectRight-report.BoundingRectLeft)*(report.BoundingRectBottom-report.BoundingRectTop))); // *.954
		}

		/**
		 * Method to score if the aspect ratio of the particle appears to match the long side of a tote.
		 */
		double LongSideScore(ParticleReport report)
		{
			return ratioToScore(((report.BoundingRectRight-report.BoundingRectLeft)/(report.BoundingRectBottom-report.BoundingRectTop))/LONG_RATIO);
		}

		/**
		 * Method to score if the aspect ratio of the particle appears to match the short side of a tote.
		 */
		double ShortSideScore(ParticleReport report){
			return ratioToScore(((report.BoundingRectRight-report.BoundingRectLeft)/(report.BoundingRectBottom-report.BoundingRectTop))/SHORT_RATIO);
		}

		/**
		 * Computes the estimated distance to a target using the width of the particle in the image. For more information and graphics
		 * showing the math behind this approach see the Vision Processing section of the ScreenStepsLive documentation.
		 *
		 * @param image The image to use for measuring the particle estimated rectangle
		 * @param report The Particle Analysis Report for the particle
		 * @param isLong Boolean indicating if the target is believed to be the long side of a tote
		 * @return The estimated distance to the target in feet.
		 */
		double computeDistance (Image image, ParticleReport report, boolean isLong) {
			double normalizedWidth, targetWidth;
			NIVision.GetImageSizeResult size;

			size = NIVision.imaqGetImageSize(image);
			normalizedWidth = 2*(report.BoundingRectRight - report.BoundingRectLeft)/size.width;
			targetWidth = isLong ? 26.0 : 16.9;

			return  targetWidth/(normalizedWidth*12*Math.tan(VIEW_ANGLE*Math.PI/(180*2)));
		}
		
//		public double getCenterX(){
//			return centerX;
//		}
//		public double getCenterY(){
//			return centerY;
//		}
}