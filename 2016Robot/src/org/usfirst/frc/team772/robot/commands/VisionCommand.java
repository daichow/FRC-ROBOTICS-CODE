package org.usfirst.frc.team772.robot.commands;

import java.util.Vector;

import org.usfirst.frc.team772.robot.RobotMap;
import org.usfirst.frc.team772.robot.vision.Particle;
import org.usfirst.frc.team772.robot.vision.ScoreAnalyser;
import org.usfirst.frc.team772.robot.vision.Scores;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;
import com.ni.vision.NIVision.MatchShapeResult;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionCommand extends Command {

	int counter = 0;
	
	// initialises camera and what kind of camera it is
//	AxisCamera camera;
	
	// initialising what we need to put the image on the screen
	Image frame;
	Image binaryFrame = null;
	int imaqError;
	int session;
	
	// declaring variables and setting defaults
	double centerX = 0;
	double centerY = 0;
	double width = 0;
	double height = 0;
	int L = -999;
	int index1 = 0;
	int index2 = 0;
	int index3 = 0;
	Image shapeImage1;
	Image shapeImage2;
	Image shapeImage3;
	Image imageTemplate1;
	Image imageTemplate2;
	Image imageTemplate3;
	
	MatchShapeResult shapeReport1 = null;
	MatchShapeResult shapeReport2 = null;
	MatchShapeResult shapeReport3 = null;

//	NIVision.Range TOTE_HUE_RANGE = new NIVision.Range(105, 255);	//Default hue range for yellow tote 100, 255
//	NIVision.Range TOTE_SAT_RANGE = new NIVision.Range(0, 255);	//Default saturation range for yellow tote 50, 255
//	NIVision.Range TOTE_LUM_RANGE = new NIVision.Range(235, 255);	//Default value range for yellow tote 100, 255
	
	// max min values for allowed target finding
	NIVision.Range TOTE_HUE_RANGE = new NIVision.Range(0, 255);	//Default hue range for yellow tote 100, 255
	NIVision.Range TOTE_SAT_RANGE = new NIVision.Range(0, 255);	//Default saturation range for yellow tote 50, 255
	NIVision.Range TOTE_LUM_RANGE = new NIVision.Range(205, 255);	//Default value range for yellow tote 100, 255
	double AREA_MINIMUM = 0.5; //Default Area minimum for particle as a percentage of total image area
	double LONG_RATIO = 20/14; //Tote long side = 26.9 / Tote height = 12.1 = 2.22
	double SHORT_RATIO = 20/14; //Tote short side = 16.9 / Tote height = 12.1 = 1.4
	double SCORE_MIN = 10.0;  //Minimum score to be considered a tote
	double VIEW_ANGLE = 49.4; //View angle fo camera, set to Axis m1011 by default, 64 for m1013, 51.7 for 206, 52 for HD3000 square, 60 for HD3000 640x480
	NIVision.ParticleFilterCriteria2 criteria[] = new NIVision.ParticleFilterCriteria2[1];
	NIVision.ParticleFilterOptions2 filterOptions = new NIVision.ParticleFilterOptions2(0,0,1,1);

    public VisionCommand() {
//    	camera = new AxisCamera("10.7.72.11"); // find camera
        frame = NIVision.imaqCreateImage(ImageType.IMAGE_HSL, 0); // create frame
        binaryFrame = NIVision.imaqCreateImage(ImageType.IMAGE_U8, 0); // create binaryFrame
        criteria[0] = new NIVision.ParticleFilterCriteria2(NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA, AREA_MINIMUM, 100.0, 0, 0);
        session = NIVision.IMAQdxOpenCamera("cam1", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        NIVision.IMAQdxConfigureGrab(session);
        IMAQdxStartAcquisition();
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	counter++;
    	SmartDashboard.putString("DB/String 0", "looping");
    	if (counter > 2) {
			try{
//				camera.getImage(frame); // get image
				NIVision.IMAQdxGrab(session, frame, 1);
				
//				TOTE_HUE_RANGE.minValue = (int)SmartDashboard.getNumber("Tote hue min", TOTE_HUE_RANGE.minValue);
//				TOTE_HUE_RANGE.maxValue = (int)SmartDashboard.getNumber("Tote hue max", TOTE_HUE_RANGE.maxValue);
//				TOTE_SAT_RANGE.minValue = (int)SmartDashboard.getNumber("Tote sat min", TOTE_SAT_RANGE.minValue);
//				TOTE_SAT_RANGE.maxValue = (int)SmartDashboard.getNumber("Tote sat max", TOTE_SAT_RANGE.maxValue);
//				TOTE_LUM_RANGE.minValue = (int)SmartDashboard.getNumber("Tote lum min", TOTE_LUM_RANGE.minValue);
//				TOTE_LUM_RANGE.maxValue = (int)SmartDashboard.getNumber("Tote lum max", TOTE_LUM_RANGE.maxValue);
				
				// thresholds by colour
	    		NIVision.imaqColorThreshold(binaryFrame, frame, 255, NIVision.ColorMode.HSI, TOTE_HUE_RANGE, TOTE_SAT_RANGE, TOTE_LUM_RANGE);
	    		
	    		//Send particle count to dashboard
				int numParticles = NIVision.imaqCountParticles(binaryFrame, 1); // puts particles on the frame
				SmartDashboard.putNumber("Masked particles", numParticles); // puts the number of particles on the dashboard underneath variables
	    		
				CameraServer.getInstance().setImage(binaryFrame); // gets camera and sets the image to the frame
				
	    		//filter out small particles
				float areaMin = (float)SmartDashboard.getNumber("Area min %", AREA_MINIMUM); // puts minimum area on the dash under variables
				criteria[0].lower = areaMin; // sets area min to the smallest val foumnd in array
				imaqError = NIVision.imaqParticleFilter4(binaryFrame, binaryFrame, criteria, filterOptions, null);
				
				//Send particle count after filtering to dashboard
				numParticles = NIVision.imaqCountParticles(binaryFrame, 1); // sets numParticles to the ones found on binary frame
				SmartDashboard.putNumber("Filtered particles", numParticles); // puts particles on dashboard underneath variables
				
				if(numParticles > 0) // does the rest if finds particles
				{
					//Measure particles and sort by particle size
					Vector<Particle> particles = new Vector<Particle>();
					for(int particleIndex = 0; particleIndex < numParticles; particleIndex++)
					{
						Particle p = new Particle(binaryFrame, particleIndex); // create particle
						Scores s = new Scores(p, binaryFrame); // calc score
						if (ScoreAnalyser.isAcceptable(s)) {
							SmartDashboard.putString("DB/String 6", "acceptable");
							particles.add(p);
							RobotMap.isTarget = s.isTarget;
							RobotMap.distance = s.distance;
						} else {
							RobotMap.isTarget = false;
							RobotMap.distance = 0;
						}
					}
				
					particles.sort(null);
					
					RobotMap.centerX = particles.elementAt(0).centerX - 320;
					RobotMap.centerY = particles.elementAt(0).centerY - 240;
				} else {
					RobotMap.centerX = 0;
					RobotMap.centerY = 0;
				}
	    	}catch(Exception e){
	    		e.printStackTrace(); // prints the error if it doesnt work, tells you what the error is
	    		SmartDashboard.putString("DB/String 1", "catch");
	    	}
			counter = 0;
    	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	IMAQdxStopAcquisition();
    }

    protected void interrupted() {
    	end();
    }
    
    public void IMAQdxStartAcquisition() {
		NIVision.IMAQdxStartAcquisition(session);
	}
    
    public void IMAQdxStopAcquisition() {
    	NIVision.IMAQdxStopAcquisition(session);
    }
}